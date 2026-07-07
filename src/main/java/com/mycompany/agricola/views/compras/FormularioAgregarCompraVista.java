package com.mycompany.agricola.views.compras;

import javax.swing.JPanel;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioAgregarCompraVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAgregarCarrito;
    public javax.swing.JButton botonEliminarFila;
    public javax.swing.JButton botonGuardarCompra;
    public javax.swing.JButton botonVolver;
    public javax.swing.JComboBox<String> comboboxMetodoPago;
    public javax.swing.JComboBox<String> comboboxProducto;
    public javax.swing.JLabel etiquetaCantidad;
    public javax.swing.JLabel etiquetaFechaExpiracion;
    public javax.swing.JLabel etiquetaIsvFactura;
    public javax.swing.JLabel etiquetaIsvFacturaEtiqueta;
    public javax.swing.JLabel etiquetaIsvLinea;
    public javax.swing.JLabel etiquetaIsvEtiqueta;
    public javax.swing.JLabel etiquetaMetodoPago;
    public javax.swing.JLabel etiquetaNoFactura;
    public javax.swing.JLabel etiquetaNoFacturaValor;
    public javax.swing.JLabel etiquetaPrecio;
    public javax.swing.JLabel etiquetaProducto;
    public javax.swing.JLabel etiquetaSubtotalFactura;
    public javax.swing.JLabel etiquetaSubtotalFacturaEtiqueta;
    public javax.swing.JLabel etiquetaSubtotalLinea;
    public javax.swing.JLabel etiquetaSubtotalEtiqueta;
    public javax.swing.JLabel etiquetaTituloCarrito;
    public javax.swing.JLabel etiquetaTituloFormulario;
    public javax.swing.JLabel etiquetaTotalEtiqueta;
    public javax.swing.JLabel etiquetaTotalFactura;
    public javax.swing.JLabel etiquetaTotalFacturaEtiqueta;
    public javax.swing.JLabel etiquetaTotalLinea;
    public javax.swing.JPanel panelCarrito;
    public javax.swing.JPanel panelFormulario;
    public javax.swing.JScrollPane scrollCarrito;
    public javax.swing.JTable tablaCarrito;
    public javax.swing.JTextField inputCantidad;
    public javax.swing.JTextField inputFechaExpiracion;
    public javax.swing.JTextField inputPrecio;

    public FormularioAgregarCompraVista() {
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
        UiStyle.estilizarCuerpo(etiquetaCantidad);
        UiStyle.estilizarInput(inputCantidad);
        UiStyle.estilizarCuerpo(etiquetaPrecio);
        UiStyle.estilizarInput(inputPrecio);
        UiStyle.estilizarCuerpo(etiquetaMetodoPago);
        UiStyle.estilizarInput(comboboxMetodoPago);
        UiStyle.estilizarCuerpo(etiquetaFechaExpiracion);
        UiStyle.estilizarInput(inputFechaExpiracion);
        UiStyle.estilizarCuerpo(etiquetaSubtotalEtiqueta);
        UiStyle.estilizarCuerpo(etiquetaSubtotalLinea);
        UiStyle.estilizarCuerpo(etiquetaIsvEtiqueta);
        UiStyle.estilizarCuerpo(etiquetaIsvLinea);
        UiStyle.estilizarCuerpo(etiquetaTotalEtiqueta);
        UiStyle.estilizarTotal(etiquetaTotalLinea);
        UiStyle.estilizarCuerpo(etiquetaSubtotalFacturaEtiqueta);
        UiStyle.estilizarCuerpo(etiquetaSubtotalFactura);
        UiStyle.estilizarCuerpo(etiquetaIsvFacturaEtiqueta);
        UiStyle.estilizarCuerpo(etiquetaIsvFactura);
        UiStyle.estilizarCuerpo(etiquetaTotalFacturaEtiqueta);
        UiStyle.estilizarTotal(etiquetaTotalFactura);
        UiStyle.estilizarBotonNav(botonAgregarCarrito);
        UiStyle.estilizarBoton(botonGuardarCompra, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(botonVolver, UiStyle.TipoBoton.SECUNDARIO);
        botonEliminarFila = new javax.swing.JButton("Eliminar fila");
        UiStyle.estilizarBoton(botonEliminarFila, UiStyle.TipoBoton.PELIGRO);
        UiStyle.conIcono(botonAgregarCarrito, UiIcons.CART);
        UiStyle.conIcono(botonGuardarCompra, UiIcons.SAVE);
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
        agregarFilaFormulario(panelFormulario, gbc, 2, etiquetaProducto, comboboxProducto);
        agregarFilaFormulario(panelFormulario, gbc, 3, etiquetaCantidad, inputCantidad);
        agregarFilaFormulario(panelFormulario, gbc, 4, etiquetaPrecio, inputPrecio);
        agregarFilaFormulario(panelFormulario, gbc, 5, etiquetaMetodoPago, comboboxMetodoPago);
        agregarFilaFormulario(panelFormulario, gbc, 6, etiquetaFechaExpiracion, inputFechaExpiracion);
        agregarFilaFormulario(panelFormulario, gbc, 7, etiquetaSubtotalEtiqueta, etiquetaSubtotalLinea);
        agregarFilaFormulario(panelFormulario, gbc, 8, etiquetaIsvEtiqueta, etiquetaIsvLinea);
        agregarFilaFormulario(panelFormulario, gbc, 9, etiquetaTotalEtiqueta, etiquetaTotalLinea);

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
        totales.add(etiquetaSubtotalFacturaEtiqueta);
        totales.add(etiquetaSubtotalFactura);
        totales.add(etiquetaIsvFacturaEtiqueta);
        totales.add(etiquetaIsvFactura);
        totales.add(etiquetaTotalFacturaEtiqueta);
        totales.add(etiquetaTotalFactura);
        contenidoCarrito.add(totales, java.awt.BorderLayout.SOUTH);
        panelCarrito.add(contenidoCarrito, java.awt.BorderLayout.CENTER);

        JPanel barraCarrito = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
                com.mycompany.agricola.views.util.UiTheme.SPACE_SM, 0));
        barraCarrito.setOpaque(false);
        barraCarrito.add(botonEliminarFila);
        barraCarrito.add(botonGuardarCompra);
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
        etiquetaCantidad = new javax.swing.JLabel();
        inputCantidad = new javax.swing.JTextField();
        etiquetaPrecio = new javax.swing.JLabel();
        inputPrecio = new javax.swing.JTextField();
        etiquetaMetodoPago = new javax.swing.JLabel();
        comboboxMetodoPago = new javax.swing.JComboBox<>(new String[]{"contado", "credito"});
        etiquetaFechaExpiracion = new javax.swing.JLabel();
        inputFechaExpiracion = new javax.swing.JTextField();
        etiquetaSubtotalEtiqueta = new javax.swing.JLabel();
        etiquetaSubtotalLinea = new javax.swing.JLabel();
        etiquetaIsvEtiqueta = new javax.swing.JLabel();
        etiquetaIsvLinea = new javax.swing.JLabel();
        etiquetaTotalEtiqueta = new javax.swing.JLabel();
        etiquetaTotalLinea = new javax.swing.JLabel();
        botonAgregarCarrito = new javax.swing.JButton();
        botonGuardarCompra = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        panelCarrito = new javax.swing.JPanel();
        etiquetaTituloCarrito = new javax.swing.JLabel();
        scrollCarrito = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        etiquetaSubtotalFacturaEtiqueta = new javax.swing.JLabel();
        etiquetaSubtotalFactura = new javax.swing.JLabel();
        etiquetaIsvFacturaEtiqueta = new javax.swing.JLabel();
        etiquetaIsvFactura = new javax.swing.JLabel();
        etiquetaTotalFacturaEtiqueta = new javax.swing.JLabel();
        etiquetaTotalFactura = new javax.swing.JLabel();

        etiquetaTituloFormulario.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTituloFormulario.setText("Nueva compra");
        etiquetaNoFactura.setText("No. factura:");
        etiquetaNoFacturaValor.setText("-");
        etiquetaProducto.setText("Producto:");
        etiquetaCantidad.setText("Cantidad:");
        etiquetaPrecio.setText("Precio unitario:");
        etiquetaMetodoPago.setText("Metodo pago:");
        etiquetaFechaExpiracion.setText("Fecha expiracion:");
        etiquetaSubtotalEtiqueta.setText("Subtotal linea:");
        etiquetaSubtotalLinea.setText("0.00");
        etiquetaIsvEtiqueta.setText("ISV 15%:");
        etiquetaIsvLinea.setText("0.00");
        etiquetaTotalEtiqueta.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalEtiqueta.setText("Total linea:");
        etiquetaTotalLinea.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalLinea.setText("0.00");
        botonAgregarCarrito.setText("Agregar al carrito");
        botonGuardarCompra.setText("Guardar compra");
        botonVolver.setText("Volver");
        etiquetaTituloCarrito.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTituloCarrito.setText("Carrito de productos");
        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"No", "Producto", "Cantidad", "Precio", "Subtotal"}));
        scrollCarrito.setViewportView(tablaCarrito);
        etiquetaSubtotalFacturaEtiqueta.setText("Subtotal:");
        etiquetaSubtotalFactura.setText("0.00");
        etiquetaIsvFacturaEtiqueta.setText("ISV:");
        etiquetaIsvFactura.setText("0.00");
        etiquetaTotalFacturaEtiqueta.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalFacturaEtiqueta.setText("Total:");
        etiquetaTotalFactura.setFont(new java.awt.Font("Arial Black", 1, 12));
        etiquetaTotalFactura.setText("0.00");

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));

        javax.swing.GroupLayout panelCarritoLayout = new javax.swing.GroupLayout(panelCarrito);
        panelCarrito.setLayout(panelCarritoLayout);
        panelCarritoLayout.setHorizontalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
        panelCarritoLayout.setVerticalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
    }
}
