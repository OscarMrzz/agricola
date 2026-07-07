package com.mycompany.agricola.controllers.admin.clientes;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.dao.implement.ClienteDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;
import com.mycompany.agricola.services.CreditosClientesService;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.clientes.ClientesAdminVista;
import com.mycompany.agricola.views.util.UiUtil;

public class ClientesAdminController {

    private static final String[] COLUMNAS = {"No", "Nombre", "Apellido", "Estatus"};

    private final ClienteDaoApl clienteDao = new ClienteDaoApl();
    private final CreditosClientesService creditosService = new CreditosClientesService();
    private final NavegacionService navegacion = new NavegacionService();
    private final FormularioAgregarClienteController formularioAgregarController = new FormularioAgregarClienteController();
    private final FormularioEditarClienteController formularioEditarController = new FormularioEditarClienteController();

    private List<ClienteEntity> clientesCache = new ArrayList<>();

    public void abrir(Component parent) {
        ClientesAdminVista vista = new ClientesAdminVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista, parent);
        navegacion.abrirVistaSiPermitida("ClientesAdminVista", vista, parent);
    }

    private void inicializarVista(ClientesAdminVista vista) {
        vista.tablaClientes.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
        refrescarTabla(vista);
    }

    private void cargarFuncionalidades(ClientesAdminVista vista, Component parent) {
        vista.botonAgregar.addActionListener(e -> agregar(parent));
        vista.botonEditar.addActionListener(e -> editar(vista, parent));
        vista.botonEliminar.addActionListener(e -> eliminar(vista));
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    public List<ClienteEntity> listarClientes() {
        return clienteDao.getAll();
    }

    public List<CreditosClientesDetalleEntity> listarCreditosDetalle() {
        return creditosService.obtenerTodos();
    }

    public ClienteEntity obtenerPorId(int id) {
        return clienteDao.getById(id);
    }

    public ResultadoPersistencia eliminar(int id) {
        return clienteDao.delete(id);
    }

    private void agregar(Component parent) {
        formularioAgregarController.abrir(parent);
    }

    private void volver(ClientesAdminVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void refrescarTabla(ClientesAdminVista vista) {
        clientesCache = listarClientes();
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (ClienteEntity cliente : clientesCache) {
            filas.add(new Object[]{
                no++,
                cliente.getNombreCliente(),
                cliente.getApellidoCliente(),
                cliente.isEstado() ? "Si" : "No"
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) vista.tablaClientes.getModel(), filas);
    }

    private void editar(ClientesAdminVista vista, Component parent) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaClientes);
        if (fila >= 0 && fila < clientesCache.size()) {
            int id = clientesCache.get(fila).getIdCliente();
            formularioEditarController.abrir(id, parent);
        }
    }

    private void eliminar(ClientesAdminVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaClientes);
        if (fila >= 0 && fila < clientesCache.size() && UiUtil.confirmarEliminar(vista)) {
            int id = clientesCache.get(fila).getIdCliente();
            ResultadoPersistencia resultado = eliminar(id);
            if (resultado.isExito()) {
                refrescarTabla(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
