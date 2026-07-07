package com.mycompany.agricola.model.entity;

public class RolEntity {

    private int idRol;
    private String nombreRol;
    private boolean estadoRol;

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }
    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }
    public boolean isEstadoRol() { return estadoRol; }
    public void setEstadoRol(boolean estadoRol) { this.estadoRol = estadoRol; }

    @Override
    public String toString() { return nombreRol; }
}
