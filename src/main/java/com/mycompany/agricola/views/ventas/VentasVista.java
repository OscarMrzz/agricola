package com.mycompany.agricola.views.ventas;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class VentasVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAgregar;
    public javax.swing.JButton botonEliminar;
    public javax.swing.JButton botonRefrescar;
    public javax.swing.JButton botonVer;
    public javax.swing.JButton botonVolver;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tablaVentas;

    public VentasVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        botonRefrescar = UiStyle.crearBotonRefrescar();
        botonVer = new javax.swing.JButton("Ver");
        UiStyle.estilizarTabla(tablaVentas);
        UiStyle.estilizarBotonNav(botonAgregar);
        UiStyle.estilizarBotonNav(botonVer);
        UiStyle.estilizarBotonNav(botonEliminar);
        UiStyle.estilizarBotonNav(botonVolver);
        UiStyle.conIcono(botonAgregar, UiIcons.ADD);
        UiStyle.conIcono(botonVer, UiIcons.VIEW);
        UiStyle.conIcono(botonEliminar, UiIcons.DELETE);
        UiStyle.conIcono(botonVolver, UiIcons.EXIT);
        UiStyle.aplicarLayoutLista(this, etiquetaTitulo, scrollTabla,
                botonAgregar, botonVer, botonEliminar, botonRefrescar, botonVolver);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        botonAgregar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();

        etiquetaTitulo.setText("Listado de ventas");
        botonAgregar.setText("Agregar");
        botonEliminar.setText("Eliminar");
        botonVolver.setText("Volver");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "No. Factura", "Fecha", "Cliente", "Total"}
        ));
        scrollTabla.setViewportView(tablaVentas);
    }
}
