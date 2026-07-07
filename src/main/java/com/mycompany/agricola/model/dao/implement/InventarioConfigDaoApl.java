package com.mycompany.agricola.model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.model.dao.interfaces.InventarioConfigDao;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioConfigEntity;

public class InventarioConfigDaoApl implements InventarioConfigDao {

    @Override
    public InventarioConfigEntity getByProductoId(int idProducto) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM inventario_config WHERE id_producto = ?")) {
            statement.setInt(1, idProducto);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return mapearConfig(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return null;
    }

    @Override
    public void asegurarConfig(int idProducto) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "IF NOT EXISTS (SELECT 1 FROM inventario_config WHERE id_producto = ?) "
                                + "INSERT INTO inventario_config (id_producto, stock_minimo) VALUES (?, 0)")) {
            statement.setInt(1, idProducto);
            statement.setInt(2, idProducto);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia actualizarStockMinimo(int idProducto, int stockMinimo) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE inventario_config SET stock_minimo = ? WHERE id_producto = ?")) {
            statement.setInt(1, stockMinimo);
            statement.setInt(2, idProducto);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el stock minimo");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public void actualizarFechaUltimaEntrada(int idProducto, LocalDateTime fecha) {
        actualizarFecha(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE inventario_config SET fecha_ultima_entrada = ? WHERE id_producto = ?")) {
                statement.setTimestamp(1, Timestamp.valueOf(fecha));
                statement.setInt(2, idProducto);
                statement.executeUpdate();
            }
        });
    }

    @Override
    public void actualizarFechaUltimaSalida(int idProducto, LocalDateTime fecha) {
        actualizarFecha(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE inventario_config SET fecha_ultima_salida = ? WHERE id_producto = ?")) {
                statement.setTimestamp(1, Timestamp.valueOf(fecha));
                statement.setInt(2, idProducto);
                statement.executeUpdate();
            }
        });
    }

    private void actualizarFecha(SqlConsumer consumer) {
        try {
            Connection connection = ConexionDB.getConexion();
            consumer.accept(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InventarioConfigEntity mapearConfig(ResultSet rs) throws SQLException {
        InventarioConfigEntity config = new InventarioConfigEntity();
        config.setIdProducto(rs.getInt("id_producto"));
        config.setStockMinimo(rs.getInt("stock_minimo"));
        Timestamp fe = rs.getTimestamp("fecha_ultima_entrada");
        if (fe != null) {
            config.setFechaUltimaEntrada(fe.toLocalDateTime());
        }
        Timestamp fs = rs.getTimestamp("fecha_ultima_salida");
        if (fs != null) {
            config.setFechaUltimaSalida(fs.toLocalDateTime());
        }
        return config;
    }

    @FunctionalInterface
    private interface SqlConsumer {
        void accept(Connection connection) throws Exception;
    }
}
