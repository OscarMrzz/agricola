package com.mycompany.agricola.controllers.ventas;

import java.awt.Component;

import com.mycompany.agricola.controllers.AlertasController;
import com.mycompany.agricola.controllers.inventario.InventarioListadoController;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.ventas.HomeVentasVista;
import com.mycompany.agricola.views.ventas.InventarioVentasVista;

public class HomeVentasController {

    private final NavegacionService navegacion = new NavegacionService();
    private final VentasController ventasController = new VentasController();
    private final FormularioAgregarVentaController formularioAgregarController = new FormularioAgregarVentaController();
    private final ClientesVentasController clientesVentasController = new ClientesVentasController();
    private final InventarioListadoController inventarioListadoController = new InventarioListadoController();
    private final AlertasController alertasController = new AlertasController();

    public HomeVentasVista crearVista() {
        HomeVentasVista vista = new HomeVentasVista();
        cargarFuncionalidades(vista);
        return vista;
    }

    private void cargarFuncionalidades(HomeVentasVista vista) {
        vista.botonVentas.addActionListener(e -> ventasController.abrir(vista));
        vista.botonNuevaVenta.addActionListener(e -> formularioAgregarController.abrir(vista));
        vista.botonClientes.addActionListener(e -> clientesVentasController.abrir(vista));
        vista.botonInventario.addActionListener(e -> abrirInventario(vista));
        vista.botonAlertar.addActionListener(e -> alertasController.abrir(vista));
    }

    public void abrirVentas(Component parent) {
        ventasController.abrir(parent);
    }

    public void abrirNuevaVenta(Component parent) {
        formularioAgregarController.abrir(parent);
    }

    public void abrirClientesCreditos(Component parent) {
        clientesVentasController.abrir(parent);
    }

    public void abrirInventario(Component parent) {
        InventarioVentasVista vista = new InventarioVentasVista();
        inventarioListadoController.abrirLectura(parent, "InventarioVentasVista", vista);
    }

    public void abrirAlertas(Component parent) {
        alertasController.abrir(parent);
    }
}
