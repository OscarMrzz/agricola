package com.mycompany.agricola.services;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mycompany.agricola.views.admin.HomeAdminVista;
import com.mycompany.agricola.views.compras.HomeComprasVista;
import com.mycompany.agricola.views.util.UiUtil;
import com.mycompany.agricola.views.ventas.HomeVentasVista;

public class NavegacionService {

    public JPanel determinarHomeInicial() {
        String rol = AuthService.getRolNombre();
        if (rol == null) {
            return new HomeVentasVista();
        }
        return switch (rol) {
            case "Administrador" -> new HomeAdminVista();
            case "Vendedor" -> new HomeVentasVista();
            case "Compras" -> new HomeComprasVista();
            default -> new HomeVentasVista();
        };
    }

    public boolean abrirVistaSiPermitida(String nombreVista, JPanel panel) {
        return abrirVistaSiPermitida(nombreVista, panel, panel);
    }

    public boolean abrirVistaSiPermitida(String nombreVista, JPanel panel, Component parentMensaje) {
        if (!AuthService.tienePermisoVista(nombreVista)) {
            JOptionPane.showMessageDialog(parentMensaje,
                    "No tiene permiso para acceder a esta vista.",
                    "Acceso denegado",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        abrirFrame(panel, nombreVista);
        return true;
    }

    public void abrirFrame(JPanel panel, String titulo) {
        if (titulo != null && (titulo.contains("Agregar") || titulo.contains("Nueva"))) {
            UiUtil.abrirFrame(panel, titulo, 1100, 620);
        } else {
            UiUtil.abrirFrame(panel, titulo);
        }
    }
}
