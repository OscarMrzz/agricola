package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaEntity {

    private int idVenta;
    private int idForaneaVendedor;
    private int idForaneaProducto;
    private String noFactura;
    private LocalDateTime fechaVenta;
    private int idCliente;
    private int cantidadProducto;
    private String tipo;
    private String metodoPago;
    private BigDecimal precioAntesImpuesto;
    private BigDecimal impuesto;
    private BigDecimal totalPagar;

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdForaneaVendedor() { return idForaneaVendedor; }
    public void setIdForaneaVendedor(int idForaneaVendedor) { this.idForaneaVendedor = idForaneaVendedor; }
    public int getIdForaneaProducto() { return idForaneaProducto; }
    public void setIdForaneaProducto(int idForaneaProducto) { this.idForaneaProducto = idForaneaProducto; }
    public String getNoFactura() { return noFactura; }
    public void setNoFactura(String noFactura) { this.noFactura = noFactura; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getCantidadProducto() { return cantidadProducto; }
    public void setCantidadProducto(int cantidadProducto) { this.cantidadProducto = cantidadProducto; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public BigDecimal getPrecioAntesImpuesto() { return precioAntesImpuesto; }
    public void setPrecioAntesImpuesto(BigDecimal precioAntesImpuesto) { this.precioAntesImpuesto = precioAntesImpuesto; }
    public BigDecimal getImpuesto() { return impuesto; }
    public void setImpuesto(BigDecimal impuesto) { this.impuesto = impuesto; }
    public BigDecimal getTotalPagar() { return totalPagar; }
    public void setTotalPagar(BigDecimal totalPagar) { this.totalPagar = totalPagar; }
}
