package com.mycompany.agricola.views.compras;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class ComprasVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAgregar;
    public javax.swing.JButton botonEliminar;
    public javax.swing.JButton botonRefrescar;
    public javax.swing.JButton botonVer;
    public javax.swing.JButton botonVolver;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tablaCompras;

    public ComprasVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        botonRefrescar = UiStyle.crearBotonRefrescar();
        botonVer = new javax.swing.JButton("Ver");
        UiStyle.estilizarTabla(tablaCompras);
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
        tablaCompras = new javax.swing.JTable();

        etiquetaTitulo.setText("Listado de compras");
        botonAgregar.setText("Agregar");
        botonEliminar.setText("Eliminar");
        botonVolver.setText("Volver");

        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "No. Factura", "Fecha", "Metodo", "Total"}
        ));
        scrollTabla.setViewportView(tablaCompras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }
}
