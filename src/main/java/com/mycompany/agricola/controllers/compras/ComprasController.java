package com.mycompany.agricola.controllers.compras;

import java.awt.Component;
import java.awt.Frame;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.dao.implement.CompraDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ComprasDetalleEntity;
import com.mycompany.agricola.model.entity.FacturaCompraEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.compras.ComprasVista;
import com.mycompany.agricola.views.compras.VerFacturaCompraDialog;
import com.mycompany.agricola.views.util.UiUtil;

public class ComprasController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String[] COLUMNAS = {"No", "No. Factura", "Fecha", "Metodo", "Total"};

    private final CompraDaoApl compraDao = new CompraDaoApl();
    private final NavegacionService navegacion = new NavegacionService();
    private final FormularioAgregarCompraController formularioAgregarController = new FormularioAgregarCompraController();

    private List<FacturaCompraEntity> facturasCache = new ArrayList<>();

    public void abrir(Component parent) {
        ComprasVista vista = new ComprasVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista, parent);
        navegacion.abrirVistaSiPermitida("ComprasVista", vista, parent);
    }

    public List<FacturaCompraEntity> listarFacturas() {
        return compraDao.getAllFacturas();
    }

    public List<ComprasDetalleEntity> listarDetalleFactura(String noFactura) {
        return compraDao.getDetalleByFactura(noFactura);
    }

    public List<ComprasDetalleEntity> listarComprasDetalle() {
        return compraDao.getAllDetalle();
    }

    public CompraEntity obtenerPorId(int id) {
        return compraDao.getById(id);
    }

    public ResultadoPersistencia eliminarFactura(String noFactura) {
        return compraDao.deleteByFactura(noFactura);
    }

    public ResultadoPersistencia eliminar(int id) {
        return compraDao.delete(id);
    }

    private void inicializarVista(ComprasVista vista) {
        vista.tablaCompras.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
        refrescarTabla(vista);
    }

    private void cargarFuncionalidades(ComprasVista vista, Component parent) {
        vista.botonAgregar.addActionListener(e -> formularioAgregarController.abrir(parent));
        vista.botonVer.addActionListener(e -> ver(vista));
        vista.botonEliminar.addActionListener(e -> eliminar(vista));
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    private void volver(ComprasVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void refrescarTabla(ComprasVista vista) {
        facturasCache = listarFacturas();
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (FacturaCompraEntity factura : facturasCache) {
            String fecha = factura.getFechaCompra() != null
                    ? factura.getFechaCompra().format(FECHA_FORMATO) : "-";
            filas.add(new Object[]{
                no++,
                factura.getNoFactura(),
                fecha,
                factura.getMetodoPago(),
                factura.getTotal()
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) vista.tablaCompras.getModel(), filas);
    }

    private void ver(ComprasVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaCompras);
        if (fila >= 0 && fila < facturasCache.size()) {
            mostrarFactura(vista, facturasCache.get(fila));
        }
    }

    private void mostrarFactura(Component parent, FacturaCompraEntity factura) {
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(parent);
        VerFacturaCompraDialog dialog = new VerFacturaCompraDialog(owner, factura.getNoFactura());
        configurarDialogoFactura(dialog, factura, listarDetalleFactura(factura.getNoFactura()));
        dialog.botonCerrar.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private void configurarDialogoFactura(VerFacturaCompraDialog dialog, FacturaCompraEntity factura,
            List<ComprasDetalleEntity> lineas) {
        dialog.etiquetaMetodoValor.setText(factura.getMetodoPago() != null ? factura.getMetodoPago() : "-");
        String fechaTexto = factura.getFechaCompra() != null
                ? factura.getFechaCompra().format(FECHA_FORMATO) : "-";
        dialog.etiquetaFechaValor.setText(fechaTexto);

        DefaultTableModel modelo = TablaCrudHelper.crearModeloNoEditable(
                new String[]{"No", "Producto", "Cant.", "Precio", "Subtotal", "ISV", "Total"});
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (ComprasDetalleEntity linea : lineas) {
            filas.add(new Object[]{
                no++,
                linea.getNombreProducto(),
                linea.getCantidadProducto(),
                linea.getPrecioUnitario(),
                linea.getTotalAntesImpuesto(),
                linea.getImpuesto(),
                linea.getTotalAPagar()
            });
        }
        TablaCrudHelper.limpiarYLLenar(modelo, filas);
        dialog.tablaDetalle.setModel(modelo);

        dialog.etiquetaSubtotal.setText(formatearMonto(factura.getSubtotal()));
        dialog.etiquetaIsv.setText(formatearMonto(factura.getImpuesto()));
        dialog.etiquetaTotal.setText(formatearMonto(factura.getTotal()));
    }

    private String formatearMonto(BigDecimal monto) {
        return monto != null ? monto.toPlainString() : "0.00";
    }

    private void eliminar(ComprasVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaCompras);
        if (fila >= 0 && fila < facturasCache.size()) {
            int respuesta = JOptionPane.showConfirmDialog(vista,
                    "Desea eliminar la factura seleccionada y todos sus registros?",
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                String noFactura = facturasCache.get(fila).getNoFactura();
                ResultadoPersistencia resultado = eliminarFactura(noFactura);
                if (resultado.isExito()) {
                    refrescarTabla(vista);
                } else {
                    JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
