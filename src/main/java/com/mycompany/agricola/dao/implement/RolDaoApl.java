package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.RolDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.RolEntity;

public class RolDaoApl implements RolDao {

    @Override
    public List<RolEntity> getAll() {
        List<RolEntity> roles = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM roles")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                roles.add(mapearRol(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return roles;
    }

    @Override
    public RolEntity getById(int id) {
        RolEntity rol = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM roles WHERE id_rol = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                rol = mapearRol(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rol;
    }

    @Override
    public ResultadoPersistencia create(RolEntity rol) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO roles (nombre_rol, estado_rol) VALUES (?, ?)")) {
            statement.setString(1, rol.getNombreRol());
            statement.setBoolean(2, rol.isEstadoRol());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el rol");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(RolEntity rol) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE roles SET nombre_rol = ?, estado_rol = ? WHERE id_rol = ?")) {
            statement.setString(1, rol.getNombreRol());
            statement.setBoolean(2, rol.isEstadoRol());
            statement.setInt(3, rol.getIdRol());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el rol");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM roles WHERE id_rol = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el rol");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    private RolEntity mapearRol(ResultSet rs) throws SQLException {
        RolEntity r = new RolEntity();
        r.setIdRol(rs.getInt("id_rol"));
        r.setNombreRol(rs.getString("nombre_rol"));
        r.setEstadoRol(rs.getBoolean("estado_rol"));
        return r;
    }
}
