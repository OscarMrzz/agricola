package com.mycompany.agricola.views.ventas;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioEditarVentaVista extends javax.swing.JPanel {

    public javax.swing.JButton botonCancelar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JComboBox<String> comboboxMetodoPago;
    public javax.swing.JLabel etiquetaCantidad;
    public javax.swing.JLabel etiquetaError;
    public javax.swing.JLabel etiquetaMetodoPago;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JTextField inputCantidad;

    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFormulario;

    public FormularioEditarVentaVista() {
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
        UiStyle.estilizarCuerpo(etiquetaCantidad);
        UiStyle.estilizarInput(inputCantidad);
        UiStyle.estilizarCuerpo(etiquetaMetodoPago);
        UiStyle.estilizarInput(comboboxMetodoPago);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaError = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        etiquetaCantidad = new javax.swing.JLabel();
        inputCantidad = new javax.swing.JTextField();
        etiquetaMetodoPago = new javax.swing.JLabel();
        comboboxMetodoPago = new javax.swing.JComboBox<>();
        panelBotones = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Editar Venta");

        etiquetaError.setForeground(new java.awt.Color(204, 0, 0));
        etiquetaError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaError.setText(" ");
        etiquetaError.setVisible(false);

        etiquetaCantidad.setText("Cantidad:");

        etiquetaMetodoPago.setText("Metodo de pago:");

        comboboxMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "contado", "credito" }));

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiquetaCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(etiquetaMetodoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCantidad)
                    .addComponent(inputCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
