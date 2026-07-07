package com.mycompany.agricola.model.entity;

import java.time.LocalDateTime;

public class InventarioEntity {

    private int idInventario;
    private int idProducto;
    private LocalDateTime fechaUltimaEntrada;
    private LocalDateTime fechaUltimaSalida;
    private int stock;
    private int stockMinimo;
    private String nombreProducto;
    private LocalDateTime proximoVencimiento;
    private int cantidadPorVencer;
    private int cantidadVencida;
    private int stockVendible;

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
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public LocalDateTime getProximoVencimiento() { return proximoVencimiento; }
    public void setProximoVencimiento(LocalDateTime proximoVencimiento) { this.proximoVencimiento = proximoVencimiento; }
    public int getCantidadPorVencer() { return cantidadPorVencer; }
    public void setCantidadPorVencer(int cantidadPorVencer) { this.cantidadPorVencer = cantidadPorVencer; }
    public int getCantidadVencida() { return cantidadVencida; }
    public void setCantidadVencida(int cantidadVencida) { this.cantidadVencida = cantidadVencida; }
    public int getStockVendible() { return stockVendible; }
    public void setStockVendible(int stockVendible) { this.stockVendible = stockVendible; }
}
