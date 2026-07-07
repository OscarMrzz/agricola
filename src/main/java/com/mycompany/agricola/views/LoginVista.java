package com.mycompany.agricola.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import com.mycompany.agricola.views.util.FormPanel;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class LoginVista extends javax.swing.JPanel {

    public javax.swing.JButton botonLogin;
    public javax.swing.JLabel etiquetaPassword;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JLabel etiquetaUsuario;
    public javax.swing.JPasswordField inputPassword;
    public javax.swing.JTextField inputUsuario;

    public LoginVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        removeAll();
        setLayout(new BorderLayout());
        UiStyle.aplicarDialogo(this);

        etiquetaTitulo.setText("Distribuidora Agricola");
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        UiStyle.estilizarTitulo(etiquetaTitulo);

        UiStyle.estilizarCuerpo(etiquetaUsuario);
        UiStyle.estilizarCuerpo(etiquetaPassword);
        UiStyle.estilizarInput(inputUsuario);
        UiStyle.estilizarInput(inputPassword);
        UiStyle.estilizarBoton(botonLogin, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.conIcono(botonLogin, com.mycompany.agricola.views.util.UiIcons.LOGIN);

        FormPanel panelFormulario = new FormPanel("Acceso");
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_XXL, 0);
        panelFormulario.add(etiquetaTitulo, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_MD, UiTheme.SPACE_MD);
        panelFormulario.add(etiquetaUsuario, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_MD, 0);
        panelFormulario.add(inputUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_LG, UiTheme.SPACE_MD);
        panelFormulario.add(etiquetaPassword, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_LG, 0);
        panelFormulario.add(inputPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(UiTheme.SPACE_MD, 0, 0, 0);
        panelFormulario.add(botonLogin, gbc);

        add(panelFormulario, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        etiquetaUsuario = new javax.swing.JLabel();
        inputUsuario = new javax.swing.JTextField();
        etiquetaPassword = new javax.swing.JLabel();
        inputPassword = new javax.swing.JPasswordField();
        botonLogin = new javax.swing.JButton();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTitulo.setText("Distribuidora Agricola");

        etiquetaUsuario.setText("Usuario:");
        etiquetaPassword.setText("Contrasena:");
        botonLogin.setText("Ingresar");

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
