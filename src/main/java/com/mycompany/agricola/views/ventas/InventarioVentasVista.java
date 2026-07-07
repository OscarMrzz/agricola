package com.mycompany.agricola.views.ventas;

import com.mycompany.agricola.controllers.inventario.InventarioListadoController;

public class InventarioVentasVista extends InventarioListadoController.InventarioLecturaVista {

    public InventarioVentasVista() {
        initComponents();
        aplicarEstilosLectura();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Inventario");

        botonVolver.setText("Volver");

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock", "Minimo", "Prox. vencimiento", "Por vencer", "Vencido"}
        ));
        scrollTabla.setViewportView(tablaInventario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTitulo)
                    .addComponent(botonVolver)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
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
