package com.mycompany.agricola.controllers.compras;

import java.awt.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.config.AppConfig;
import com.mycompany.agricola.controllers.TotalesLinea;
import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.dao.implement.CompraDaoApl;
import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CarritoCompraEntity;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.services.AuthService;
import com.mycompany.agricola.services.InventarioLoteService;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.compras.FormularioAgregarCompraVista;

public class FormularioAgregarCompraController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String[] COLUMNAS_CARRITO = {"No", "Producto", "Cantidad", "Precio", "Subtotal"};

    private final CompraDaoApl compraDao = new CompraDaoApl();
    private final ProductoDaoApl productoDao = new ProductoDaoApl();
    private final InventarioLoteService inventarioLoteService = new InventarioLoteService();
    private final NavegacionService navegacion = new NavegacionService();

    private final List<LineaPendiente> lineasPendientes = new ArrayList<>();
    private List<ProductoEntity> productosCache = new ArrayList<>();
    private String noFactura = generarNoFactura();

    public void abrir(Component parent) {
        FormularioAgregarCompraVista vista = new FormularioAgregarCompraVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista);
        navegacion.abrirVistaSiPermitida("FormularioAgregarCompraVista", vista, parent);
    }

    private void inicializarVista(FormularioAgregarCompraVista vista) {
        vista.etiquetaNoFacturaValor.setText(noFactura);
        vista.inputFechaExpiracion.setText(LocalDateTime.now().plusYears(1).format(FECHA_FORMATO));
        vista.tablaCarrito.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS_CARRITO));
        cargarProductos(vista);
        actualizarCalculosLinea(vista);
        actualizarTablaCarrito(vista);
    }

    public List<ProductoEntity> listarProductos() {
        return productoDao.getAll();
    }

    public List<CarritoCompraEntity> getCarrito() {
        List<CarritoCompraEntity> carrito = new ArrayList<>();
        for (LineaPendiente linea : lineasPendientes) {
            CarritoCompraEntity item = new CarritoCompraEntity();
            item.setNombreProducto(linea.nombreProducto);
            item.setCantidadProducto(linea.cantidad);
            item.setPrecioUnitario(linea.precioUnitario);
            item.setSubtotal(linea.subtotal);
            carrito.add(item);
        }
        return carrito;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public TotalesLinea calcularTotalesLinea(BigDecimal precioUnitario, int cantidad) {
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) <= 0 || cantidad <= 0) {
            return new TotalesLinea(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal impuesto = subtotal.multiply(BigDecimal.valueOf(AppConfig.ISV_PORCENTAJE))
                .setScale(2, java.math.RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(impuesto);
        return new TotalesLinea(precioUnitario, subtotal, impuesto, total);
    }

    public void agregarLinea(ProductoEntity producto, int cantidad, BigDecimal precioCompra,
            String metodoPago, LocalDateTime fechaExpiracion) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (precioCompra == null || precioCompra.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio de compra debe ser mayor a cero");
        }
        if (fechaExpiracion == null) {
            throw new IllegalArgumentException("La fecha de expiracion es obligatoria");
        }

        BigDecimal subtotal = precioCompra.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal impuesto = subtotal.multiply(BigDecimal.valueOf(AppConfig.ISV_PORCENTAJE))
                .setScale(2, java.math.RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(impuesto);

        LineaPendiente linea = new LineaPendiente();
        linea.idProducto = producto.getIdProducto();
        linea.nombreProducto = producto.getNombreProducto();
        linea.cantidad = cantidad;
        linea.precioUnitario = precioCompra;
        linea.subtotal = subtotal;
        linea.impuesto = impuesto;
        linea.total = total;
        linea.metodoPago = metodoPago;
        linea.fechaExpiracion = fechaExpiracion;
        lineasPendientes.add(linea);
    }

    public void eliminarLinea(int indice) {
        if (indice >= 0 && indice < lineasPendientes.size()) {
            lineasPendientes.remove(indice);
        }
    }

    public BigDecimal calcularTotalFactura() {
        return lineasPendientes.stream()
                .map(l -> l.total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularSubtotalFactura() {
        return lineasPendientes.stream()
                .map(l -> l.subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularIsvFactura() {
        return lineasPendientes.stream()
                .map(l -> l.impuesto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ResultadoPersistencia guardarCompra(int idUsuario) {
        if (lineasPendientes.isEmpty()) {
            return ResultadoPersistencia.error(new IllegalStateException("Carrito vacio"), "registrar la compra");
        }

        for (LineaPendiente linea : lineasPendientes) {
            CompraEntity compra = new CompraEntity();
            compra.setNoFactura(noFactura);
            compra.setIdForaneaProducto(linea.idProducto);
            compra.setIdForaneaUsuario(idUsuario);
            compra.setCantidadCompra(linea.cantidad);
            compra.setPrecioCompra(linea.precioUnitario);
            compra.setFechaExpiracion(linea.fechaExpiracion);
            compra.setFechaCompra(LocalDateTime.now());
            compra.setMetodoPago(linea.metodoPago);

            int idCompra = compraDao.createReturningId(compra);
            if (idCompra < 0) {
                return ResultadoPersistencia.error(new IllegalStateException("No se pudo registrar la compra"),
                        "registrar la compra");
            }

            inventarioLoteService.crearLoteDesdeCompra(
                    linea.idProducto, linea.cantidad, linea.fechaExpiracion, idCompra);
        }

        lineasPendientes.clear();
        noFactura = generarNoFactura();
        return ResultadoPersistencia.exito();
    }

    private void cargarFuncionalidades(FormularioAgregarCompraVista vista) {
        // Calculos de linea
        vista.comboboxProducto.addActionListener(e -> actualizarPrecioDesdeProducto(vista));
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { actualizarCalculosLinea(vista); }
            @Override
            public void removeUpdate(DocumentEvent e) { actualizarCalculosLinea(vista); }
            @Override
            public void changedUpdate(DocumentEvent e) { actualizarCalculosLinea(vista); }
        };
        vista.inputCantidad.getDocument().addDocumentListener(listener);
        vista.inputPrecio.getDocument().addDocumentListener(listener);

        // Carrito
        vista.botonAgregarCarrito.addActionListener(e -> agregarLinea(vista));
        vista.botonEliminarFila.addActionListener(e -> eliminarFilaSeleccionada(vista));

        // Acciones principales
        vista.botonGuardarCompra.addActionListener(e -> guardarCompra(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    private void volver(FormularioAgregarCompraVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void cargarProductos(FormularioAgregarCompraVista vista) {
        vista.comboboxProducto.removeAllItems();
        productosCache = listarProductos();
        for (ProductoEntity producto : productosCache) {
            vista.comboboxProducto.addItem(producto.getNombreProducto());
        }
        actualizarPrecioDesdeProducto(vista);
    }

    private ProductoEntity obtenerProductoSeleccionado(FormularioAgregarCompraVista vista) {
        int indice = vista.comboboxProducto.getSelectedIndex();
        if (indice >= 0 && indice < productosCache.size()) {
            return productosCache.get(indice);
        }
        return null;
    }

    private void actualizarPrecioDesdeProducto(FormularioAgregarCompraVista vista) {
        ProductoEntity producto = obtenerProductoSeleccionado(vista);
        if (producto != null && producto.getPrecioVenta() != null) {
            vista.inputPrecio.setText(producto.getPrecioVenta().toPlainString());
        } else {
            vista.inputPrecio.setText("0");
        }
        actualizarCalculosLinea(vista);
    }

    private int leerCantidad(FormularioAgregarCompraVista vista) {
        try {
            return Integer.parseInt(vista.inputCantidad.getText().trim());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private BigDecimal leerPrecio(FormularioAgregarCompraVista vista) {
        try {
            return new BigDecimal(vista.inputPrecio.getText().trim());
        } catch (NumberFormatException ex) {
            return BigDecimal.ZERO;
        }
    }

    private void actualizarCalculosLinea(FormularioAgregarCompraVista vista) {
        TotalesLinea totales = calcularTotalesLinea(leerPrecio(vista), leerCantidad(vista));
        vista.etiquetaSubtotalLinea.setText(totales.getSubtotal().toPlainString());
        vista.etiquetaIsvLinea.setText(totales.getIsv().toPlainString());
        vista.etiquetaTotalLinea.setText(totales.getTotal().toPlainString());
    }

    private void agregarLinea(FormularioAgregarCompraVista vista) {
        try {
            ProductoEntity producto = obtenerProductoSeleccionado(vista);
            if (producto == null) {
                JOptionPane.showMessageDialog(vista, "Seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int cantidad = leerCantidad(vista);
            BigDecimal precio = leerPrecio(vista);
            String metodo = (String) vista.comboboxMetodoPago.getSelectedItem();
            LocalDateTime fechaExpiracion = LocalDateTime.parse(
                    vista.inputFechaExpiracion.getText().trim(), FECHA_FORMATO);
            agregarLinea(producto, cantidad, precio, metodo, fechaExpiracion);
            actualizarTablaCarrito(vista);
            vista.inputCantidad.setText("");
            actualizarCalculosLinea(vista);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vista, "Fecha de expiracion invalida");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFilaSeleccionada(FormularioAgregarCompraVista vista) {
        int fila = vista.tablaCarrito.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione una fila del carrito", "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        eliminarLinea(fila);
        actualizarTablaCarrito(vista);
    }

    private void guardarCompra(FormularioAgregarCompraVista vista) {
        var usuario = AuthService.getUsuarioActual();
        int idUsuario = usuario != null ? usuario.getIdUser() : 1;
        ResultadoPersistencia resultado = guardarCompra(idUsuario);
        if (resultado.isExito()) {
            JOptionPane.showMessageDialog(vista, "Compra guardada correctamente");
            SwingUtilities.getWindowAncestor(vista).dispose();
        } else {
            JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTablaCarrito(FormularioAgregarCompraVista vista) {
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (CarritoCompraEntity linea : getCarrito()) {
            filas.add(new Object[]{
                no++,
                linea.getNombreProducto(),
                linea.getCantidadProducto(),
                linea.getPrecioUnitario(),
                linea.getSubtotal()
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) vista.tablaCarrito.getModel(), filas);
        vista.etiquetaSubtotalFactura.setText(calcularSubtotalFactura().toPlainString());
        vista.etiquetaIsvFactura.setText(calcularIsvFactura().toPlainString());
        vista.etiquetaTotalFactura.setText(calcularTotalFactura().toPlainString());
    }

    private String generarNoFactura() {
        return "COMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private static class LineaPendiente {
        int idProducto;
        String nombreProducto;
        int cantidad;
        BigDecimal precioUnitario;
        BigDecimal subtotal;
        BigDecimal impuesto;
        BigDecimal total;
        String metodoPago;
        LocalDateTime fechaExpiracion;
    }
}
