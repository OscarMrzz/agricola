package com.mycompany.agricola.views.admin.clientes;

import com.mycompany.agricola.views.util.UiStyle;

public class FormularioAgregarClienteVista extends javax.swing.JPanel {

    public javax.swing.JButton botonCancelar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JCheckBox checkboxActivo;
    public javax.swing.JLabel etiquetaApellido;
    public javax.swing.JLabel etiquetaEstado;
    public javax.swing.JLabel etiquetaLimiteCredito;
    public javax.swing.JLabel etiquetaNombre;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JTextField inputApellido;
    public javax.swing.JTextField inputLimiteCredito;
    public javax.swing.JTextField inputNombre;

    public FormularioAgregarClienteVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        inputLimiteCredito.setText("0");
        UiStyle.aplicarVistaFormulario(this, etiquetaTitulo, botonGuardar, botonCancelar,
                etiquetaNombre, inputNombre, etiquetaApellido, inputApellido,
                etiquetaLimiteCredito, inputLimiteCredito, etiquetaEstado);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        inputNombre = new javax.swing.JTextField();
        etiquetaApellido = new javax.swing.JLabel();
        inputApellido = new javax.swing.JTextField();
        etiquetaLimiteCredito = new javax.swing.JLabel();
        inputLimiteCredito = new javax.swing.JTextField();
        etiquetaEstado = new javax.swing.JLabel();
        checkboxActivo = new javax.swing.JCheckBox();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Agregar cliente");

        etiquetaNombre.setText("Nombre:");
        etiquetaApellido.setText("Apellido:");
        etiquetaLimiteCredito.setText("Limite credito:");
        etiquetaEstado.setText("Estado:");
        checkboxActivo.setText("Activo");
        checkboxActivo.setSelected(true);
        botonGuardar.setText("Guardar");
        botonCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkboxActivo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonGuardar)
                        .addGap(8, 8, 8)
                        .addComponent(botonCancelar)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etiquetaTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre)
                    .addComponent(inputNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaApellido)
                    .addComponent(inputApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaLimiteCredito)
                    .addComponent(inputLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaEstado)
                    .addComponent(checkboxActivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }
}
