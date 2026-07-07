package com.mycompany.agricola.views.ventas;

import javax.swing.JPanel;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioAgregarVentaVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAgregarCarrito;
    public javax.swing.JButton botonEliminarFila;
    public javax.swing.JButton botonGuardarVenta;
    public javax.swing.JButton botonVolver;
    public javax.swing.JComboBox comboboxCliente;
    public javax.swing.JComboBox<String> comboboxMetodoPago;
    public javax.swing.JComboBox comboboxProducto;
    public javax.swing.JLabel etiquetaCantidad;
    public javax.swing.JLabel etiquetaCliente;
    public javax.swing.JLabel etiquetaIsvFactura;
    public javax.swing.JLabel etiquetaIsvFacturaTitulo;
    public javax.swing.JLabel etiquetaIsvLinea;
    public javax.swing.JLabel etiquetaIsvLineaTitulo;
    public javax.swing.JLabel etiquetaMetodoPago;
    public javax.swing.JLabel etiquetaNoFactura;
    public javax.swing.JLabel etiquetaNoFacturaValor;
    public javax.swing.JLabel etiquetaPrecioUnitario;
    public javax.swing.JLabel etiquetaProducto;
    public javax.swing.JLabel etiquetaSubtotalFactura;
    public javax.swing.JLabel etiquetaSubtotalFacturaTitulo;
    public javax.swing.JLabel etiquetaSubtotalLinea;
    public javax.swing.JLabel etiquetaSubtotalLineaTitulo;
    public javax.swing.JLabel etiquetaTituloCarrito;
    public javax.swing.JLabel etiquetaTituloFormulario;
    public javax.swing.JLabel etiquetaTotalFactura;
    public javax.swing.JLabel etiquetaTotalFacturaTitulo;
    public javax.swing.JLabel etiquetaTotalLinea;
    public javax.swing.JLabel etiquetaTotalLineaTitulo;
    public javax.swing.JScrollPane scrollCarrito;
    public javax.swing.JTable tablaCarrito;
    public javax.swing.JTextField inputCantidad;
    public javax.swing.JTextField inputPrecioUnitario;

    private javax.swing.JPanel panelCarrito;
    private javax.swing.JPanel panelFormulario;

    public FormularioAgregarVentaVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(etiquetaTituloFormulario);
        UiStyle.estilizarSeccion(etiquetaTituloCarrito);
        UiStyle.estilizarFormPanel(panelFormulario, "Datos");
        UiStyle.estilizarFormPanel(panelCarrito, "Carrito");
        UiStyle.estilizarTabla(tablaCarrito);
        UiStyle.estilizarScrollTabla(scrollCarrito);
        UiStyle.estilizarCuerpo(etiquetaNoFactura);
        UiStyle.estilizarCuerpo(etiquetaNoFacturaValor);
        UiStyle.estilizarCuerpo(etiquetaProducto);
        UiStyle.estilizarInput(comboboxProducto);
        UiStyle.estilizarCuerpo(etiquetaCliente);
        UiStyle.estilizarInput(comboboxCliente);
        UiStyle.estilizarCuerpo(etiquetaCantidad);
        UiStyle.estilizarInput(inputCantidad);
        UiStyle.estilizarCuerpo(etiquetaPrecioUnitario);
        UiStyle.estilizarInput(inputPrecioUnitario);
        UiStyle.estilizarCuerpo(etiquetaMetodoPago);
        UiStyle.estilizarInput(comboboxMetodoPago);
        UiStyle.estilizarCuerpo(etiquetaSubtotalLineaTitulo);
        UiStyle.estilizarCuerpo(etiquetaSubtotalLinea);
        UiStyle.estilizarCuerpo(etiquetaIsvLineaTitulo);
        UiStyle.estilizarCuerpo(etiquetaIsvLinea);
        UiStyle.estilizarCuerpo(etiquetaTotalLineaTitulo);
        UiStyle.estilizarTotal(etiquetaTotalLinea);
        UiStyle.estilizarCuerpo(etiquetaSubtotalFacturaTitulo);
        UiStyle.estilizarCuerpo(etiquetaSubtotalFactura);
        UiStyle.estilizarCuerpo(etiquetaIsvFacturaTitulo);
        UiStyle.estilizarCuerpo(etiquetaIsvFactura);
        UiStyle.estilizarCuerpo(etiquetaTotalFacturaTitulo);
        UiStyle.estilizarTotal(etiquetaTotalFactura);
        UiStyle.estilizarBotonNav(botonAgregarCarrito);
        UiStyle.estilizarBoton(botonGuardarVenta, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(botonVolver, UiStyle.TipoBoton.SECUNDARIO);
        botonEliminarFila = new javax.swing.JButton("Eliminar fila");
        UiStyle.estilizarBoton(botonEliminarFila, UiStyle.TipoBoton.PELIGRO);
        UiStyle.conIcono(botonAgregarCarrito, UiIcons.CART);
        UiStyle.conIcono(botonGuardarVenta, UiIcons.SAVE);
        UiStyle.conIcono(botonVolver, UiIcons.EXIT);
        UiStyle.conIcono(botonEliminarFila, UiIcons.DELETE);
        reorganizarLayout();
    }

    private void reorganizarLayout() {
        panelFormulario.removeAll();
        panelCarrito.removeAll();
        removeAll();

        panelFormulario.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 0, com.mycompany.agricola.views.util.UiTheme.SPACE_LG, 0);
        panelFormulario.add(etiquetaTituloFormulario, gbc);

        agregarFilaFormulario(panelFormulario, gbc, 1, etiquetaNoFactura, etiquetaNoFacturaValor);
        agregarFilaFormulario(panelFormulario, gbc, 2, etiquetaCliente, comboboxCliente);
        agregarFilaFormulario(panelFormulario, gbc, 3, etiquetaProducto, comboboxProducto);
        agregarFilaFormulario(panelFormulario, gbc, 4, etiquetaCantidad, inputCantidad);
        agregarFilaFormulario(panelFormulario, gbc, 5, etiquetaPrecioUnitario, inputPrecioUnitario);
        agregarFilaFormulario(panelFormulario, gbc, 6, etiquetaMetodoPago, comboboxMetodoPago);
        agregarFilaFormulario(panelFormulario, gbc, 7, etiquetaSubtotalLineaTitulo, etiquetaSubtotalLinea);
        agregarFilaFormulario(panelFormulario, gbc, 8, etiquetaIsvLineaTitulo, etiquetaIsvLinea);
        agregarFilaFormulario(panelFormulario, gbc, 9, etiquetaTotalLineaTitulo, etiquetaTotalLinea);

        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(com.mycompany.agricola.views.util.UiTheme.SPACE_LG, 0,
                com.mycompany.agricola.views.util.UiTheme.SPACE_MD, 0);
        panelFormulario.add(botonAgregarCarrito, gbc);
        gbc.gridy = 11;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        panelFormulario.add(botonVolver, gbc);

        panelCarrito.setLayout(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD));
        JPanel contenidoCarrito = new JPanel(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD));
        contenidoCarrito.setOpaque(false);
        contenidoCarrito.add(etiquetaTituloCarrito, java.awt.BorderLayout.NORTH);
        contenidoCarrito.add(scrollCarrito, java.awt.BorderLayout.CENTER);

        JPanel totales = new JPanel(new java.awt.GridLayout(3, 2, com.mycompany.agricola.views.util.UiTheme.SPACE_MD,
                com.mycompany.agricola.views.util.UiTheme.SPACE_SM));
        totales.setOpaque(false);
        totales.add(etiquetaSubtotalFacturaTitulo);
        totales.add(etiquetaSubtotalFactura);
        totales.add(etiquetaIsvFacturaTitulo);
        totales.add(etiquetaIsvFactura);
        totales.add(etiquetaTotalFacturaTitulo);
        totales.add(etiquetaTotalFactura);
        contenidoCarrito.add(totales, java.awt.BorderLayout.SOUTH);
        panelCarrito.add(contenidoCarrito, java.awt.BorderLayout.CENTER);

        JPanel barraCarrito = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
                com.mycompany.agricola.views.util.UiTheme.SPACE_SM, 0));
        barraCarrito.setOpaque(false);
        barraCarrito.add(botonEliminarFila);
        barraCarrito.add(botonGuardarVenta);
        panelCarrito.add(barraCarrito, java.awt.BorderLayout.SOUTH);

        setLayout(new java.awt.GridLayout(1, 2, com.mycompany.agricola.views.util.UiTheme.SPACE_LG, 0));
        add(panelFormulario);
        add(panelCarrito);
        revalidate();
        repaint();
    }

    private void agregarFilaFormulario(javax.swing.JPanel panel, java.awt.GridBagConstraints gbc, int fila,
            javax.swing.JLabel etiqueta, java.awt.Component campo) {
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.insets = new java.awt.Insets(0, 0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD,
                com.mycompany.agricola.views.util.UiTheme.SPACE_MD);
        panel.add(etiqueta, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD, 0);
        panel.add(campo, gbc);
        gbc.gridx = 0;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        panelFormulario = new javax.swing.JPanel();
        etiquetaTituloFormulario = new javax.swing.JLabel();
        etiquetaNoFactura = new javax.swing.JLabel();
        etiquetaNoFacturaValor = new javax.swing.JLabel();
        etiquetaProducto = new javax.swing.JLabel();
        comboboxProducto = new javax.swing.JComboBox<>();
        etiquetaCliente = new javax.swing.JLabel();
        comboboxCliente = new javax.swing.JComboBox<>();
        etiquetaCantidad = new javax.swing.JLabel();
        inputCantidad = new javax.swing.JTextField();
        etiquetaPrecioUnitario = new javax.swing.JLabel();
        inputPrecioUnitario = new javax.swing.JTextField();
        etiquetaMetodoPago = new javax.swing.JLabel();
        comboboxMetodoPago = new javax.swing.JComboBox<>(new String[]{"contado", "credito"});
        etiquetaSubtotalLineaTitulo = new javax.swing.JLabel();
        etiquetaSubtotalLinea = new javax.swing.JLabel();
        etiquetaIsvLineaTitulo = new javax.swing.JLabel();
        etiquetaIsvLinea = new javax.swing.JLabel();
        etiquetaTotalLineaTitulo = new javax.swing.JLabel();
        etiquetaTotalLinea = new javax.swing.JLabel();
        botonAgregarCarrito = new javax.swing.JButton();
        botonGuardarVenta = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        panelCarrito = new javax.swing.JPanel();
        etiquetaTituloCarrito = new javax.swing.JLabel();
        scrollCarrito = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        etiquetaSubtotalFacturaTitulo = new javax.swing.JLabel();
        etiquetaSubtotalFactura = new javax.swing.JLabel();
        etiquetaIsvFacturaTitulo = new javax.swing.JLabel();
        etiquetaIsvFactura = new javax.swing.JLabel();
        etiquetaTotalFacturaTitulo = new javax.swing.JLabel();
        etiquetaTotalFactura = new javax.swing.JLabel();

        etiquetaTituloFormulario.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTituloFormulario.setText("Nueva venta");
        etiquetaNoFactura.setText("No. factura:");
        etiquetaNoFacturaValor.setText("-");
        etiquetaProducto.setText("Producto:");
        etiquetaCliente.setText("Cliente:");
        etiquetaCantidad.setText("Cantidad:");
        etiquetaPrecioUnitario.setText("Precio unitario:");
        inputPrecioUnitario.setEditable(false);
        etiquetaMetodoPago.setText("Metodo pago:");
        etiquetaSubtotalLineaTitulo.setText("Subtotal linea:");
        etiquetaSubtotalLinea.setText("0.00");
        etiquetaIsvLineaTitulo.setText("ISV 15%:");
        etiquetaIsvLinea.setText("0.00");
        etiquetaTotalLineaTitulo.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalLineaTitulo.setText("Total linea:");
        etiquetaTotalLinea.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalLinea.setText("0.00");
        botonAgregarCarrito.setText("Agregar al carrito");
        botonGuardarVenta.setText("Guardar venta");
        botonVolver.setText("Volver");
        etiquetaTituloCarrito.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTituloCarrito.setText("Carrito de productos");
        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"No", "Producto", "Cantidad", "Precio", "Total"}));
        scrollCarrito.setViewportView(tablaCarrito);
        etiquetaSubtotalFacturaTitulo.setText("Subtotal:");
        etiquetaSubtotalFactura.setText("0.00");
        etiquetaIsvFacturaTitulo.setText("ISV:");
        etiquetaIsvFactura.setText("0.00");
        etiquetaTotalFacturaTitulo.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalFacturaTitulo.setText("Total:");
        etiquetaTotalFactura.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalFactura.setText("0.00");

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
        panelFormularioLayout.setVerticalGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));

        javax.swing.GroupLayout panelCarritoLayout = new javax.swing.GroupLayout(panelCarrito);
        panelCarrito.setLayout(panelCarritoLayout);
        panelCarritoLayout.setHorizontalGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
        panelCarritoLayout.setVerticalGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
    }
}
