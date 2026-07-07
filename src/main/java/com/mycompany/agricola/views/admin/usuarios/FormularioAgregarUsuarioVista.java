package com.mycompany.agricola.views.admin.usuarios;

import com.mycompany.agricola.views.util.UiStyle;

public class FormularioAgregarUsuarioVista extends javax.swing.JPanel {

    public javax.swing.JButton botonCancelar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JCheckBox checkboxActivo;
    public javax.swing.JComboBox<Object> comboboxRol;
    public javax.swing.JLabel etiquetaEstado;
    public javax.swing.JLabel etiquetaNombre;
    public javax.swing.JLabel etiquetaPassword;
    public javax.swing.JLabel etiquetaRol;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JPasswordField inputPassword;
    public javax.swing.JTextField inputNombre;

    public FormularioAgregarUsuarioVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        checkboxActivo.setSelected(true);
        UiStyle.aplicarVistaFormulario(this, etiquetaTitulo, botonGuardar, botonCancelar,
                etiquetaNombre, inputNombre, etiquetaPassword, inputPassword, etiquetaRol, comboboxRol, etiquetaEstado);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        inputNombre = new javax.swing.JTextField();
        etiquetaPassword = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        etiquetaRol = new javax.swing.JLabel();
        comboboxRol = new javax.swing.JComboBox<>();
        etiquetaEstado = new javax.swing.JLabel();
        checkboxActivo = new javax.swing.JCheckBox();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Agregar usuario");

        etiquetaNombre.setText("Usuario:");
        etiquetaPassword.setText("Contrasena:");
        etiquetaRol.setText("Rol:");
        etiquetaEstado.setText("Estado:");
        checkboxActivo.setText("Activo");
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
                        .addComponent(etiquetaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaRol, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboboxRol, 0, 260, Short.MAX_VALUE))
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
                    .addComponent(etiquetaPassword)
                    .addComponent(inputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaRol)
                    .addComponent(comboboxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
