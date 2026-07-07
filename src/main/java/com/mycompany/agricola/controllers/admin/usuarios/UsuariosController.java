package com.mycompany.agricola.controllers.admin.usuarios;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.dao.implement.RolDaoApl;
import com.mycompany.agricola.model.dao.implement.UsuarioDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.UsuarioEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.usuarios.UsuariosVista;
import com.mycompany.agricola.views.util.UiUtil;

public class UsuariosController {

    private static final String[] COLUMNAS = {"No", "Nombre", "Rol", "Estatus"};

    private final UsuarioDaoApl usuarioDao = new UsuarioDaoApl();
    private final RolDaoApl rolDao = new RolDaoApl();
    private final NavegacionService navegacion = new NavegacionService();
    private final FormularioAgregarUsuarioController formularioAgregarController = new FormularioAgregarUsuarioController();
    private final FormularioEditarUsuarioController formularioEditarController = new FormularioEditarUsuarioController();

    private List<UsuarioEntity> usuariosCache = new ArrayList<>();

    public void abrir(Component parent) {
        UsuariosVista vista = new UsuariosVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista, parent);
        navegacion.abrirVistaSiPermitida("UsuariosVista", vista, parent);
    }

    private void inicializarVista(UsuariosVista vista) {
        vista.tablaUsuarios.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
        refrescarTabla(vista);
    }

    private void cargarFuncionalidades(UsuariosVista vista, Component parent) {
        vista.botonAgregar.addActionListener(e -> agregar(parent));
        vista.botonEditar.addActionListener(e -> editar(vista, parent));
        vista.botonEliminar.addActionListener(e -> eliminar(vista));
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    public List<UsuarioEntity> listarUsuarios() {
        return usuarioDao.getAll();
    }

    public UsuarioEntity obtenerPorId(int id) {
        return usuarioDao.getById(id);
    }

    public ResultadoPersistencia eliminar(int id) {
        return usuarioDao.delete(id);
    }

    public String obtenerNombreRol(int idRol) {
        var rol = rolDao.getById(idRol);
        return rol != null ? rol.getNombreRol() : String.valueOf(idRol);
    }

    private void agregar(Component parent) {
        formularioAgregarController.abrir(parent);
    }

    private void volver(UsuariosVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void refrescarTabla(UsuariosVista vista) {
        usuariosCache = listarUsuarios();
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (UsuarioEntity usuario : usuariosCache) {
            filas.add(new Object[]{
                no++,
                usuario.getNombreUser(),
                obtenerNombreRol(usuario.getIdForaneaRol()),
                usuario.isEstado() ? "Si" : "No"
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) vista.tablaUsuarios.getModel(), filas);
    }

    private void editar(UsuariosVista vista, Component parent) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaUsuarios);
        if (fila >= 0 && fila < usuariosCache.size()) {
            int id = usuariosCache.get(fila).getIdUser();
            formularioEditarController.abrir(id, parent);
        }
    }

    private void eliminar(UsuariosVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaUsuarios);
        if (fila >= 0 && fila < usuariosCache.size() && UiUtil.confirmarEliminar(vista)) {
            int id = usuariosCache.get(fila).getIdUser();
            ResultadoPersistencia resultado = eliminar(id);
            if (resultado.isExito()) {
                refrescarTabla(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
