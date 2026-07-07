package com.mycompany.agricola.controllers.compras;

import java.awt.Component;

import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.AlertasVista;
import com.mycompany.agricola.views.compras.ComprasVista;
import com.mycompany.agricola.views.compras.FormularioAgregarCompraVista;
import com.mycompany.agricola.views.compras.InventarioComprasVista;

public class HomeComprasController {

    private final NavegacionService navegacion = new NavegacionService();

    public void abrirCompras(Component parent) {
        navegacion.abrirVistaSiPermitida("ComprasVista", new ComprasVista(), parent);
    }

    public void abrirNuevaCompra(Component parent) {
        navegacion.abrirVistaSiPermitida("FormularioAgregarCompraVista", new FormularioAgregarCompraVista(), parent);
    }

    public void abrirInventario(Component parent) {
        navegacion.abrirVistaSiPermitida("InventarioComprasVista", new InventarioComprasVista(), parent);
    }

    public void abrirAlertas(Component parent) {
        navegacion.abrirVistaSiPermitida("AlertasVista", new AlertasVista(), parent);
    }
}
