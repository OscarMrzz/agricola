package com.mycompany.agricola.controllers.admin;

import java.awt.Component;

import com.mycompany.agricola.controllers.compras.HomeComprasController;
import com.mycompany.agricola.controllers.ventas.HomeVentasController;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.AlertasVista;
import com.mycompany.agricola.views.admin.clientes.ClientesAdminVista;
import com.mycompany.agricola.views.admin.inventario.InventarioAdminVista;
import com.mycompany.agricola.views.admin.productos.ProductosVista;
import com.mycompany.agricola.views.admin.usuarios.UsuariosVista;

public class HomeAdminController {

    private final NavegacionService navegacion = new NavegacionService();
    private final HomeVentasController ventasController = new HomeVentasController();
    private final HomeComprasController comprasController = new HomeComprasController();

    public void abrirListadoVentas(Component parent) {
        ventasController.abrirVentas(parent);
    }

    public void abrirNuevaVenta(Component parent) {
        ventasController.abrirNuevaVenta(parent);
    }

    public void abrirClientesCreditos(Component parent) {
        ventasController.abrirClientesCreditos(parent);
    }

    public void abrirInventarioVentas(Component parent) {
        ventasController.abrirInventario(parent);
    }

    public void abrirFactura(Component parent) {
        ventasController.abrirFactura(parent);
    }

    public void abrirListadoCompras(Component parent) {
        comprasController.abrirCompras(parent);
    }

    public void abrirNuevaCompra(Component parent) {
        comprasController.abrirNuevaCompra(parent);
    }

    public void abrirInventarioCompras(Component parent) {
        comprasController.abrirInventario(parent);
    }

    public void abrirUsuarios(Component parent) {
        navegacion.abrirVistaSiPermitida("UsuariosVista", new UsuariosVista(), parent);
    }

    public void abrirClientes(Component parent) {
        navegacion.abrirVistaSiPermitida("ClientesAdminVista", new ClientesAdminVista(), parent);
    }

    public void abrirProductos(Component parent) {
        navegacion.abrirVistaSiPermitida("ProductosVista", new ProductosVista(), parent);
    }

    public void abrirInventario(Component parent) {
        navegacion.abrirVistaSiPermitida("InventarioAdminVista", new InventarioAdminVista(), parent);
    }

    public void abrirAlertas(Component parent) {
        navegacion.abrirVistaSiPermitida("AlertasVista", new AlertasVista(), parent);
    }
}
