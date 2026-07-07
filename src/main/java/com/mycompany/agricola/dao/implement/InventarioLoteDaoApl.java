package com.mycompany.agricola.dao.implement;

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
import com.mycompany.agricola.dao.interfaces.InventarioLoteDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioLoteEntity;

public class InventarioLoteDaoApl implements InventarioLoteDao {

    @Override
    public int insertarLote(int idProducto, int cantidad, LocalDateTime fechaVencimiento, Integer idCompra) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO inventario_lote (id_producto, cantidad, fecha_vencimiento, fecha_entrada, id_compra) "
                                + "VALUES (?, ?, ?, GETDATE(), ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, idProducto);
            statement.setInt(2, cantidad);
            statement.setTimestamp(3, Timestamp.valueOf(fechaVencimiento));
            if (idCompra != null) {
                statement.setInt(4, idCompra);
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return -1;
    }

    @Override
    public List<InventarioLoteEntity> listarLotesVendiblesFefo(int idProducto) {
        List<InventarioLoteEntity> lotes = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM inventario_lote WHERE id_producto = ? AND cantidad > 0 "
                                + "AND fecha_vencimiento >= GETDATE() ORDER BY fecha_vencimiento ASC")) {
            statement.setInt(1, idProducto);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lotes.add(mapearLote(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lotes;
    }

    @Override
    public List<InventarioLoteEntity> listarLotesParaRetiro(int idProducto) {
        List<InventarioLoteEntity> lotes = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM inventario_lote WHERE id_producto = ? AND cantidad > 0 "
                                + "ORDER BY CASE WHEN fecha_vencimiento < GETDATE() THEN 0 ELSE 1 END, "
                                + "fecha_vencimiento ASC")) {
            statement.setInt(1, idProducto);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lotes.add(mapearLote(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lotes;
    }

    @Override
    public void actualizarCantidadLote(int idLote, int nuevaCantidad) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE inventario_lote SET cantidad = ? WHERE id_lote = ?")) {
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idLote);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultadoPersistencia descontarParaVenta(int idProducto, int cantidad) {
        Connection connection = null;
        try {
            connection = ConexionDB.getConexion();
            connection.setAutoCommit(false);
            consumirCantidad(connection, idProducto, cantidad, listarLotesVendiblesFefoEnConexion(connection, idProducto),
                    null, null, null);
            actualizarSalidaEnConexion(connection, idProducto);
            connection.commit();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "descontar inventario para venta");
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia retirar(int idProducto, int cantidad, int idUsuario, String motivo) {
        Connection connection = null;
        try {
            connection = ConexionDB.getConexion();
            connection.setAutoCommit(false);
            consumirCantidad(connection, idProducto, cantidad,
                    listarLotesParaRetiroEnConexion(connection, idProducto),
                    idUsuario, motivo, idProducto);
            actualizarSalidaEnConexion(connection, idProducto);
            connection.commit();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "retirar inventario");
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ConexionDB.cerrarConexion();
        }
    }

    private void consumirCantidad(Connection connection, int idProducto, int cantidad,
            List<InventarioLoteEntity> lotes, Integer idUsuario, String motivo, Integer idProductoRetiro)
            throws SQLException {
        int pendiente = cantidad;
        for (InventarioLoteEntity lote : lotes) {
            if (pendiente <= 0) {
                break;
            }
            int tomar = Math.min(pendiente, lote.getCantidad());
            int nuevaCantidad = lote.getCantidad() - tomar;
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE inventario_lote SET cantidad = ? WHERE id_lote = ?")) {
                statement.setInt(1, nuevaCantidad);
                statement.setInt(2, lote.getIdLote());
                statement.executeUpdate();
            }
            if (idUsuario != null) {
                registrarRetiro(connection, idProductoRetiro, lote.getIdLote(), tomar, motivo, idUsuario);
            }
            pendiente -= tomar;
        }
        if (pendiente > 0) {
            throw new IllegalStateException("Stock insuficiente para completar la operacion");
        }
    }

    private void registrarRetiro(Connection connection, int idProducto, int idLote, int cantidad,
            String motivo, int idUsuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO retiro_inventario (id_producto, id_lote, cantidad, motivo, fecha_retiro, id_usuario) "
                        + "VALUES (?, ?, ?, ?, GETDATE(), ?)")) {
            statement.setInt(1, idProducto);
            statement.setInt(2, idLote);
            statement.setInt(3, cantidad);
            statement.setString(4, motivo);
            statement.setInt(5, idUsuario);
            statement.executeUpdate();
        }
    }

    private void actualizarSalidaEnConexion(Connection connection, int idProducto) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE inventario_config SET fecha_ultima_salida = GETDATE() WHERE id_producto = ?")) {
            statement.setInt(1, idProducto);
            statement.executeUpdate();
        }
    }

    private List<InventarioLoteEntity> listarLotesVendiblesFefoEnConexion(Connection connection, int idProducto)
            throws SQLException {
        List<InventarioLoteEntity> lotes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM inventario_lote WHERE id_producto = ? AND cantidad > 0 "
                        + "AND fecha_vencimiento >= GETDATE() ORDER BY fecha_vencimiento ASC")) {
            statement.setInt(1, idProducto);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lotes.add(mapearLote(rs));
            }
        }
        return lotes;
    }

    private List<InventarioLoteEntity> listarLotesParaRetiroEnConexion(Connection connection, int idProducto)
            throws SQLException {
        List<InventarioLoteEntity> lotes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM inventario_lote WHERE id_producto = ? AND cantidad > 0 "
                        + "ORDER BY CASE WHEN fecha_vencimiento < GETDATE() THEN 0 ELSE 1 END, "
                        + "fecha_vencimiento ASC")) {
            statement.setInt(1, idProducto);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lotes.add(mapearLote(rs));
            }
        }
        return lotes;
    }

    private InventarioLoteEntity mapearLote(ResultSet rs) throws SQLException {
        InventarioLoteEntity lote = new InventarioLoteEntity();
        lote.setIdLote(rs.getInt("id_lote"));
        lote.setIdProducto(rs.getInt("id_producto"));
        lote.setCantidad(rs.getInt("cantidad"));
        Timestamp fv = rs.getTimestamp("fecha_vencimiento");
        if (fv != null) {
            lote.setFechaVencimiento(fv.toLocalDateTime());
        }
        Timestamp fe = rs.getTimestamp("fecha_entrada");
        if (fe != null) {
            lote.setFechaEntrada(fe.toLocalDateTime());
        }
        int idCompra = rs.getInt("id_compra");
        if (!rs.wasNull()) {
            lote.setIdCompra(idCompra);
        }
        return lote;
    }
}
