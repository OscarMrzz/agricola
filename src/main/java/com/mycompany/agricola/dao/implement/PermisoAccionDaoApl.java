package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.PermisoAccionDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.PermisoAccionEntity;

public class PermisoAccionDaoApl implements PermisoAccionDao {

    @Override
    public List<PermisoAccionEntity> getAll() {
        List<PermisoAccionEntity> permisos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM permisos_accion")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                permisos.add(mapearPermisoAccion(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return permisos;
    }

    @Override
    public List<PermisoAccionEntity> getByRol(int idRol) {
        List<PermisoAccionEntity> permisos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM permisos_accion WHERE id_foranea_rol = ?")) {
            statement.setInt(1, idRol);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                permisos.add(mapearPermisoAccion(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return permisos;
    }

    @Override
    public ResultadoPersistencia create(PermisoAccionEntity permiso) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO permisos_accion (id_foranea_rol, tabla, accion, estado) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, permiso.getIdForaneaRol());
            statement.setString(2, permiso.getTabla());
            statement.setString(3, permiso.getAccion());
            statement.setBoolean(4, permiso.isEstado());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el permiso de accion");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(PermisoAccionEntity permiso) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE permisos_accion SET id_foranea_rol = ?, tabla = ?, accion = ?, estado = ? WHERE id_permiso_accion = ?")) {
            statement.setInt(1, permiso.getIdForaneaRol());
            statement.setString(2, permiso.getTabla());
            statement.setString(3, permiso.getAccion());
            statement.setBoolean(4, permiso.isEstado());
            statement.setInt(5, permiso.getIdPermisoAccion());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el permiso de accion");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM permisos_accion WHERE id_permiso_accion = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el permiso de accion");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    private PermisoAccionEntity mapearPermisoAccion(ResultSet rs) throws SQLException {
        PermisoAccionEntity p = new PermisoAccionEntity();
        p.setIdPermisoAccion(rs.getInt("id_permiso_accion"));
        p.setIdForaneaRol(rs.getInt("id_foranea_rol"));
        p.setTabla(rs.getString("tabla"));
        p.setAccion(rs.getString("accion"));
        p.setEstado(rs.getBoolean("estado"));
        return p;
    }
}
