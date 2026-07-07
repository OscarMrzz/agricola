package com.mycompany.agricola.views;

import javax.swing.JPanel;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class AlertasVista extends javax.swing.JPanel {

    public javax.swing.JButton botonRefrescar;
    public javax.swing.JButton botonVolver;
    public javax.swing.JLabel etiquetaStock;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JLabel etiquetaVencidos;
    public javax.swing.JLabel etiquetaVencimiento;
    public javax.swing.JScrollPane scrollStock;
    public javax.swing.JScrollPane scrollVencidos;
    public javax.swing.JScrollPane scrollVencimiento;
    public javax.swing.JTable tablaStock;
    public javax.swing.JTable tablaVencidos;
    public javax.swing.JTable tablaVencimiento;

    public AlertasVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        botonRefrescar = UiStyle.crearBotonRefrescar();
        botonVolver = new javax.swing.JButton("Volver");
        etiquetaVencidos = new javax.swing.JLabel("Productos vencidos");
        scrollVencidos = new javax.swing.JScrollPane();
        tablaVencidos = new javax.swing.JTable();
        scrollVencidos.setViewportView(tablaVencidos);
        UiStyle.estilizarBotonNav(botonVolver);
        UiStyle.conIcono(botonVolver, UiIcons.EXIT);
        UiStyle.estilizarTitulo(etiquetaTitulo);
        UiStyle.estilizarSeccion(etiquetaVencimiento);
        UiStyle.estilizarSeccion(etiquetaVencidos);
        UiStyle.estilizarSeccion(etiquetaStock);
        UiStyle.estilizarTabla(tablaVencimiento);
        UiStyle.estilizarTabla(tablaVencidos);
        UiStyle.estilizarTabla(tablaStock);
        UiStyle.estilizarScrollTabla(scrollVencimiento);
        UiStyle.estilizarScrollTabla(scrollVencidos);
        UiStyle.estilizarScrollTabla(scrollStock);
        reorganizarLayout();
    }

    private void reorganizarLayout() {
        removeAll();
        setLayout(new java.awt.BorderLayout(0, UiTheme.SPACE_LG));
        UiStyle.aplicarPagina(this);

        JPanel encabezado = new javax.swing.JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        encabezado.setOpaque(false);
        encabezado.add(etiquetaTitulo, java.awt.BorderLayout.NORTH);

        JPanel barra = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, UiTheme.SPACE_SM, 0));
        barra.setOpaque(false);
        barra.add(botonRefrescar);
        barra.add(botonVolver);
        encabezado.add(barra, java.awt.BorderLayout.CENTER);
        add(encabezado, java.awt.BorderLayout.NORTH);

        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new javax.swing.BoxLayout(contenido, javax.swing.BoxLayout.Y_AXIS));

        JPanel seccionVencimiento = new JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        seccionVencimiento.setOpaque(false);
        seccionVencimiento.add(etiquetaVencimiento, java.awt.BorderLayout.NORTH);
        seccionVencimiento.add(scrollVencimiento, java.awt.BorderLayout.CENTER);
        scrollVencimiento.setPreferredSize(new java.awt.Dimension(0, 140));
        contenido.add(seccionVencimiento);
        contenido.add(javax.swing.Box.createVerticalStrut(UiTheme.SPACE_LG));

        JPanel seccionVencidos = new JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        seccionVencidos.setOpaque(false);
        seccionVencidos.add(etiquetaVencidos, java.awt.BorderLayout.NORTH);
        seccionVencidos.add(scrollVencidos, java.awt.BorderLayout.CENTER);
        scrollVencidos.setPreferredSize(new java.awt.Dimension(0, 120));
        contenido.add(seccionVencidos);
        contenido.add(javax.swing.Box.createVerticalStrut(UiTheme.SPACE_LG));

        JPanel seccionStock = new JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        seccionStock.setOpaque(false);
        seccionStock.add(etiquetaStock, java.awt.BorderLayout.NORTH);
        seccionStock.add(scrollStock, java.awt.BorderLayout.CENTER);
        scrollStock.setPreferredSize(new java.awt.Dimension(0, 120));
        contenido.add(seccionStock);

        add(contenido, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaVencimiento = new javax.swing.JLabel();
        scrollVencimiento = new javax.swing.JScrollPane();
        tablaVencimiento = new javax.swing.JTable();
        etiquetaStock = new javax.swing.JLabel();
        scrollStock = new javax.swing.JScrollPane();
        tablaStock = new javax.swing.JTable();

        etiquetaTitulo.setText("Alertas");
        etiquetaVencimiento.setText("Productos por vencer");
        tablaVencimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Cantidad", "Fecha vencimiento", "Dias restantes"}
        ));
        scrollVencimiento.setViewportView(tablaVencimiento);
        etiquetaStock.setText("Stock bajo");
        tablaStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock actual"}
        ));
        scrollStock.setViewportView(tablaStock);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }
}
