package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoEntity {

    private int idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private String departamentoOrigen;
    private BigDecimal precioVenta;
    private LocalDateTime fechaVencimiento;

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public String getCategoriaProducto() { return categoriaProducto; }
    public void setCategoriaProducto(String categoriaProducto) { this.categoriaProducto = categoriaProducto; }
    public String getDepartamentoOrigen() { return departamentoOrigen; }
    public void setDepartamentoOrigen(String departamentoOrigen) { this.departamentoOrigen = departamentoOrigen; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public LocalDateTime getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDateTime fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    @Override
    public String toString() { return nombreProducto; }
}
