package com.mycompany.agricola.controllers.admin.usuarios;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.RolDaoApl;
import com.mycompany.agricola.model.dao.implement.UsuarioDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.RolEntity;
import com.mycompany.agricola.model.entity.UsuarioEntity;

public class FormularioEditarUsuarioController {

    private final UsuarioDaoApl usuarioDao = new UsuarioDaoApl();
    private final RolDaoApl rolDao = new RolDaoApl();

    public UsuarioEntity obtenerPorId(int id) {
        return usuarioDao.getById(id);
    }

    public List<RolEntity> listarRoles() {
        return rolDao.getAll();
    }

    public ResultadoPersistencia actualizar(UsuarioEntity usuario) {
        if (usuario.getNombreUser() == null || usuario.getNombreUser().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre de usuario es obligatorio"), "actualizar el usuario");
        }
        return usuarioDao.update(usuario);
    }
}
