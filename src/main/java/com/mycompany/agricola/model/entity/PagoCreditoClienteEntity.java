package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoCreditoClienteEntity {

    private int idPagoCredito;
    private int idForaneaCliente;
    private LocalDateTime fechaPago;
    private BigDecimal cantidad;

    public int getIdPagoCredito() { return idPagoCredito; }
    public void setIdPagoCredito(int idPagoCredito) { this.idPagoCredito = idPagoCredito; }
    public int getIdForaneaCliente() { return idForaneaCliente; }
    public void setIdForaneaCliente(int idForaneaCliente) { this.idForaneaCliente = idForaneaCliente; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
}
