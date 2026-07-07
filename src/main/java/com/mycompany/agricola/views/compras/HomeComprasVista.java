package com.mycompany.agricola.views.compras;

import com.mycompany.agricola.controllers.compras.HomeComprasController;

public class HomeComprasVista extends javax.swing.JPanel {

    private final HomeComprasController controller = new HomeComprasController();

    public HomeComprasVista() {
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        btnCompras.addActionListener(e -> controller.abrirCompras(this));
        btnNuevaCompra.addActionListener(e -> controller.abrirNuevaCompra(this));
        btnInventario.addActionListener(e -> controller.abrirInventario(this));
        btnAlertar.addActionListener(e -> controller.abrirAlertas(this));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnCompras = new javax.swing.JButton();
        btnNuevaCompra = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnAlertar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Menu Compras");

        btnCompras.setText("Compras");

        btnNuevaCompra.setText("Nueva Compra");

        btnInventario.setText("Inventario");

        btnAlertar.setText("Alertar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCompras)
                        .addGap(8, 8, 8)
                        .addComponent(btnNuevaCompra)
                        .addGap(8, 8, 8)
                        .addComponent(btnInventario)
                        .addGap(8, 8, 8)
                        .addComponent(btnAlertar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompras)
                    .addComponent(btnNuevaCompra)
                    .addComponent(btnInventario)
                    .addComponent(btnAlertar))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnNuevaCompra;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
