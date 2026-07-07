package com.mycompany.agricola.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mycompany.agricola.model.dao.implement.PermisoVistaDaoApl;
import com.mycompany.agricola.model.dao.implement.RolDaoApl;
import com.mycompany.agricola.model.dao.implement.UsuarioDaoApl;
import com.mycompany.agricola.model.dao.implement.VistaDaoApl;
import com.mycompany.agricola.model.entity.PermisoVistaEntity;
import com.mycompany.agricola.model.entity.RolEntity;
import com.mycompany.agricola.model.entity.UsuarioEntity;
import com.mycompany.agricola.model.entity.VistaEntity;

public class AuthService {

    private static UsuarioEntity usuarioActual;
    private static List<String> vistasPermitidas = new ArrayList<>();
    private static String rolNombre;

    private final UsuarioDaoApl usuarioDao = new UsuarioDaoApl();
    private final PermisoVistaDaoApl permisoVistaDao = new PermisoVistaDaoApl();
    private final VistaDaoApl vistaDao = new VistaDaoApl();
    private final RolDaoApl rolDao = new RolDaoApl();

    public UsuarioEntity login(String nombreUser, String passwordUser) {
        UsuarioEntity usuario = usuarioDao.getByNombre(nombreUser);
        if (usuario == null || !usuario.isEstado()) {
            return null;
        }
        if (!usuario.getPasswordUser().equals(passwordUser)) {
            return null;
        }
        usuarioActual = usuario;
        cargarPermisosVista(usuario.getIdForaneaRol());
        return usuario;
    }

    private void cargarPermisosVista(int idRol) {
        vistasPermitidas = new ArrayList<>();
        RolEntity rol = rolDao.getById(idRol);
        rolNombre = rol != null ? rol.getNombreRol() : null;

        for (PermisoVistaEntity permiso : permisoVistaDao.getByRol(idRol)) {
            if (!permiso.isEstado()) {
                continue;
            }
            VistaEntity vista = vistaDao.getById(permiso.getIdForaneaVista());
            if (vista != null) {
                vistasPermitidas.add(vista.getNombreVista());
            }
        }
    }

    public void logout() {
        usuarioActual = null;
        vistasPermitidas = new ArrayList<>();
        rolNombre = null;
    }

    public static UsuarioEntity getUsuarioActual() {
        return usuarioActual;
    }

    public static List<String> getVistasPermitidas() {
        return Collections.unmodifiableList(vistasPermitidas);
    }

    public static boolean tienePermisoVista(String nombreVista) {
        return vistasPermitidas.contains(nombreVista);
    }

    public static String getRolNombre() {
        return rolNombre;
    }
}
