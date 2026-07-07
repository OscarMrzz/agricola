package com.mycompany.agricola.model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.model.dao.interfaces.PermisoVistaDao;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.PermisoVistaEntity;

public class PermisoVistaDaoApl implements PermisoVistaDao {

    @Override
    public List<PermisoVistaEntity> getAll() {
        List<PermisoVistaEntity> permisos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM permisos_vista")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                permisos.add(mapearPermisoVista(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return permisos;
    }

    @Override
    public List<PermisoVistaEntity> getByRol(int idRol) {
        List<PermisoVistaEntity> permisos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM permisos_vista WHERE id_foranea_rol = ?")) {
            statement.setInt(1, idRol);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                permisos.add(mapearPermisoVista(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return permisos;
    }

    @Override
    public ResultadoPersistencia create(PermisoVistaEntity permiso) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO permisos_vista (id_foranea_rol, id_foranea_vista, estado) VALUES (?, ?, ?)")) {
            statement.setInt(1, permiso.getIdForaneaRol());
            statement.setInt(2, permiso.getIdForaneaVista());
            statement.setBoolean(3, permiso.isEstado());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el permiso de vista");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(PermisoVistaEntity permiso) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE permisos_vista SET id_foranea_rol = ?, id_foranea_vista = ?, estado = ? WHERE id_permiso_vista = ?")) {
            statement.setInt(1, permiso.getIdForaneaRol());
            statement.setInt(2, permiso.getIdForaneaVista());
            statement.setBoolean(3, permiso.isEstado());
            statement.setInt(4, permiso.getIdPermisoVista());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el permiso de vista");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM permisos_vista WHERE id_permiso_vista = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el permiso de vista");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    private PermisoVistaEntity mapearPermisoVista(ResultSet rs) throws SQLException {
        PermisoVistaEntity p = new PermisoVistaEntity();
        p.setIdPermisoVista(rs.getInt("id_permiso_vista"));
        p.setIdForaneaRol(rs.getInt("id_foranea_rol"));
        p.setIdForaneaVista(rs.getInt("id_foranea_vista"));
        p.setEstado(rs.getBoolean("estado"));
        return p;
    }
}
