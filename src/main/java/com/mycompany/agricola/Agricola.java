package com.mycompany.agricola;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.controllers.InicializacionController;
import com.mycompany.agricola.views.InicializacionVista;
import com.mycompany.agricola.views.LoginVista;
import com.mycompany.agricola.views.util.UiUtil;

public class Agricola {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Distribuidora Agricola");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            InicializacionController inicializacionController = new InicializacionController();
            if (inicializacionController.baseDeDatosInicializada()) {
                UiUtil.mostrarVistaEnFrame(frame, new LoginVista(), "Distribuidora Agricola - Login",
                        new Dimension(450, 200));
            } else {
                UiUtil.mostrarVistaEnFrame(frame, new InicializacionVista(frame),
                        "Distribuidora Agricola - Inicializacion", new Dimension(500, 220));
            }

            frame.setVisible(true);
        });
    }
}
