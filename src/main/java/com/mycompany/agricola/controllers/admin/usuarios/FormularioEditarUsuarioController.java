package com.mycompany.agricola.controllers.admin.usuarios;

import java.awt.Component;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.dao.implement.RolDaoApl;
import com.mycompany.agricola.dao.implement.UsuarioDaoApl;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.RolEntity;
import com.mycompany.agricola.model.entity.UsuarioEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.usuarios.FormularioEditarUsuarioVista;

public class FormularioEditarUsuarioController {

    private final UsuarioDaoApl usuarioDao = new UsuarioDaoApl();
    private final RolDaoApl rolDao = new RolDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    private UsuarioEntity usuario;

    public void abrir(int id, Component parent) {
        FormularioEditarUsuarioVista vista = new FormularioEditarUsuarioVista();
        usuario = obtenerPorId(id);
        if (usuario == null) {
            vista.panelFormulario.setVisible(false);
            vista.etiquetaNoEncontrado.setVisible(true);
        } else {
            inicializarVista(vista);
            cargarFuncionalidades(vista);
        }
        navegacion.abrirFrame(vista, "Editar Usuario");
    }

    private void inicializarVista(FormularioEditarUsuarioVista vista) {
        vista.comboboxRol.removeAllItems();
        for (RolEntity rol : listarRoles()) {
            vista.comboboxRol.addItem(rol);
        }
        vista.inputNombre.setText(usuario.getNombreUser());
        vista.inputPassword.setText(usuario.getPasswordUser());
        vista.checkboxActivo.setSelected(usuario.isEstado());
        seleccionarRol(vista, usuario.getIdForaneaRol());
    }

    private void cargarFuncionalidades(FormularioEditarUsuarioVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    public UsuarioEntity obtenerPorId(int id) {
        return usuarioDao.getById(id);
    }

    public List<RolEntity> listarRoles() {
        return rolDao.getAll();
    }

    public ResultadoPersistencia actualizar(UsuarioEntity usuarioActualizado) {
        if (usuarioActualizado.getNombreUser() == null || usuarioActualizado.getNombreUser().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre de usuario es obligatorio"), "actualizar el usuario");
        }
        return usuarioDao.update(usuarioActualizado);
    }

    private void seleccionarRol(FormularioEditarUsuarioVista vista, int idRol) {
        for (int i = 0; i < vista.comboboxRol.getItemCount(); i++) {
            RolEntity rol = (RolEntity) vista.comboboxRol.getItemAt(i);
            if (rol != null && rol.getIdRol() == idRol) {
                vista.comboboxRol.setSelectedIndex(i);
                return;
            }
        }
    }

    private void cancelar(FormularioEditarUsuarioVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioEditarUsuarioVista vista) {
        RolEntity rol = (RolEntity) vista.comboboxRol.getSelectedItem();
        usuario.setNombreUser(vista.inputNombre.getText().trim());
        usuario.setPasswordUser(new String(vista.inputPassword.getPassword()));
        usuario.setIdForaneaRol(rol != null ? rol.getIdRol() : usuario.getIdForaneaRol());
        usuario.setEstado(vista.checkboxActivo.isSelected());

        ResultadoPersistencia resultado = actualizar(usuario);
        if (resultado.isExito()) {
            JOptionPane.showMessageDialog(vista, "Usuario actualizado correctamente");
            cancelar(vista);
        } else {
            JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
