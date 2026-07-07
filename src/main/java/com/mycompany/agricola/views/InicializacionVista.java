package com.mycompany.agricola.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import com.mycompany.agricola.views.util.FormPanel;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class InicializacionVista extends javax.swing.JPanel {

    public javax.swing.JButton botonDatosBlanco;
    public javax.swing.JButton botonDatosPrueba;
    public javax.swing.JLabel etiquetaMensaje;
    public javax.swing.JLabel etiquetaTitulo;

    public InicializacionVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        removeAll();
        setLayout(new BorderLayout());
        UiStyle.aplicarDialogo(this);

        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        UiStyle.estilizarTitulo(etiquetaTitulo);
        UiStyle.estilizarCuerpo(etiquetaMensaje);
        etiquetaMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        UiStyle.estilizarBoton(botonDatosBlanco, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(botonDatosPrueba, UiStyle.TipoBoton.SECUNDARIO);
        UiStyle.conIcono(botonDatosBlanco, UiIcons.INIT);
        UiStyle.conIcono(botonDatosPrueba, UiIcons.INIT);

        FormPanel panelFormulario = new FormPanel("Configuracion inicial");
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_LG, 0);

        gbc.gridy = 0;
        panelFormulario.add(etiquetaTitulo, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_XXL, 0);
        panelFormulario.add(etiquetaMensaje, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_MD, 0);
        panelFormulario.add(botonDatosBlanco, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        panelFormulario.add(botonDatosPrueba, gbc);

        add(panelFormulario, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonDatosBlanco = new javax.swing.JButton();
        botonDatosPrueba = new javax.swing.JButton();

        etiquetaTitulo.setText("Distribuidora Agricola");
        etiquetaMensaje.setText("Bienvenido. Configure la base de datos para comenzar.");
        botonDatosBlanco.setText("Iniciar con datos en blanco");
        botonDatosPrueba.setText("Iniciar con datos de prueba");

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
