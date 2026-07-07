package com.mycompany.agricola.controllers.ventas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mycompany.agricola.config.AppConfig;
import com.mycompany.agricola.controllers.TotalesLinea;
import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.dao.implement.VentaDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CarritoVentaEntity;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;
import com.mycompany.agricola.services.CreditoExcedidoException;
import com.mycompany.agricola.services.CreditosClientesService;
import com.mycompany.agricola.services.InventarioLoteService;
import com.mycompany.agricola.services.ProductosAptosParaVenderService;

public class FormularioAgregarVentaController {

    private static final BigDecimal ISV = BigDecimal.valueOf(AppConfig.ISV_PORCENTAJE);

    private final VentaDaoApl ventaDao = new VentaDaoApl();
    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();
    private final InventarioLoteService inventarioLoteService = new InventarioLoteService();
    private final ProductosAptosParaVenderService productosAptosService = new ProductosAptosParaVenderService();
    private final CreditosClientesService creditosService = new CreditosClientesService();
    private final List<CarritoVentaEntity> carrito = new ArrayList<>();
    private ClienteEntity clienteFactura;
    private String noFactura;

    public FormularioAgregarVentaController() {
        this.noFactura = generarNoFactura();
    }

    public List<ProductoEntity> listarProductosAptos() {
        return productosAptosService.obtenerProductosAptos();
    }

    public List<CarritoVentaEntity> getCarrito() {
        return carrito;
    }

    public String getNoFactura() {
        return noFactura;
    }

    public ClienteEntity getClienteFactura() {
        return clienteFactura;
    }

    public boolean isClienteBloqueado() {
        return !carrito.isEmpty();
    }

    public TotalesLinea calcularTotalesLinea(ProductoEntity producto, int cantidad) {
        if (producto == null || producto.getPrecioVenta() == null || cantidad <= 0) {
            return new TotalesLinea(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        BigDecimal precioUnitario = producto.getPrecioVenta();
        BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal impuesto = subtotal.multiply(ISV).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(impuesto);
        return new TotalesLinea(precioUnitario, subtotal, impuesto, total);
    }

    public void agregarLinea(ProductoEntity producto, int cantidad, ClienteEntity cliente, String metodoPago) {
        if (producto == null) {
            throw new IllegalArgumentException("Debe seleccionar un producto");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("Debe seleccionar un cliente");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (producto.getPrecioVenta() == null) {
            throw new IllegalArgumentException("El producto no tiene precio de venta");
        }
        if (clienteFactura == null) {
            clienteFactura = cliente;
        } else if (clienteFactura.getIdCliente() != cliente.getIdCliente()) {
            throw new IllegalArgumentException(
                    "La factura solo puede tener un cliente. Elimine el carrito para cambiar de cliente.");
        }

        InventarioEntity inventario = inventarioDao.getByProductoId(producto.getIdProducto());
        int stockVendible = inventario != null ? inventario.getStockVendible() : 0;
        if (cantidad > stockVendible) {
            int vencidos = inventario != null ? inventario.getCantidadVencida() : 0;
            if (vencidos > 0) {
                throw new IllegalArgumentException(
                        vencidos + " productos en el almacen estan vencidos. Solo puedes vender " + stockVendible + ".");
            }
            throw new IllegalArgumentException("Stock insuficiente para " + producto.getNombreProducto());
        }

        BigDecimal precioUnitario = producto.getPrecioVenta();
        BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal impuesto = subtotal.multiply(ISV).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(impuesto);

        CarritoVentaEntity linea = new CarritoVentaEntity();
        linea.setNoFactura(noFactura);
        linea.setCliente(cliente.toString());
        linea.setFechaVenta(LocalDateTime.now());
        linea.setIdProducto(producto.getIdProducto());
        linea.setNombreProducto(producto.getNombreProducto());
        linea.setCantidadProducto(cantidad);
        linea.setMetodoPago(metodoPago);
        linea.setPrecioUnitario(precioUnitario);
        linea.setSubtotal(subtotal);
        linea.setImpuesto(impuesto);
        linea.setTotalAPagar(total);
        carrito.add(linea);
    }

    public BigDecimal calcularTotalFactura() {
        BigDecimal total = BigDecimal.ZERO;
        for (CarritoVentaEntity linea : carrito) {
            total = total.add(linea.getTotalAPagar());
        }
        return total;
    }

    public BigDecimal calcularSubtotalFactura() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (CarritoVentaEntity linea : carrito) {
            subtotal = subtotal.add(linea.getSubtotal());
        }
        return subtotal;
    }

    public BigDecimal calcularIsvFactura() {
        BigDecimal isv = BigDecimal.ZERO;
        for (CarritoVentaEntity linea : carrito) {
            isv = isv.add(linea.getImpuesto());
        }
        return isv;
    }

    public void eliminarLinea(int indice) {
        if (indice >= 0 && indice < carrito.size()) {
            carrito.remove(indice);
            if (carrito.isEmpty()) {
                clienteFactura = null;
            }
        }
    }

    public ResultadoPersistencia guardarVenta(ClienteEntity cliente, int idVendedor, String metodoPago)
            throws CreditoExcedidoException {
        if (clienteFactura != null) {
            cliente = clienteFactura;
        }
        if (cliente == null) {
            return ResultadoPersistencia.error(new IllegalStateException("Cliente no seleccionado"), "registrar la venta");
        }
        if (carrito.isEmpty()) {
            return ResultadoPersistencia.error(new IllegalStateException("Carrito vacio"), "registrar la venta");
        }

        BigDecimal totalFactura = calcularTotalFactura();
        if ("credito".equalsIgnoreCase(metodoPago)) {
            creditosService.validarLimiteCredito(cliente.getIdCliente(), totalFactura);
        }

        for (CarritoVentaEntity linea : carrito) {
            int cantidad = linea.getCantidadProducto();
            BigDecimal precioUnitario = linea.getPrecioUnitario();
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
            BigDecimal impuesto = subtotal.multiply(ISV).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalLinea = subtotal.add(impuesto);

            VentaEntity venta = new VentaEntity();
            venta.setNoFactura(noFactura);
            venta.setIdCliente(cliente.getIdCliente());
            venta.setIdForaneaVendedor(idVendedor);
            venta.setIdForaneaProducto(linea.getIdProducto());
            venta.setCantidadProducto(cantidad);
            venta.setTipo(metodoPago);
            venta.setMetodoPago(metodoPago);
            venta.setPrecioAntesImpuesto(precioUnitario);
            venta.setImpuesto(impuesto);
            venta.setTotalPagar(totalLinea);
            venta.setFechaVenta(LocalDateTime.now());

            ResultadoPersistencia resultado = ventaDao.create(venta);
            if (!resultado.isExito()) {
                return resultado;
            }

            ResultadoPersistencia descuento = inventarioLoteService.descontarParaVenta(
                    linea.getIdProducto(), cantidad);
            if (!descuento.isExito()) {
                return descuento;
            }
        }

        carrito.clear();
        clienteFactura = null;
        noFactura = generarNoFactura();
        return ResultadoPersistencia.exito();
    }

    public List<VentasDetalleEntity> listarVentasDetalle() {
        return ventaDao.getAllDetalle();
    }

    private String generarNoFactura() {
        return "FAC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
