package com.mycompany.agricola.controllers.ventas;

import java.awt.Component;

import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.AlertasVista;
import com.mycompany.agricola.views.ventas.ClientesVentasVista;
import com.mycompany.agricola.views.ventas.FormularioAgregarVentaVista;
import com.mycompany.agricola.views.ventas.InventarioVentasVista;
import com.mycompany.agricola.views.ventas.VentasVista;

public class HomeVentasController {

    private final NavegacionService navegacion = new NavegacionService();

    public void abrirVentas(Component parent) {
        navegacion.abrirVistaSiPermitida("VentasVista", new VentasVista(), parent);
    }

    public void abrirNuevaVenta(Component parent) {
        navegacion.abrirVistaSiPermitida("FormularioAgregarVentaVista", new FormularioAgregarVentaVista(), parent);
    }

    public void abrirClientesCreditos(Component parent) {
        navegacion.abrirVistaSiPermitida("ClientesVentasVista", new ClientesVentasVista(), parent);
    }

    public void abrirInventario(Component parent) {
        navegacion.abrirVistaSiPermitida("InventarioVentasVista", new InventarioVentasVista(), parent);
    }

    public void abrirAlertas(Component parent) {
        navegacion.abrirVistaSiPermitida("AlertasVista", new AlertasVista(), parent);
    }
}
