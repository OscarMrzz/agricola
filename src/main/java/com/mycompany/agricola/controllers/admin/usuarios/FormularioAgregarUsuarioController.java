package com.mycompany.agricola.controllers.admin.usuarios;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.RolDaoApl;
import com.mycompany.agricola.model.dao.implement.UsuarioDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.RolEntity;
import com.mycompany.agricola.model.entity.UsuarioEntity;

public class FormularioAgregarUsuarioController {

    private final UsuarioDaoApl usuarioDao = new UsuarioDaoApl();
    private final RolDaoApl rolDao = new RolDaoApl();

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
}
