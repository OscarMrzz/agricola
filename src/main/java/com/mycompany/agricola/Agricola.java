package com.mycompany.agricola;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.mycompany.agricola.controllers.InicializacionController;
import com.mycompany.agricola.controllers.LoginController;
import com.mycompany.agricola.views.util.UiUtil;

public class Agricola {

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Distribuidora Agricola");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            InicializacionController inicializacionController = new InicializacionController();
            LoginController loginController = new LoginController();
            if (inicializacionController.baseDeDatosInicializada()) {
                UiUtil.mostrarVistaEnFrame(frame, loginController.crearVista(), "Distribuidora Agricola - Login",
                        new Dimension(480, 300));
            } else {
                UiUtil.mostrarVistaEnFrame(frame, inicializacionController.crearVista(frame),
                        "Distribuidora Agricola - Inicializacion", new Dimension(520, 280));
            }

            frame.setVisible(true);
        });
    }
}
