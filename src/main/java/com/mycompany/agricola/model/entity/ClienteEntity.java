package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;

public class ClienteEntity {

    private int idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private boolean estado;
    private BigDecimal limiteCredito;

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getApellidoCliente() { return apellidoCliente; }
    public void setApellidoCliente(String apellidoCliente) { this.apellidoCliente = apellidoCliente; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
    public BigDecimal getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(BigDecimal limiteCredito) { this.limiteCredito = limiteCredito; }

    @Override
    public String toString() {
        return nombreCliente + " " + apellidoCliente;
    }
}
