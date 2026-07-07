package com.mycompany.agricola.model.entity;

public class PermisoAccionEntity {

    private int idPermisoAccion;
    private int idForaneaRol;
    private String tabla;
    private String accion;
    private boolean estado;

    public int getIdPermisoAccion() { return idPermisoAccion; }
    public void setIdPermisoAccion(int idPermisoAccion) { this.idPermisoAccion = idPermisoAccion; }
    public int getIdForaneaRol() { return idForaneaRol; }
    public void setIdForaneaRol(int idForaneaRol) { this.idForaneaRol = idForaneaRol; }
    public String getTabla() { return tabla; }
    public void setTabla(String tabla) { this.tabla = tabla; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
