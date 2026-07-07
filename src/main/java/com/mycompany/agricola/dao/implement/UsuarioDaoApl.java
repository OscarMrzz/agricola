package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.UsuarioDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.UsuarioEntity;

public class UsuarioDaoApl implements UsuarioDao {

    @Override
    public List<UsuarioEntity> getAll() {
        List<UsuarioEntity> usuarios = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM [user]")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return usuarios;
    }

    @Override
    public UsuarioEntity getById(int id) {
        UsuarioEntity usuario = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM [user] WHERE id_user = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                usuario = mapearUsuario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public UsuarioEntity getByNombre(String nombreUser) {
        UsuarioEntity usuario = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM [user] WHERE nombre_user = ?")) {
            statement.setString(1, nombreUser);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                usuario = mapearUsuario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public ResultadoPersistencia create(UsuarioEntity usuario) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO [user] (nombre_user, password_user, id_foranea_rol, estado) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, usuario.getNombreUser());
            statement.setString(2, usuario.getPasswordUser());
            statement.setInt(3, usuario.getIdForaneaRol());
            statement.setBoolean(4, usuario.isEstado());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el usuario");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(UsuarioEntity usuario) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE [user] SET nombre_user = ?, password_user = ?, id_foranea_rol = ?, estado = ? WHERE id_user = ?")) {
            statement.setString(1, usuario.getNombreUser());
            statement.setString(2, usuario.getPasswordUser());
            statement.setInt(3, usuario.getIdForaneaRol());
            statement.setBoolean(4, usuario.isEstado());
            statement.setInt(5, usuario.getIdUser());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el usuario");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM [user] WHERE id_user = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el usuario");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    private UsuarioEntity mapearUsuario(ResultSet rs) throws SQLException {
        UsuarioEntity u = new UsuarioEntity();
        u.setIdUser(rs.getInt("id_user"));
        u.setNombreUser(rs.getString("nombre_user"));
        u.setPasswordUser(rs.getString("password_user"));
        u.setIdForaneaRol(rs.getInt("id_foranea_rol"));
        u.setEstado(rs.getBoolean("estado"));
        return u;
    }
}
