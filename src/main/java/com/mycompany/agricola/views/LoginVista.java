package com.mycompany.agricola.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.controllers.LoginController;
import com.mycompany.agricola.model.entity.UsuarioEntity;
import com.mycompany.agricola.services.AuthService;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.util.FormPanel;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class LoginVista extends javax.swing.JPanel {

    private final LoginController controller = new LoginController();

    public LoginVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        removeAll();
        setLayout(new BorderLayout());
        UiStyle.aplicarDialogo(this);

        lblTitulo.setText("Distribuidora Agricola");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        UiStyle.estilizarTitulo(lblTitulo);

        UiStyle.estilizarCuerpo(lblUsuario);
        UiStyle.estilizarCuerpo(lblPassword);
        UiStyle.estilizarInput(txtUsuario);
        UiStyle.estilizarInput(txtPassword);
        UiStyle.estilizarBoton(btnLogin, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.conIcono(btnLogin, com.mycompany.agricola.views.util.UiIcons.LOGIN);

        FormPanel panelFormulario = new FormPanel("Acceso");
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_XXL, 0);
        panelFormulario.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_MD, UiTheme.SPACE_MD);
        panelFormulario.add(lblUsuario, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_MD, 0);
        panelFormulario.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_LG, UiTheme.SPACE_MD);
        panelFormulario.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_LG, 0);
        panelFormulario.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(UiTheme.SPACE_MD, 0, 0, 0);
        panelFormulario.add(btnLogin, gbc);

        add(panelFormulario, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void inicializarLogica() {
        btnLogin.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        UsuarioEntity usuario = controller.iniciarSesion(
                txtUsuario.getText(), new String(txtPassword.getPassword()));
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Credenciales invalidas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SwingUtilities.getWindowAncestor(this).dispose();
        NavegacionService navegacion = new NavegacionService();
        javax.swing.JFrame frame = new javax.swing.JFrame("Agricola - " + AuthService.getRolNombre());
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(navegacion.determinarHomeInicial());
        frame.setSize(960, 640);
        frame.setMinimumSize(new java.awt.Dimension(960, 640));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Distribuidora Agricola");

        lblUsuario.setText("Usuario:");

        lblPassword.setText("Contrasena:");

        btnLogin.setText("Ingresar");

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
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
