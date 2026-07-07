package com.mycompany.agricola.model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.model.dao.interfaces.VistaDao;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.VistaEntity;

public class VistaDaoApl implements VistaDao {

    @Override
    public List<VistaEntity> getAll() {
        List<VistaEntity> vistas = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM vistas")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                vistas.add(mapearVista(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return vistas;
    }

    @Override
    public VistaEntity getById(int id) {
        VistaEntity vista = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vistas WHERE id_vista = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                vista = mapearVista(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vista;
    }

    @Override
    public ResultadoPersistencia create(VistaEntity vista) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO vistas (nombre_vista) VALUES (?)")) {
            statement.setString(1, vista.getNombreVista());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear la vista");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(VistaEntity vista) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE vistas SET nombre_vista = ? WHERE id_vista = ?")) {
            statement.setString(1, vista.getNombreVista());
            statement.setInt(2, vista.getIdVista());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar la vista");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM vistas WHERE id_vista = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar la vista");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    private VistaEntity mapearVista(ResultSet rs) throws SQLException {
        VistaEntity v = new VistaEntity();
        v.setIdVista(rs.getInt("id_vista"));
        v.setNombreVista(rs.getString("nombre_vista"));
        return v;
    }
}
