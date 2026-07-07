package com.mycompany.agricola.views.admin.inventario;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioEditarInventarioVista extends javax.swing.JPanel {

    public javax.swing.JButton botonCancelar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JLabel etiquetaIdProducto;
    public javax.swing.JLabel etiquetaIdProductoValor;
    public javax.swing.JLabel etiquetaNoEncontrado;
    public javax.swing.JLabel etiquetaStockActual;
    public javax.swing.JLabel etiquetaStockActualValor;
    public javax.swing.JLabel etiquetaStockMinimo;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JPanel panelFormulario;
    public javax.swing.JTextField inputStockMinimo;

    public FormularioEditarInventarioVista() {
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
        UiStyle.estilizarCuerpo(etiquetaIdProducto);
        UiStyle.estilizarCuerpo(etiquetaIdProductoValor);
        UiStyle.estilizarCuerpo(etiquetaStockActual);
        UiStyle.estilizarCuerpo(etiquetaStockActualValor);
        UiStyle.estilizarCuerpo(etiquetaStockMinimo);
        UiStyle.estilizarInput(inputStockMinimo);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaNoEncontrado = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaIdProducto = new javax.swing.JLabel();
        etiquetaIdProductoValor = new javax.swing.JLabel();
        etiquetaStockActual = new javax.swing.JLabel();
        etiquetaStockActualValor = new javax.swing.JLabel();
        etiquetaStockMinimo = new javax.swing.JLabel();
        inputStockMinimo = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        etiquetaNoEncontrado.setText("Inventario no encontrado");
        etiquetaNoEncontrado.setVisible(false);

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Editar stock minimo");

        etiquetaIdProducto.setText("ID Producto:");
        etiquetaIdProductoValor.setText("-");
        etiquetaStockActual.setText("Stock actual:");
        etiquetaStockActualValor.setText("-");
        etiquetaStockMinimo.setText("Stock minimo:");
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
                        .addComponent(etiquetaIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiquetaIdProductoValor))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(etiquetaStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiquetaStockActualValor))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(etiquetaStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(etiquetaIdProducto)
                    .addComponent(etiquetaIdProductoValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaStockActual)
                    .addComponent(etiquetaStockActualValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaStockMinimo)
                    .addComponent(inputStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
