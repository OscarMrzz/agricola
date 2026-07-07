package com.mycompany.agricola.model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.model.dao.interfaces.InventarioDao;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class InventarioDaoApl implements InventarioDao {

    @Override
    public List<InventarioEntity> getAll() {
        List<InventarioEntity> inventarios = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventario")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                inventarios.add(mapearInventario(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return inventarios;
    }

    @Override
    public InventarioEntity getById(int id) {
        InventarioEntity inventario = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM inventario WHERE id_inventario = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                inventario = mapearInventario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventario;
    }

    @Override
    public InventarioEntity getByProductoId(int idProducto) {
        InventarioEntity inventario = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM inventario WHERE id_producto = ?")) {
            statement.setInt(1, idProducto);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                inventario = mapearInventario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventario;
    }

    @Override
    public ResultadoPersistencia create(InventarioEntity inventario) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO inventario (id_producto, fecha_ultima_entrada, fecha_ultima_salida, stock, stock_minimo) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, inventario.getIdProducto());
            setNullableTimestamp(statement, 2, inventario.getFechaUltimaEntrada());
            setNullableTimestamp(statement, 3, inventario.getFechaUltimaSalida());
            statement.setInt(4, inventario.getStock());
            statement.setInt(5, inventario.getStockMinimo());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el inventario");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(InventarioEntity inventario) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE inventario SET id_producto = ?, fecha_ultima_entrada = ?, fecha_ultima_salida = ?, stock = ?, stock_minimo = ? WHERE id_inventario = ?")) {
            statement.setInt(1, inventario.getIdProducto());
            setNullableTimestamp(statement, 2, inventario.getFechaUltimaEntrada());
            setNullableTimestamp(statement, 3, inventario.getFechaUltimaSalida());
            statement.setInt(4, inventario.getStock());
            statement.setInt(5, inventario.getStockMinimo());
            statement.setInt(6, inventario.getIdInventario());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el inventario");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM inventario WHERE id_inventario = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el inventario");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public List<AdvertenciaStockBajoEntity> getStockBajo() {
        List<AdvertenciaStockBajoEntity> advertencias = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_advertencia_stock_bajo")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                advertencias.add(mapearAdvertenciaStockBajo(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return advertencias;
    }

    private void setNullableTimestamp(PreparedStatement statement, int index,
            java.time.LocalDateTime fecha) throws SQLException {
        if (fecha != null) {
            statement.setTimestamp(index, Timestamp.valueOf(fecha));
        } else {
            statement.setNull(index, Types.TIMESTAMP);
        }
    }

    private InventarioEntity mapearInventario(ResultSet rs) throws SQLException {
        InventarioEntity i = new InventarioEntity();
        i.setIdInventario(rs.getInt("id_inventario"));
        i.setIdProducto(rs.getInt("id_producto"));
        Timestamp fe = rs.getTimestamp("fecha_ultima_entrada");
        if (fe != null) {
            i.setFechaUltimaEntrada(fe.toLocalDateTime());
        }
        Timestamp fs = rs.getTimestamp("fecha_ultima_salida");
        if (fs != null) {
            i.setFechaUltimaSalida(fs.toLocalDateTime());
        }
        i.setStock(rs.getInt("stock"));
        i.setStockMinimo(rs.getInt("stock_minimo"));
        return i;
    }

    private AdvertenciaStockBajoEntity mapearAdvertenciaStockBajo(ResultSet rs) throws SQLException {
        AdvertenciaStockBajoEntity a = new AdvertenciaStockBajoEntity();
        a.setIdProducto(rs.getInt("id_producto"));
        a.setNombreProducto(rs.getString("nombre_producto"));
        a.setStockActual(rs.getInt("stock_actual"));
        a.setDepartamentoOrigen(rs.getString("departamento_origen"));
        return a;
    }
}
