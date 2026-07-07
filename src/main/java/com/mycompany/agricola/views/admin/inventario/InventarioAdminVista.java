package com.mycompany.agricola.views.admin.inventario;

import javax.swing.JPanel;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class InventarioAdminVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAlertar;
    public javax.swing.JButton botonEditar;
    public javax.swing.JButton botonVolver;
    public javax.swing.JButton botonRefrescar;
    public javax.swing.JButton botonRetirar;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JScrollPane scrollTablaInventario;
    public javax.swing.JTable tablaInventario;

    public InventarioAdminVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        botonRefrescar = UiStyle.crearBotonRefrescar();
        botonRetirar = new javax.swing.JButton("Retirar");
        UiStyle.estilizarTitulo(etiquetaTitulo);
        UiStyle.estilizarTabla(tablaInventario);
        UiStyle.estilizarScrollTabla(scrollTablaInventario);
        UiStyle.estilizarBotonNav(botonEditar);
        UiStyle.estilizarBotonNav(botonRetirar);
        UiStyle.estilizarBotonNav(botonAlertar);
        UiStyle.estilizarBotonNav(botonVolver);
        UiStyle.conIcono(botonEditar, UiIcons.EDIT);
        UiStyle.conIcono(botonRetirar, UiIcons.DELETE);
        UiStyle.conIcono(botonAlertar, UiIcons.ALERT);
        UiStyle.conIcono(botonVolver, UiIcons.EXIT);
        reorganizarLayout();
    }

    private void reorganizarLayout() {
        removeAll();
        setLayout(new java.awt.BorderLayout(0, UiTheme.SPACE_LG));
        UiStyle.aplicarPagina(this);

        JPanel encabezado = new javax.swing.JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        encabezado.setOpaque(false);
        encabezado.add(etiquetaTitulo, java.awt.BorderLayout.NORTH);

        JPanel barra = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
                UiTheme.SPACE_SM, 0));
        barra.setOpaque(false);
        barra.add(botonEditar);
        barra.add(botonRetirar);
        barra.add(botonAlertar);
        barra.add(botonRefrescar);
        barra.add(botonVolver);
        encabezado.add(barra, java.awt.BorderLayout.CENTER);
        add(encabezado, java.awt.BorderLayout.NORTH);
        add(scrollTablaInventario, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        botonEditar = new javax.swing.JButton();
        botonAlertar = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        scrollTablaInventario = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Inventario");

        botonEditar.setText("Editar");
        botonAlertar.setText("Alertar");
        botonVolver.setText("Volver");

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock", "Minimo", "Prox. vencimiento", "Por vencer", "Vencido"}
        ));
        scrollTablaInventario.setViewportView(tablaInventario);
    }
}
