package com.mycompany.agricola.controllers.compras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mycompany.agricola.config.AppConfig;
import com.mycompany.agricola.controllers.TotalesLinea;
import com.mycompany.agricola.model.dao.implement.CompraDaoApl;
import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CarritoCompraEntity;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class FormularioAgregarCompraController {

    private final CompraDaoApl compraDao = new CompraDaoApl();
    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();
    private final ProductoDaoApl productoDao = new ProductoDaoApl();
    private final List<LineaPendiente> lineasPendientes = new ArrayList<>();
    private String noFactura = generarNoFactura();

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
                .setScale(2, RoundingMode.HALF_UP);
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

        BigDecimal subtotal = precioCompra.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal impuesto = subtotal.multiply(BigDecimal.valueOf(AppConfig.ISV_PORCENTAJE))
                .setScale(2, RoundingMode.HALF_UP);
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

            ResultadoPersistencia resultado = compraDao.create(compra);
            if (!resultado.isExito()) {
                return resultado;
            }

            InventarioEntity inventario = inventarioDao.getByProductoId(linea.idProducto);
            if (inventario != null) {
                inventario.setStock(inventario.getStock() + linea.cantidad);
                inventario.setFechaUltimaEntrada(LocalDateTime.now());
                inventarioDao.update(inventario);
            }
        }

        lineasPendientes.clear();
        noFactura = generarNoFactura();
        return ResultadoPersistencia.exito();
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
