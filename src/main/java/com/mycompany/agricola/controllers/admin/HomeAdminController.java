package com.mycompany.agricola.controllers.admin;

import java.awt.Component;

import com.mycompany.agricola.controllers.AlertasController;
import com.mycompany.agricola.controllers.admin.clientes.ClientesAdminController;
import com.mycompany.agricola.controllers.admin.inventario.InventarioAdminController;
import com.mycompany.agricola.controllers.admin.productos.ProductosController;
import com.mycompany.agricola.controllers.admin.usuarios.UsuariosController;
import com.mycompany.agricola.controllers.compras.HomeComprasController;
import com.mycompany.agricola.controllers.ventas.HomeVentasController;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.HomeAdminVista;

public class HomeAdminController {

    private final NavegacionService navegacion = new NavegacionService();
    private final HomeVentasController ventasController = new HomeVentasController();
    private final HomeComprasController comprasController = new HomeComprasController();
    private final UsuariosController usuariosController = new UsuariosController();
    private final ClientesAdminController clientesController = new ClientesAdminController();
    private final ProductosController productosController = new ProductosController();
    private final InventarioAdminController inventarioController = new InventarioAdminController();
    private final AlertasController alertasController = new AlertasController();

    public HomeAdminVista crearVista() {
        HomeAdminVista vista = new HomeAdminVista();
        cargarFuncionalidades(vista);
        return vista;
    }

    private void cargarFuncionalidades(HomeAdminVista vista) {
        vista.botonVentas.addActionListener(e -> abrirListadoVentas(vista));
        vista.botonCompras.addActionListener(e -> abrirListadoCompras(vista));
        vista.botonUsuarios.addActionListener(e -> usuariosController.abrir(vista));
        vista.botonClientes.addActionListener(e -> clientesController.abrir(vista));
        vista.botonProductos.addActionListener(e -> productosController.abrir(vista));
        vista.botonInventario.addActionListener(e -> inventarioController.abrir(vista));
        vista.botonAlertar.addActionListener(e -> alertasController.abrir(vista));
    }

    public void abrirListadoVentas(Component parent) {
        ventasController.abrirVentas(parent);
    }

    public void abrirListadoCompras(Component parent) {
        comprasController.abrirCompras(parent);
    }
}
