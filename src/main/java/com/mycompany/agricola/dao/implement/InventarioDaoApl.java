package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.InventarioDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class InventarioDaoApl implements InventarioDao {

    private static final String CONSULTA_VISTA =
            "SELECT * FROM vista_inventario WHERE stock > 0 "
                    + "OR EXISTS (SELECT 1 FROM inventario_config ic WHERE ic.id_producto = vista_inventario.id_producto)";

    @Override
    public List<InventarioEntity> getAll() {
        List<InventarioEntity> inventarios = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(CONSULTA_VISTA)) {
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
    public InventarioEntity getById(int idProducto) {
        return getByProductoId(idProducto);
    }

    @Override
    public InventarioEntity getByProductoId(int idProducto) {
        InventarioEntity inventario = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_inventario WHERE id_producto = ?")) {
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
                        "INSERT INTO inventario_config (id_producto, stock_minimo) VALUES (?, ?)")) {
            statement.setInt(1, inventario.getIdProducto());
            statement.setInt(2, inventario.getStockMinimo());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear la configuracion de inventario");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(InventarioEntity inventario) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE inventario_config SET stock_minimo = ? WHERE id_producto = ?")) {
            statement.setInt(1, inventario.getStockMinimo());
            statement.setInt(2, inventario.getIdProducto());
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
        return ResultadoPersistencia.error(
                new UnsupportedOperationException("Eliminar inventario no soportado"),
                "eliminar el inventario");
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

    private InventarioEntity mapearInventario(ResultSet rs) throws SQLException {
        InventarioEntity i = new InventarioEntity();
        int idProducto = rs.getInt("id_producto");
        i.setIdInventario(idProducto);
        i.setIdProducto(idProducto);
        i.setNombreProducto(rs.getString("nombre_producto"));
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
        Timestamp pv = rs.getTimestamp("proximo_vencimiento");
        if (pv != null) {
            i.setProximoVencimiento(pv.toLocalDateTime());
        }
        i.setCantidadPorVencer(rs.getInt("cantidad_por_vencer"));
        i.setCantidadVencida(rs.getInt("cantidad_vencida"));
        i.setStockVendible(rs.getInt("stock_vendible"));
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
