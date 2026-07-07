package com.mycompany.agricola.views.compras;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.mycompany.agricola.controllers.compras.HomeComprasController;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class HomeComprasVista extends javax.swing.JPanel {

    private final HomeComprasController controller = new HomeComprasController();

    public HomeComprasVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(lblTitulo);
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estilizarTile(btnCompras, UiIcons.PURCHASE);
        estilizarTile(btnNuevaCompra, UiIcons.ADD);
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

        JPanel cuadricula = new JPanel();
        cuadricula.setLayout(new BoxLayout(cuadricula, BoxLayout.Y_AXIS));
        cuadricula.setOpaque(false);

        int anchoFila = 3 * UiTheme.BTN_TILE.width + 2 * UiTheme.SPACE_MD;

        JPanel filaSuperior = new JPanel(new GridLayout(1, 3, UiTheme.SPACE_MD, 0));
        filaSuperior.setOpaque(false);
        filaSuperior.setAlignmentX(CENTER_ALIGNMENT);
        filaSuperior.setMaximumSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaSuperior.setPreferredSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaSuperior.add(btnCompras);
        filaSuperior.add(btnNuevaCompra);
        filaSuperior.add(btnInventario);

        JPanel filaInferior = new JPanel(new GridLayout(1, 3, UiTheme.SPACE_MD, 0));
        filaInferior.setOpaque(false);
        filaInferior.setAlignmentX(CENTER_ALIGNMENT);
        filaInferior.setMaximumSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaInferior.setPreferredSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaInferior.add(celdaVacia());
        filaInferior.add(btnAlertar);
        filaInferior.add(celdaVacia());

        cuadricula.add(filaSuperior);
        cuadricula.add(Box.createVerticalStrut(UiTheme.SPACE_MD));
        cuadricula.add(filaInferior);

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
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnNuevaCompra;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
