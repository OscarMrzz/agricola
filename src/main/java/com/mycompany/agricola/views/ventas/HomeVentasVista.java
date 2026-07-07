package com.mycompany.agricola.views.ventas;

import com.mycompany.agricola.controllers.ventas.HomeVentasController;

public class HomeVentasVista extends javax.swing.JPanel {

    private final HomeVentasController controller = new HomeVentasController();

    public HomeVentasVista() {
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        btnVentas.addActionListener(e -> controller.abrirVentas(this));
        btnNuevaVenta.addActionListener(e -> controller.abrirNuevaVenta(this));
        btnClientes.addActionListener(e -> controller.abrirClientesCreditos(this));
        btnInventario.addActionListener(e -> controller.abrirInventario(this));
        btnFactura.addActionListener(e -> controller.abrirFactura(this));
        btnAlertar.addActionListener(e -> controller.abrirAlertas(this));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnVentas = new javax.swing.JButton();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnFactura = new javax.swing.JButton();
        btnAlertar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Menu Ventas");

        btnVentas.setText("Ventas");

        btnNuevaVenta.setText("Nueva Venta");

        btnClientes.setText("Clientes Creditos");

        btnInventario.setText("Inventario");

        btnFactura.setText("Factura");

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
                        .addComponent(btnVentas)
                        .addGap(8, 8, 8)
                        .addComponent(btnNuevaVenta)
                        .addGap(8, 8, 8)
                        .addComponent(btnClientes)
                        .addGap(8, 8, 8)
                        .addComponent(btnInventario)
                        .addGap(8, 8, 8)
                        .addComponent(btnFactura)
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
                    .addComponent(btnVentas)
                    .addComponent(btnNuevaVenta)
                    .addComponent(btnClientes)
                    .addComponent(btnInventario)
                    .addComponent(btnFactura)
                    .addComponent(btnAlertar))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnFactura;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
