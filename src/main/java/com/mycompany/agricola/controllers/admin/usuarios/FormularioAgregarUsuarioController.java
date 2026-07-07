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
import com.mycompany.agricola.views.admin.usuarios.FormularioAgregarUsuarioVista;

public class FormularioAgregarUsuarioController {

    private final UsuarioDaoApl usuarioDao = new UsuarioDaoApl();
    private final RolDaoApl rolDao = new RolDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    public void abrir(Component parent) {
        FormularioAgregarUsuarioVista vista = new FormularioAgregarUsuarioVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista);
        navegacion.abrirFrame(vista, "Agregar Usuario");
    }

    private void inicializarVista(FormularioAgregarUsuarioVista vista) {
        vista.comboboxRol.removeAllItems();
        for (RolEntity rol : listarRoles()) {
            vista.comboboxRol.addItem(rol);
        }
    }

    private void cargarFuncionalidades(FormularioAgregarUsuarioVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    public List<RolEntity> listarRoles() {
        return rolDao.getAll();
    }

    public ResultadoPersistencia crear(UsuarioEntity usuario) {
        if (usuario.getNombreUser() == null || usuario.getNombreUser().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre de usuario es obligatorio"), "crear el usuario");
        }
        if (usuario.getPasswordUser() == null || usuario.getPasswordUser().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La contrasena es obligatoria"), "crear el usuario");
        }
        return usuarioDao.create(usuario);
    }

    private void cancelar(FormularioAgregarUsuarioVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioAgregarUsuarioVista vista) {
        RolEntity rol = (RolEntity) vista.comboboxRol.getSelectedItem();
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombreUser(vista.inputNombre.getText().trim());
        usuario.setPasswordUser(new String(vista.inputPassword.getPassword()));
        usuario.setIdForaneaRol(rol != null ? rol.getIdRol() : 0);
        usuario.setEstado(vista.checkboxActivo.isSelected());

        ResultadoPersistencia resultado = crear(usuario);
        if (resultado.isExito()) {
            JOptionPane.showMessageDialog(vista, "Usuario creado correctamente");
            cancelar(vista);
        } else {
            JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
