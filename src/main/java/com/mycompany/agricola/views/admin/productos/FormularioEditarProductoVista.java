package com.mycompany.agricola.views.admin.productos;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioEditarProductoVista extends javax.swing.JPanel {

    public javax.swing.JButton botonCancelar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JLabel etiquetaCategoria;
    public javax.swing.JLabel etiquetaDepartamento;
    public javax.swing.JLabel etiquetaNoEncontrado;
    public javax.swing.JLabel etiquetaNombre;
    public javax.swing.JLabel etiquetaPrecio;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JLabel etiquetaVencimiento;
    public javax.swing.JPanel panelFormulario;
    public javax.swing.JTextField inputCategoria;
    public javax.swing.JTextField inputDepartamento;
    public javax.swing.JTextField inputNombre;
    public javax.swing.JTextField inputPrecio;
    public javax.swing.JTextField inputVencimiento;

    public FormularioEditarProductoVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(etiquetaTitulo);
        UiStyle.estilizarFormPanel(panelFormulario, "Datos");
        UiStyle.estilizarError(etiquetaNoEncontrado);
        UiStyle.estilizarBoton(botonGuardar, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(botonCancelar, UiStyle.TipoBoton.SECUNDARIO);
        UiStyle.conIcono(botonGuardar, UiIcons.SAVE);
        UiStyle.conIcono(botonCancelar, UiIcons.CANCEL);
        UiStyle.estilizarCuerpo(etiquetaNombre);
        UiStyle.estilizarInput(inputNombre);
        UiStyle.estilizarCuerpo(etiquetaCategoria);
        UiStyle.estilizarInput(inputCategoria);
        UiStyle.estilizarCuerpo(etiquetaDepartamento);
        UiStyle.estilizarInput(inputDepartamento);
        UiStyle.estilizarCuerpo(etiquetaPrecio);
        UiStyle.estilizarInput(inputPrecio);
        UiStyle.estilizarCuerpo(etiquetaVencimiento);
        UiStyle.estilizarInput(inputVencimiento);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaNoEncontrado = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        inputNombre = new javax.swing.JTextField();
        etiquetaCategoria = new javax.swing.JLabel();
        inputCategoria = new javax.swing.JTextField();
        etiquetaDepartamento = new javax.swing.JLabel();
        inputDepartamento = new javax.swing.JTextField();
        etiquetaPrecio = new javax.swing.JLabel();
        inputPrecio = new javax.swing.JTextField();
        etiquetaVencimiento = new javax.swing.JLabel();
        inputVencimiento = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        etiquetaNoEncontrado.setText("Producto no encontrado");
        etiquetaNoEncontrado.setVisible(false);

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Editar producto");

        etiquetaNombre.setText("Nombre:");
        etiquetaCategoria.setText("Categoria:");
        etiquetaDepartamento.setText("Departamento:");
        etiquetaPrecio.setText("Precio venta:");
        etiquetaVencimiento.setText("Vencimiento:");
        botonGuardar.setText("Guardar");
        botonCancelar.setText("Cancelar");

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTitulo)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(etiquetaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(etiquetaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(etiquetaDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(etiquetaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(etiquetaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(botonGuardar)
                        .addGap(8, 8, 8)
                        .addComponent(botonCancelar)))
                .addGap(20, 20, 20))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etiquetaTitulo)
                .addGap(18, 18, 18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre)
                    .addComponent(inputNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCategoria)
                    .addComponent(inputCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaDepartamento)
                    .addComponent(inputDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaPrecio)
                    .addComponent(inputPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaVencimiento)
                    .addComponent(inputVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etiquetaNoEncontrado)
            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etiquetaNoEncontrado)
            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}
