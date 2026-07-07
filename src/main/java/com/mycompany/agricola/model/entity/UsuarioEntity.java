package com.mycompany.agricola.model.entity;

public class UsuarioEntity {

    private int idUser;
    private String nombreUser;
    private String passwordUser;
    private int idForaneaRol;
    private boolean estado;

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    public String getNombreUser() { return nombreUser; }
    public void setNombreUser(String nombreUser) { this.nombreUser = nombreUser; }
    public String getPasswordUser() { return passwordUser; }
    public void setPasswordUser(String passwordUser) { this.passwordUser = passwordUser; }
    public int getIdForaneaRol() { return idForaneaRol; }
    public void setIdForaneaRol(int idForaneaRol) { this.idForaneaRol = idForaneaRol; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    @Override
    public String toString() { return nombreUser; }
}
