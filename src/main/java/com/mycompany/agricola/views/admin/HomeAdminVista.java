package com.mycompany.agricola.views.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class HomeAdminVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAlertar;
    public javax.swing.JButton botonClientes;
    public javax.swing.JButton botonCompras;
    public javax.swing.JButton botonInventario;
    public javax.swing.JButton botonProductos;
    public javax.swing.JButton botonUsuarios;
    public javax.swing.JButton botonVentas;
    public javax.swing.JLabel etiquetaTitulo;

    public HomeAdminVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(etiquetaTitulo);
        etiquetaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estilizarTile(botonVentas, UiIcons.SALE);
        estilizarTile(botonCompras, UiIcons.PURCHASE);
        estilizarTile(botonUsuarios, UiIcons.USERS);
        estilizarTile(botonClientes, UiIcons.CLIENT);
        estilizarTile(botonProductos, UiIcons.PRODUCTS);
        estilizarTile(botonInventario, UiIcons.INVENTORY);
        estilizarTile(botonAlertar, UiIcons.ALERT);
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
        cuadricula.add(botonVentas);
        cuadricula.add(botonCompras);
        cuadricula.add(botonUsuarios);
        cuadricula.add(botonClientes);
        cuadricula.add(botonProductos);
        cuadricula.add(botonInventario);
        cuadricula.add(celdaVacia());
        cuadricula.add(botonAlertar);
        cuadricula.add(celdaVacia());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        centro.add(etiquetaTitulo, gbc);

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

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        botonVentas = new javax.swing.JButton();
        botonCompras = new javax.swing.JButton();
        botonUsuarios = new javax.swing.JButton();
        botonClientes = new javax.swing.JButton();
        botonProductos = new javax.swing.JButton();
        botonInventario = new javax.swing.JButton();
        botonAlertar = new javax.swing.JButton();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Panel de administracion");

        botonVentas.setText("Venta");
        botonCompras.setText("Compras");
        botonUsuarios.setText("Usuarios");
        botonClientes.setText("Clientes");
        botonProductos.setText("Productos");
        botonInventario.setText("Inventario");
        botonAlertar.setText("Alertar");
    }
}
