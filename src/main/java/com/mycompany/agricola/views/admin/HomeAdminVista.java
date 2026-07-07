package com.mycompany.agricola.views.admin;

import com.mycompany.agricola.controllers.admin.HomeAdminController;

public class HomeAdminVista extends javax.swing.JPanel {

    private final HomeAdminController controller = new HomeAdminController();

    public HomeAdminVista() {
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        btnListadoVentas.addActionListener(e -> controller.abrirListadoVentas(this));
        btnNuevaVenta.addActionListener(e -> controller.abrirNuevaVenta(this));
        btnClientesCreditos.addActionListener(e -> controller.abrirClientesCreditos(this));
        btnInventarioVentas.addActionListener(e -> controller.abrirInventarioVentas(this));
        btnFactura.addActionListener(e -> controller.abrirFactura(this));

        btnListadoCompras.addActionListener(e -> controller.abrirListadoCompras(this));
        btnNuevaCompra.addActionListener(e -> controller.abrirNuevaCompra(this));
        btnInventarioCompras.addActionListener(e -> controller.abrirInventarioCompras(this));

        btnUsuarios.addActionListener(e -> controller.abrirUsuarios(this));
        btnClientes.addActionListener(e -> controller.abrirClientes(this));
        btnProductos.addActionListener(e -> controller.abrirProductos(this));
        btnInventario.addActionListener(e -> controller.abrirInventario(this));
        btnAlertar.addActionListener(e -> controller.abrirAlertas(this));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblSeccionVentas = new javax.swing.JLabel();
        btnListadoVentas = new javax.swing.JButton();
        btnNuevaVenta = new javax.swing.JButton();
        btnClientesCreditos = new javax.swing.JButton();
        btnInventarioVentas = new javax.swing.JButton();
        btnFactura = new javax.swing.JButton();
        lblSeccionCompras = new javax.swing.JLabel();
        btnListadoCompras = new javax.swing.JButton();
        btnNuevaCompra = new javax.swing.JButton();
        btnInventarioCompras = new javax.swing.JButton();
        lblSeccionAdmin = new javax.swing.JLabel();
        btnUsuarios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnAlertar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Panel de administracion");

        lblSeccionVentas.setFont(new java.awt.Font("Arial", 1, 12));
        lblSeccionVentas.setText("Ventas");

        btnListadoVentas.setText("Listado Ventas");
        btnNuevaVenta.setText("Nueva Venta");
        btnClientesCreditos.setText("Clientes Creditos");
        btnInventarioVentas.setText("Inventario Ventas");
        btnFactura.setText("Factura");

        lblSeccionCompras.setFont(new java.awt.Font("Arial", 1, 12));
        lblSeccionCompras.setText("Compras");

        btnListadoCompras.setText("Listado Compras");
        btnNuevaCompra.setText("Nueva Compra");
        btnInventarioCompras.setText("Inventario Compras");

        lblSeccionAdmin.setFont(new java.awt.Font("Arial", 1, 12));
        lblSeccionAdmin.setText("Administracion");

        btnUsuarios.setText("Usuarios");
        btnClientes.setText("Clientes");
        btnProductos.setText("Productos");
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
                    .addComponent(lblSeccionVentas)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnListadoVentas)
                        .addGap(8, 8, 8)
                        .addComponent(btnNuevaVenta)
                        .addGap(8, 8, 8)
                        .addComponent(btnClientesCreditos)
                        .addGap(8, 8, 8)
                        .addComponent(btnInventarioVentas)
                        .addGap(8, 8, 8)
                        .addComponent(btnFactura))
                    .addComponent(lblSeccionCompras)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnListadoCompras)
                        .addGap(8, 8, 8)
                        .addComponent(btnNuevaCompra)
                        .addGap(8, 8, 8)
                        .addComponent(btnInventarioCompras))
                    .addComponent(lblSeccionAdmin)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuarios)
                        .addGap(8, 8, 8)
                        .addComponent(btnClientes)
                        .addGap(8, 8, 8)
                        .addComponent(btnProductos)
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
                .addComponent(lblSeccionVentas)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListadoVentas)
                    .addComponent(btnNuevaVenta)
                    .addComponent(btnClientesCreditos)
                    .addComponent(btnInventarioVentas)
                    .addComponent(btnFactura))
                .addGap(18, 18, 18)
                .addComponent(lblSeccionCompras)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListadoCompras)
                    .addComponent(btnNuevaCompra)
                    .addComponent(btnInventarioCompras))
                .addGap(18, 18, 18)
                .addComponent(lblSeccionAdmin)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuarios)
                    .addComponent(btnClientes)
                    .addComponent(btnProductos)
                    .addComponent(btnInventario)
                    .addComponent(btnAlertar))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnClientesCreditos;
    private javax.swing.JButton btnFactura;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnInventarioCompras;
    private javax.swing.JButton btnInventarioVentas;
    private javax.swing.JButton btnListadoCompras;
    private javax.swing.JButton btnListadoVentas;
    private javax.swing.JButton btnNuevaCompra;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel lblSeccionAdmin;
    private javax.swing.JLabel lblSeccionCompras;
    private javax.swing.JLabel lblSeccionVentas;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
