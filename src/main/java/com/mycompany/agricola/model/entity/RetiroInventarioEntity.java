package com.mycompany.agricola.model.entity;

import java.time.LocalDateTime;

public class RetiroInventarioEntity {

    private int idRetiro;
    private int idProducto;
    private Integer idLote;
    private int cantidad;
    private String motivo;
    private LocalDateTime fechaRetiro;
    private int idUsuario;

    public int getIdRetiro() { return idRetiro; }
    public void setIdRetiro(int idRetiro) { this.idRetiro = idRetiro; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public Integer getIdLote() { return idLote; }
    public void setIdLote(Integer idLote) { this.idLote = idLote; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public LocalDateTime getFechaRetiro() { return fechaRetiro; }
    public void setFechaRetiro(LocalDateTime fechaRetiro) { this.fechaRetiro = fechaRetiro; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}
