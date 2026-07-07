package com.mycompany.agricola.model.entity;

import java.time.LocalDateTime;

public class InventarioConfigEntity {

    private int idProducto;
    private int stockMinimo;
    private LocalDateTime fechaUltimaEntrada;
    private LocalDateTime fechaUltimaSalida;

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public LocalDateTime getFechaUltimaEntrada() { return fechaUltimaEntrada; }
    public void setFechaUltimaEntrada(LocalDateTime fechaUltimaEntrada) { this.fechaUltimaEntrada = fechaUltimaEntrada; }
    public LocalDateTime getFechaUltimaSalida() { return fechaUltimaSalida; }
    public void setFechaUltimaSalida(LocalDateTime fechaUltimaSalida) { this.fechaUltimaSalida = fechaUltimaSalida; }
}
