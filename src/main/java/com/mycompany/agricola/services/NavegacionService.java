package com.mycompany.agricola.services;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mycompany.agricola.controllers.admin.HomeAdminController;
import com.mycompany.agricola.controllers.compras.HomeComprasController;
import com.mycompany.agricola.controllers.ventas.HomeVentasController;
import com.mycompany.agricola.views.util.UiUtil;

public class NavegacionService {

    private HomeAdminController homeAdminController;
    private HomeVentasController homeVentasController;
    private HomeComprasController homeComprasController;

    public JPanel determinarHomeInicial() {
        String rol = AuthService.getRolNombre();
        if (rol == null) {
            return getHomeVentasController().crearVista();
        }
        return switch (rol) {
            case "Administrador" -> getHomeAdminController().crearVista();
            case "Vendedor" -> getHomeVentasController().crearVista();
            case "Compras" -> getHomeComprasController().crearVista();
            default -> getHomeVentasController().crearVista();
        };
    }

    private HomeAdminController getHomeAdminController() {
        if (homeAdminController == null) {
            homeAdminController = new HomeAdminController();
        }
        return homeAdminController;
    }

    private HomeVentasController getHomeVentasController() {
        if (homeVentasController == null) {
            homeVentasController = new HomeVentasController();
        }
        return homeVentasController;
    }

    private HomeComprasController getHomeComprasController() {
        if (homeComprasController == null) {
            homeComprasController = new HomeComprasController();
        }
        return homeComprasController;
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
            UiUtil.abrirFrame(panel, titulo, 1120, 680);
        } else if (titulo != null && titulo.contains("Editar")) {
            UiUtil.abrirFrameFormulario(panel, titulo);
        } else {
            UiUtil.abrirFrame(panel, titulo);
        }
    }
}
