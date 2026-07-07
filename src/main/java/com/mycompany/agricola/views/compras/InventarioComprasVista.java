package com.mycompany.agricola.views.compras;

import com.mycompany.agricola.controllers.inventario.InventarioListadoController;

public class InventarioComprasVista extends InventarioListadoController.InventarioLecturaVista {

    public InventarioComprasVista() {
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
    }
}
