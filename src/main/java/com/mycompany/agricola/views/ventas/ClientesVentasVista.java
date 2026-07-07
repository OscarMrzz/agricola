package com.mycompany.agricola.views.ventas;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class ClientesVentasVista extends javax.swing.JPanel {

    public javax.swing.JButton botonRefrescar;
    public javax.swing.JButton botonVolver;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tablaClientes;

    public ClientesVentasVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        botonRefrescar = UiStyle.crearBotonRefrescar();
        UiStyle.estilizarTabla(tablaClientes);
        UiStyle.estilizarBotonNav(botonVolver);
        UiStyle.conIcono(botonVolver, UiIcons.EXIT);
        UiStyle.aplicarLayoutLista(this, etiquetaTitulo, scrollTabla, botonRefrescar, botonVolver);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Clientes Creditos");

        botonVolver.setText("Volver");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Nombre", "Apellido", "Credito max", "Credito actual", "Diferencia"}
        ));
        scrollTabla.setViewportView(tablaClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTitulo)
                    .addComponent(botonVolver)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etiquetaTitulo)
                .addGap(18, 18, 18)
                .addComponent(botonVolver)
                .addGap(18, 18, 18)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }
}
