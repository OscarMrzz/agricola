package com.mycompany.agricola.views.compras;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.compras.InventarioComprasController;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class InventarioComprasVista extends javax.swing.JPanel {

    private final InventarioComprasController controller = new InventarioComprasController();
    private DefaultTableModel modelo;
    private javax.swing.JButton btnRefrescar;

    public InventarioComprasVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        btnRefrescar = UiStyle.crearBotonRefrescar();
        UiStyle.estilizarTabla(tablaInventario);
        UiStyle.estilizarBotonNav(btnVolver);
        UiStyle.conIcono(btnVolver, UiIcons.EXIT);
        UiStyle.aplicarLayoutLista(this, lblTitulo, scrollTabla, btnRefrescar, btnVolver);
    }

    private void inicializarLogica() {
        modelo = new DefaultTableModel(
                new String[]{"No", "Producto", "Stock", "Minimo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaInventario.setModel(modelo);
        cargarDatos();
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        int no = 1;
        for (InventarioEntity inv : controller.listarInventario()) {
            modelo.addRow(new Object[]{
                no++,
                controller.obtenerNombreProducto(inv.getIdProducto()),
                inv.getStock(),
                inv.getStockMinimo()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Inventario");

        btnVolver.setText("Volver");

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock", "Minimo"}
        ));
        scrollTabla.setViewportView(tablaInventario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(btnVolver)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addGap(18, 18, 18)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables
}
