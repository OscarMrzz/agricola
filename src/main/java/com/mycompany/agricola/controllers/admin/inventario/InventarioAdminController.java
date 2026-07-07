package com.mycompany.agricola.controllers.admin.inventario;

import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.AlertasController;
import com.mycompany.agricola.controllers.inventario.InventarioListadoController;
import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.inventario.InventarioAdminVista;
import com.mycompany.agricola.views.util.UiUtil;

public class InventarioAdminController {

    private static final String[] COLUMNAS = {
        "No", "Producto", "Stock", "Minimo", "Prox. vencimiento", "Por vencer", "Vencido"
    };

    private final InventarioListadoController inventarioListado = new InventarioListadoController();
    private final NavegacionService navegacion = new NavegacionService();
    private final FormularioEditarInventarioController formularioEditarController = new FormularioEditarInventarioController();
    private final InventarioRetiroController retiroController = new InventarioRetiroController();
    private final AlertasController alertasController = new AlertasController();

    private List<InventarioEntity> inventarioCache = new ArrayList<>();

    public void abrir(Component parent) {
        InventarioAdminVista vista = new InventarioAdminVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista, parent);
        navegacion.abrirVistaSiPermitida("InventarioAdminVista", vista, parent);
    }

    private void inicializarVista(InventarioAdminVista vista) {
        vista.tablaInventario.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
        refrescarTabla(vista);
    }

    private void cargarFuncionalidades(InventarioAdminVista vista, Component parent) {
        vista.botonEditar.addActionListener(e -> editar(vista, parent));
        vista.botonRetirar.addActionListener(e -> retirar(vista));
        vista.botonAlertar.addActionListener(e -> alertasController.abrir(parent));
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    public List<InventarioEntity> listarInventario() {
        return inventarioListado.listarInventario();
    }

    private void volver(InventarioAdminVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void refrescarTabla(InventarioAdminVista vista) {
        inventarioCache = listarInventario();
        inventarioListado.refrescarTabla(vista.tablaInventario);
    }

    private void editar(InventarioAdminVista vista, Component parent) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaInventario);
        if (fila >= 0 && fila < inventarioCache.size()) {
            int idProducto = inventarioCache.get(fila).getIdProducto();
            formularioEditarController.abrir(idProducto, parent);
        }
    }

    private void retirar(InventarioAdminVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaInventario);
        if (fila < 0 || fila >= inventarioCache.size()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para retirar");
            return;
        }
        InventarioEntity seleccionado = inventarioCache.get(fila);
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(vista);
        retiroController.abrir(owner, seleccionado, () -> refrescarTabla(vista));
    }
}
