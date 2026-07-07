package com.mycompany.agricola.views.compras;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class HomeComprasVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAlertar;
    public javax.swing.JButton botonCompras;
    public javax.swing.JButton botonInventario;
    public javax.swing.JButton botonNuevaCompra;
    public javax.swing.JLabel etiquetaTitulo;

    public HomeComprasVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(etiquetaTitulo);
        etiquetaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estilizarTile(botonCompras, UiIcons.PURCHASE);
        estilizarTile(botonNuevaCompra, UiIcons.ADD);
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

        JPanel cuadricula = new JPanel();
        cuadricula.setLayout(new BoxLayout(cuadricula, BoxLayout.Y_AXIS));
        cuadricula.setOpaque(false);

        int anchoFila = 3 * UiTheme.BTN_TILE.width + 2 * UiTheme.SPACE_MD;

        JPanel filaSuperior = new JPanel(new GridLayout(1, 3, UiTheme.SPACE_MD, 0));
        filaSuperior.setOpaque(false);
        filaSuperior.setAlignmentX(CENTER_ALIGNMENT);
        filaSuperior.setMaximumSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaSuperior.setPreferredSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaSuperior.add(botonCompras);
        filaSuperior.add(botonNuevaCompra);
        filaSuperior.add(botonInventario);

        JPanel filaInferior = new JPanel(new GridLayout(1, 3, UiTheme.SPACE_MD, 0));
        filaInferior.setOpaque(false);
        filaInferior.setAlignmentX(CENTER_ALIGNMENT);
        filaInferior.setMaximumSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaInferior.setPreferredSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaInferior.add(celdaVacia());
        filaInferior.add(botonAlertar);
        filaInferior.add(celdaVacia());

        cuadricula.add(filaSuperior);
        cuadricula.add(Box.createVerticalStrut(UiTheme.SPACE_MD));
        cuadricula.add(filaInferior);

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
        botonCompras = new javax.swing.JButton();
        botonNuevaCompra = new javax.swing.JButton();
        botonInventario = new javax.swing.JButton();
        botonAlertar = new javax.swing.JButton();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Menu Compras");

        botonCompras.setText("Compras");
        botonNuevaCompra.setText("Nueva Compra");
        botonInventario.setText("Inventario");
        botonAlertar.setText("Alertar");
    }
}
