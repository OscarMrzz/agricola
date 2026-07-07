package com.mycompany.agricola.views;

import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.AlertasController;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;

public class AlertasVista extends javax.swing.JPanel {

    private final AlertasController controller = new AlertasController();

    public AlertasVista() {
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        DefaultTableModel modeloVencimiento = new DefaultTableModel(
                new String[]{"No", "Producto", "Fecha vencimiento", "Dias restantes"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int no = 1;
        for (AdvertenciaVencimientoEntity alerta : controller.listarProductosPorVencer()) {
            modeloVencimiento.addRow(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getFechaVencimiento(),
                alerta.getDiasRestantes()
            });
        }
        tablaVencimiento.setModel(modeloVencimiento);

        DefaultTableModel modeloStock = new DefaultTableModel(
                new String[]{"No", "Producto", "Stock actual"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        no = 1;
        for (AdvertenciaStockBajoEntity alerta : controller.listarStockBajo()) {
            modeloStock.addRow(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getStockActual()
            });
        }
        tablaStock.setModel(modeloStock);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblVencimiento = new javax.swing.JLabel();
        scrollVencimiento = new javax.swing.JScrollPane();
        tablaVencimiento = new javax.swing.JTable();
        lblStock = new javax.swing.JLabel();
        scrollStock = new javax.swing.JScrollPane();
        tablaStock = new javax.swing.JTable();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Alertas");

        lblVencimiento.setText("Productos por vencer");

        tablaVencimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Fecha vencimiento", "Dias restantes"}
        ));
        scrollVencimiento.setViewportView(tablaVencimiento);

        lblStock.setText("Stock bajo");

        tablaStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock actual"}
        ));
        scrollStock.setViewportView(tablaStock);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(lblVencimiento)
                    .addComponent(scrollVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addComponent(lblStock)
                    .addComponent(scrollStock, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(lblVencimiento)
                .addGap(8, 8, 8)
                .addComponent(scrollVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblStock)
                .addGap(8, 8, 8)
                .addComponent(scrollStock, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVencimiento;
    private javax.swing.JScrollPane scrollStock;
    private javax.swing.JScrollPane scrollVencimiento;
    private javax.swing.JTable tablaStock;
    private javax.swing.JTable tablaVencimiento;
    // End of variables declaration//GEN-END:variables
}
