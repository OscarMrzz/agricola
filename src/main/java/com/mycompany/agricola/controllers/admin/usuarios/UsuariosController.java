package com.mycompany.agricola.controllers.admin.usuarios;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.RolDaoApl;
import com.mycompany.agricola.model.dao.implement.UsuarioDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.UsuarioEntity;

public class UsuariosController {

    private final UsuarioDaoApl usuarioDao = new UsuarioDaoApl();
    private final RolDaoApl rolDao = new RolDaoApl();

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
}
