package com.mycompany.agricola.controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.model.entity.UsuarioEntity;
import com.mycompany.agricola.services.AuthService;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.LoginVista;

public class LoginController {

    private final AuthService authService = new AuthService();
    private final NavegacionService navegacion = new NavegacionService();

    public LoginVista crearVista() {
        LoginVista vista = new LoginVista();
        cargarFuncionalidades(vista);
        return vista;
    }

    public UsuarioEntity iniciarSesion(String usuario, String password) {
        if (usuario == null || usuario.isBlank() || password == null || password.isBlank()) {
            return null;
        }
        return authService.login(usuario.trim(), password);
    }

    public void cerrarSesion() {
        authService.logout();
    }

    private void cargarFuncionalidades(LoginVista vista) {
        vista.botonLogin.addActionListener(e -> iniciarSesion(vista));
    }

    private void iniciarSesion(LoginVista vista) {
        UsuarioEntity usuario = iniciarSesion(
                vista.inputUsuario.getText(), new String(vista.inputPassword.getPassword()));
        if (usuario == null) {
            JOptionPane.showMessageDialog(vista, "Credenciales invalidas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SwingUtilities.getWindowAncestor(vista).dispose();
        JFrame frame = new JFrame("Agricola - " + AuthService.getRolNombre());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(navegacion.determinarHomeInicial());
        frame.setSize(960, 640);
        frame.setMinimumSize(new java.awt.Dimension(960, 640));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
