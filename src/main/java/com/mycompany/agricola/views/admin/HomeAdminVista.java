package com.mycompany.agricola.views.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(lblTitulo);
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estilizarTile(btnVentas, UiIcons.SALE);
        estilizarTile(btnCompras, UiIcons.PURCHASE);
        estilizarTile(btnUsuarios, UiIcons.USERS);
        estilizarTile(btnClientes, UiIcons.CLIENT);
        estilizarTile(btnProductos, UiIcons.PRODUCTS);
        estilizarTile(btnInventario, UiIcons.INVENTORY);
        estilizarTile(btnAlertar, UiIcons.ALERT);
        reorganizarLayout();
    }

    private void estilizarTile(javax.swing.JButton boton, String icono) {
        UiStyle.estilizarBotonTile(boton);
        UiStyle.conIconoTile(boton, icono);
    }

    private void reorganizarLayout() {
        removeAll();
        setLayout(new java.awt.BorderLayout());

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);

        JPanel cuadricula = new JPanel(new GridLayout(3, 3, UiTheme.SPACE_MD, UiTheme.SPACE_MD));
        cuadricula.setOpaque(false);
        cuadricula.add(btnVentas);
        cuadricula.add(btnCompras);
        cuadricula.add(btnUsuarios);
        cuadricula.add(btnClientes);
        cuadricula.add(btnProductos);
        cuadricula.add(btnInventario);
        cuadricula.add(celdaVacia());
        cuadricula.add(btnAlertar);
        cuadricula.add(celdaVacia());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        centro.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(UiTheme.SPACE_XXL, 0, 0, 0);
        centro.add(cuadricula, gbc);

        add(centro, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel celdaVacia() {
        JPanel celda = new JPanel();
        celda.setOpaque(false);
        return celda;
    }

    private void inicializarLogica() {
        btnVentas.addActionListener(e -> controller.abrirListadoVentas(this));
        btnCompras.addActionListener(e -> controller.abrirListadoCompras(this));
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
        btnVentas = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnAlertar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Panel de administracion");

        btnVentas.setText("Venta");
        btnCompras.setText("Compras");
        btnUsuarios.setText("Usuarios");
        btnClientes.setText("Clientes");
        btnProductos.setText("Productos");
        btnInventario.setText("Inventario");
        btnAlertar.setText("Alertar");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
