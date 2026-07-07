package com.mycompany.agricola.views.admin;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.mycompany.agricola.controllers.admin.HomeAdminController;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class HomeAdminVista extends javax.swing.JPanel {

    private final HomeAdminController controller = new HomeAdminController();

    public HomeAdminVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarVistaHome(this, lblTitulo,
                new javax.swing.JLabel[]{lblSeccionVentas, lblSeccionCompras, lblSeccionAdmin},
                btnListadoVentas, btnNuevaVenta, btnFactura,
                btnListadoCompras, btnNuevaCompra,
                btnUsuarios, btnClientes, btnProductos, btnInventario, btnAlertar);
        UiStyle.conIcono(btnListadoVentas, UiIcons.SALE);
        UiStyle.conIcono(btnNuevaVenta, UiIcons.ADD);
        UiStyle.conIcono(btnFactura, UiIcons.PDF);
        UiStyle.conIcono(btnListadoCompras, UiIcons.PURCHASE);
        UiStyle.conIcono(btnNuevaCompra, UiIcons.ADD);
        UiStyle.conIcono(btnUsuarios, UiIcons.USERS);
        UiStyle.conIcono(btnClientes, UiIcons.CLIENT);
        UiStyle.conIcono(btnProductos, UiIcons.PRODUCTS);
        UiStyle.conIcono(btnInventario, UiIcons.INVENTORY);
        UiStyle.conIcono(btnAlertar, UiIcons.ALERT);
        reorganizarLayout();
    }

    private void reorganizarLayout() {
        removeAll();
        setLayout(new java.awt.BorderLayout());
        UiStyle.aplicarPagina(this);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);

        contenido.add(lblTitulo);
        contenido.add(Box.createVerticalStrut(UiTheme.SPACE_LG));

        contenido.add(lblSeccionVentas);
        contenido.add(Box.createVerticalStrut(UiTheme.SPACE_SM));
        contenido.add(filaBotones(btnListadoVentas, btnNuevaVenta, btnFactura));
        contenido.add(Box.createVerticalStrut(UiTheme.SPACE_LG));

        contenido.add(lblSeccionCompras);
        contenido.add(Box.createVerticalStrut(UiTheme.SPACE_SM));
        contenido.add(filaBotones(btnListadoCompras, btnNuevaCompra));
        contenido.add(Box.createVerticalStrut(UiTheme.SPACE_LG));

        contenido.add(lblSeccionAdmin);
        contenido.add(Box.createVerticalStrut(UiTheme.SPACE_SM));
        contenido.add(filaBotones(btnUsuarios, btnClientes, btnProductos, btnInventario, btnAlertar));

        add(contenido, java.awt.BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    private JPanel filaBotones(javax.swing.JButton... botones) {
        JPanel fila = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, UiTheme.SPACE_SM, 0));
        fila.setOpaque(false);
        fila.setAlignmentX(LEFT_ALIGNMENT);
        for (javax.swing.JButton boton : botones) {
            fila.add(boton);
        }
        return fila;
    }

    private void inicializarLogica() {
        btnListadoVentas.addActionListener(e -> controller.abrirListadoVentas(this));
        btnNuevaVenta.addActionListener(e -> controller.abrirNuevaVenta(this));
        btnFactura.addActionListener(e -> controller.abrirFactura(this));

        btnListadoCompras.addActionListener(e -> controller.abrirListadoCompras(this));
        btnNuevaCompra.addActionListener(e -> controller.abrirNuevaCompra(this));

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
        btnFactura = new javax.swing.JButton();
        lblSeccionCompras = new javax.swing.JLabel();
        btnListadoCompras = new javax.swing.JButton();
        btnNuevaCompra = new javax.swing.JButton();
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
        btnFactura.setText("Factura");

        lblSeccionCompras.setFont(new java.awt.Font("Arial", 1, 12));
        lblSeccionCompras.setText("Compras");

        btnListadoCompras.setText("Listado Compras");
        btnNuevaCompra.setText("Nueva Compra");

        lblSeccionAdmin.setFont(new java.awt.Font("Arial", 1, 12));
        lblSeccionAdmin.setText("Administracion");

        btnUsuarios.setText("Usuarios");
        btnClientes.setText("Clientes");
        btnProductos.setText("Productos");
        btnInventario.setText("Inventario");
        btnAlertar.setText("Alertar");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnFactura;
    private javax.swing.JButton btnInventario;
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
