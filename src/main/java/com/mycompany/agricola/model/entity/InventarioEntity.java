package com.mycompany.agricola.model.entity;

import java.time.LocalDateTime;

public class InventarioEntity {

    private int idInventario;
    private int idProducto;
    private LocalDateTime fechaUltimaEntrada;
    private LocalDateTime fechaUltimaSalida;
    private int stock;
    private int stockMinimo;

    public int getIdInventario() { return idInventario; }
    public void setIdInventario(int idInventario) { this.idInventario = idInventario; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public LocalDateTime getFechaUltimaEntrada() { return fechaUltimaEntrada; }
    public void setFechaUltimaEntrada(LocalDateTime fechaUltimaEntrada) { this.fechaUltimaEntrada = fechaUltimaEntrada; }
    public LocalDateTime getFechaUltimaSalida() { return fechaUltimaSalida; }
    public void setFechaUltimaSalida(LocalDateTime fechaUltimaSalida) { this.fechaUltimaSalida = fechaUltimaSalida; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
}
