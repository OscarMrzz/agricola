package com.mycompany.agricola.views.ventas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.mycompany.agricola.controllers.ventas.HomeVentasController;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class HomeVentasVista extends javax.swing.JPanel {

    private final HomeVentasController controller = new HomeVentasController();

    public HomeVentasVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(lblTitulo);
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estilizarTile(btnVentas, UiIcons.SALE);
        estilizarTile(btnNuevaVenta, UiIcons.ADD);
        estilizarTile(btnClientes, UiIcons.CLIENT);
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
        filaSuperior.add(btnVentas);
        filaSuperior.add(btnNuevaVenta);
        filaSuperior.add(btnClientes);

        JPanel filaInferior = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, UiTheme.SPACE_MD, 0));
        filaInferior.setOpaque(false);
        filaInferior.setAlignmentX(CENTER_ALIGNMENT);
        filaInferior.setMaximumSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaInferior.setPreferredSize(new java.awt.Dimension(anchoFila, UiTheme.BTN_TILE.height));
        filaInferior.add(btnInventario);
        filaInferior.add(btnAlertar);

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
        btnVentas.addActionListener(e -> controller.abrirVentas(this));
        btnNuevaVenta.addActionListener(e -> controller.abrirNuevaVenta(this));
        btnClientes.addActionListener(e -> controller.abrirClientesCreditos(this));
        btnInventario.addActionListener(e -> controller.abrirInventario(this));
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
        btnAlertar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Menu Ventas");

        btnVentas.setText("Ventas");
        btnNuevaVenta.setText("Nueva Venta");
        btnClientes.setText("Clientes Creditos");
        btnInventario.setText("Inventario");
        btnAlertar.setText("Alertar");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnVentas;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
