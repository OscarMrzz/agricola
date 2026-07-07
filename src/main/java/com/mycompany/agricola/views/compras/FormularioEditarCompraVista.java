package com.mycompany.agricola.views.compras;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioEditarCompraVista extends javax.swing.JPanel {

    public javax.swing.JButton botonCancelar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JComboBox<String> comboboxMetodoPago;
    public javax.swing.JLabel etiquetaCantidad;
    public javax.swing.JLabel etiquetaError;
    public javax.swing.JLabel etiquetaFactura;
    public javax.swing.JLabel etiquetaFacturaValor;
    public javax.swing.JLabel etiquetaMetodoPago;
    public javax.swing.JLabel etiquetaPrecio;
    public javax.swing.JLabel etiquetaProducto;
    public javax.swing.JLabel etiquetaProductoValor;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JPanel panelBotones;
    public javax.swing.JPanel panelFormulario;
    public javax.swing.JTextField inputCantidad;
    public javax.swing.JTextField inputPrecio;

    public FormularioEditarCompraVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(etiquetaTitulo);
        UiStyle.estilizarFormPanel(panelFormulario, "Datos");
        UiStyle.estilizarError(etiquetaError);
        UiStyle.estilizarBoton(botonGuardar, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(botonCancelar, UiStyle.TipoBoton.SECUNDARIO);
        UiStyle.conIcono(botonGuardar, UiIcons.SAVE);
        UiStyle.conIcono(botonCancelar, UiIcons.CANCEL);
        UiStyle.estilizarCuerpo(etiquetaFactura);
        UiStyle.estilizarCuerpo(etiquetaFacturaValor);
        UiStyle.estilizarCuerpo(etiquetaProducto);
        UiStyle.estilizarCuerpo(etiquetaProductoValor);
        UiStyle.estilizarCuerpo(etiquetaCantidad);
        UiStyle.estilizarInput(inputCantidad);
        UiStyle.estilizarCuerpo(etiquetaPrecio);
        UiStyle.estilizarInput(inputPrecio);
        UiStyle.estilizarCuerpo(etiquetaMetodoPago);
        UiStyle.estilizarInput(comboboxMetodoPago);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaError = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        etiquetaFactura = new javax.swing.JLabel();
        etiquetaFacturaValor = new javax.swing.JLabel();
        etiquetaProducto = new javax.swing.JLabel();
        etiquetaProductoValor = new javax.swing.JLabel();
        etiquetaCantidad = new javax.swing.JLabel();
        inputCantidad = new javax.swing.JTextField();
        etiquetaPrecio = new javax.swing.JLabel();
        inputPrecio = new javax.swing.JTextField();
        etiquetaMetodoPago = new javax.swing.JLabel();
        comboboxMetodoPago = new javax.swing.JComboBox<>();
        panelBotones = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Editar Compra");

        etiquetaError.setForeground(new java.awt.Color(204, 0, 0));
        etiquetaError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaError.setText(" ");
        etiquetaError.setVisible(false);

        etiquetaFactura.setText("Factura:");
        etiquetaProducto.setText("Producto:");
        etiquetaCantidad.setText("Cantidad:");
        etiquetaPrecio.setText("Precio compra:");
        etiquetaMetodoPago.setText("Metodo de pago:");

        comboboxMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "contado", "credito" }));

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiquetaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(etiquetaProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaMetodoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaFacturaValor, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(etiquetaProductoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaFactura)
                    .addComponent(etiquetaFacturaValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaProducto)
                    .addComponent(etiquetaProductoValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCantidad)
                    .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaPrecio)
                    .addComponent(inputPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaMetodoPago)
                    .addComponent(comboboxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonGuardar.setText("Guardar");
        botonCancelar.setText("Cancelar");

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonGuardar)
                .addGap(8, 8, 8)
                .addComponent(botonCancelar))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botonGuardar)
                .addComponent(botonCancelar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTitulo)
                    .addComponent(etiquetaError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etiquetaTitulo)
                .addGap(18, 18, 18)
                .addComponent(etiquetaError)
                .addGap(18, 18, 18)
                .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }
}
