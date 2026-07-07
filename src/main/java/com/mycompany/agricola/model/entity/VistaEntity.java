package com.mycompany.agricola.model.entity;

public class VistaEntity {

    private int idVista;
    private String nombreVista;

    public int getIdVista() { return idVista; }
    public void setIdVista(int idVista) { this.idVista = idVista; }
    public String getNombreVista() { return nombreVista; }
    public void setNombreVista(String nombreVista) { this.nombreVista = nombreVista; }

    @Override
    public String toString() { return nombreVista; }
}
