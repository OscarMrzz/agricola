package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraEntity {

    private int idCompra;
    private int idForaneaProducto;
    private int idForaneaUsuario;
    private int cantidadCompra;
    private BigDecimal precioCompra;
    private LocalDateTime fechaExpiracion;
    private LocalDateTime fechaCompra;
    private String noFactura;
    private String metodoPago;

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }
    public int getIdForaneaProducto() { return idForaneaProducto; }
    public void setIdForaneaProducto(int idForaneaProducto) { this.idForaneaProducto = idForaneaProducto; }
    public int getIdForaneaUsuario() { return idForaneaUsuario; }
    public void setIdForaneaUsuario(int idForaneaUsuario) { this.idForaneaUsuario = idForaneaUsuario; }
    public int getCantidadCompra() { return cantidadCompra; }
    public void setCantidadCompra(int cantidadCompra) { this.cantidadCompra = cantidadCompra; }
    public BigDecimal getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(BigDecimal precioCompra) { this.precioCompra = precioCompra; }
    public LocalDateTime getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(LocalDateTime fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
    public String getNoFactura() { return noFactura; }
    public void setNoFactura(String noFactura) { this.noFactura = noFactura; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}
