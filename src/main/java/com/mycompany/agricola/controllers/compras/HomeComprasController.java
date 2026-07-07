package com.mycompany.agricola.controllers.compras;

import java.awt.Component;

import com.mycompany.agricola.controllers.AlertasController;
import com.mycompany.agricola.controllers.inventario.InventarioListadoController;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.compras.HomeComprasVista;
import com.mycompany.agricola.views.compras.InventarioComprasVista;

public class HomeComprasController {

    private final NavegacionService navegacion = new NavegacionService();
    private final ComprasController comprasController = new ComprasController();
    private final FormularioAgregarCompraController formularioAgregarController = new FormularioAgregarCompraController();
    private final InventarioListadoController inventarioListadoController = new InventarioListadoController();
    private final AlertasController alertasController = new AlertasController();

    public HomeComprasVista crearVista() {
        HomeComprasVista vista = new HomeComprasVista();
        cargarFuncionalidades(vista);
        return vista;
    }

    private void cargarFuncionalidades(HomeComprasVista vista) {
        vista.botonCompras.addActionListener(e -> comprasController.abrir(vista));
        vista.botonNuevaCompra.addActionListener(e -> formularioAgregarController.abrir(vista));
        vista.botonInventario.addActionListener(e -> abrirInventario(vista));
        vista.botonAlertar.addActionListener(e -> alertasController.abrir(vista));
    }

    public void abrirCompras(Component parent) {
        comprasController.abrir(parent);
    }

    private void abrirInventario(Component parent) {
        InventarioComprasVista vista = new InventarioComprasVista();
        inventarioListadoController.abrirLectura(parent, "InventarioComprasVista", vista);
    }
}
