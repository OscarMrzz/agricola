package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;

public class CreditosClientesDetalleEntity {

    private int idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private BigDecimal creditoMaximo;
    private BigDecimal creditoActual;
    private BigDecimal diferencia;

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getApellidoCliente() { return apellidoCliente; }
    public void setApellidoCliente(String apellidoCliente) { this.apellidoCliente = apellidoCliente; }
    public BigDecimal getCreditoMaximo() { return creditoMaximo; }
    public void setCreditoMaximo(BigDecimal creditoMaximo) { this.creditoMaximo = creditoMaximo; }
    public BigDecimal getCreditoActual() { return creditoActual; }
    public void setCreditoActual(BigDecimal creditoActual) { this.creditoActual = creditoActual; }
    public BigDecimal getDiferencia() { return diferencia; }
    public void setDiferencia(BigDecimal diferencia) { this.diferencia = diferencia; }
}
