package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.CompraDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ComprasDetalleEntity;
import com.mycompany.agricola.model.entity.FacturaCompraEntity;

public class CompraDaoApl implements CompraDao {

    @Override
    public List<CompraEntity> getAll() {
        List<CompraEntity> compras = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM compras")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                compras.add(mapearCompra(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return compras;
    }

    @Override
    public CompraEntity getById(int id) {
        CompraEntity compra = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM compras WHERE id_compra = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                compra = mapearCompra(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compra;
    }

    @Override
    public ResultadoPersistencia create(CompraEntity compra) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO compras (id_foranea_producto, id_foranea_usuario, cantidad_compra, precio_compra, fecha_expiracion, fecha_compra, no_factura, metodo_pago) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, compra.getIdForaneaProducto());
            statement.setInt(2, compra.getIdForaneaUsuario());
            statement.setInt(3, compra.getCantidadCompra());
            statement.setBigDecimal(4, compra.getPrecioCompra());
            if (compra.getFechaExpiracion() != null) {
                statement.setTimestamp(5, Timestamp.valueOf(compra.getFechaExpiracion()));
            } else {
                statement.setNull(5, Types.TIMESTAMP);
            }
            LocalDateTime fechaCompra = compra.getFechaCompra() != null
                    ? compra.getFechaCompra() : LocalDateTime.now();
            statement.setTimestamp(6, Timestamp.valueOf(fechaCompra));
            if (compra.getNoFactura() != null) {
                statement.setString(7, compra.getNoFactura());
            } else {
                statement.setNull(7, Types.VARCHAR);
            }
            statement.setString(8, compra.getMetodoPago());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear la compra");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public int createReturningId(CompraEntity compra) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO compras (id_foranea_producto, id_foranea_usuario, cantidad_compra, precio_compra, fecha_expiracion, fecha_compra, no_factura, metodo_pago) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, compra.getIdForaneaProducto());
            statement.setInt(2, compra.getIdForaneaUsuario());
            statement.setInt(3, compra.getCantidadCompra());
            statement.setBigDecimal(4, compra.getPrecioCompra());
            if (compra.getFechaExpiracion() != null) {
                statement.setTimestamp(5, Timestamp.valueOf(compra.getFechaExpiracion()));
            } else {
                statement.setNull(5, Types.TIMESTAMP);
            }
            LocalDateTime fechaCompra = compra.getFechaCompra() != null
                    ? compra.getFechaCompra() : LocalDateTime.now();
            statement.setTimestamp(6, Timestamp.valueOf(fechaCompra));
            if (compra.getNoFactura() != null) {
                statement.setString(7, compra.getNoFactura());
            } else {
                statement.setNull(7, Types.VARCHAR);
            }
            statement.setString(8, compra.getMetodoPago());
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
    public ResultadoPersistencia update(CompraEntity compra) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE compras SET id_foranea_producto = ?, id_foranea_usuario = ?, cantidad_compra = ?, precio_compra = ?, fecha_expiracion = ?, fecha_compra = ?, no_factura = ?, metodo_pago = ? WHERE id_compra = ?")) {
            statement.setInt(1, compra.getIdForaneaProducto());
            statement.setInt(2, compra.getIdForaneaUsuario());
            statement.setInt(3, compra.getCantidadCompra());
            statement.setBigDecimal(4, compra.getPrecioCompra());
            if (compra.getFechaExpiracion() != null) {
                statement.setTimestamp(5, Timestamp.valueOf(compra.getFechaExpiracion()));
            } else {
                statement.setNull(5, Types.TIMESTAMP);
            }
            statement.setTimestamp(6, Timestamp.valueOf(compra.getFechaCompra()));
            if (compra.getNoFactura() != null) {
                statement.setString(7, compra.getNoFactura());
            } else {
                statement.setNull(7, Types.VARCHAR);
            }
            statement.setString(8, compra.getMetodoPago());
            statement.setInt(9, compra.getIdCompra());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar la compra");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM compras WHERE id_compra = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar la compra");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia deleteByFactura(String noFactura) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM compras WHERE no_factura = ?")) {
            statement.setString(1, noFactura);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar la factura");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public List<FacturaCompraEntity> getAllFacturas() {
        List<FacturaCompraEntity> facturas = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_facturas_compra ORDER BY fecha_compra DESC")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                facturas.add(mapearFactura(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return facturas;
    }

    @Override
    public List<ComprasDetalleEntity> getAllDetalle() {
        List<ComprasDetalleEntity> detalles = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_compras_detalle")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                detalles.add(mapearCompraDetalle(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return detalles;
    }

    @Override
    public List<ComprasDetalleEntity> getDetalleByFactura(String noFactura) {
        List<ComprasDetalleEntity> detalles = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_compras_detalle WHERE no_factura = ?")) {
            statement.setString(1, noFactura);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                detalles.add(mapearCompraDetalle(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return detalles;
    }

    @Override
    public ComprasDetalleEntity getByIdDetalle(int id) {
        ComprasDetalleEntity detalle = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_compras_detalle WHERE id_compra = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                detalle = mapearCompraDetalle(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalle;
    }

    private FacturaCompraEntity mapearFactura(ResultSet rs) throws SQLException {
        FacturaCompraEntity f = new FacturaCompraEntity();
        f.setNoFactura(rs.getString("no_factura"));
        Timestamp fc = rs.getTimestamp("fecha_compra");
        if (fc != null) {
            f.setFechaCompra(fc.toLocalDateTime());
        }
        f.setMetodoPago(rs.getString("metodo_pago"));
        f.setSubtotal(rs.getBigDecimal("subtotal"));
        f.setImpuesto(rs.getBigDecimal("impuesto"));
        f.setTotal(rs.getBigDecimal("total"));
        return f;
    }

    private CompraEntity mapearCompra(ResultSet rs) throws SQLException {
        CompraEntity c = new CompraEntity();
        c.setIdCompra(rs.getInt("id_compra"));
        c.setIdForaneaProducto(rs.getInt("id_foranea_producto"));
        c.setIdForaneaUsuario(rs.getInt("id_foranea_usuario"));
        c.setCantidadCompra(rs.getInt("cantidad_compra"));
        c.setPrecioCompra(rs.getBigDecimal("precio_compra"));
        Timestamp fe = rs.getTimestamp("fecha_expiracion");
        if (fe != null) {
            c.setFechaExpiracion(fe.toLocalDateTime());
        }
        Timestamp fc = rs.getTimestamp("fecha_compra");
        if (fc != null) {
            c.setFechaCompra(fc.toLocalDateTime());
        }
        c.setNoFactura(rs.getString("no_factura"));
        c.setMetodoPago(rs.getString("metodo_pago"));
        return c;
    }

    private ComprasDetalleEntity mapearCompraDetalle(ResultSet rs) throws SQLException {
        ComprasDetalleEntity d = new ComprasDetalleEntity();
        d.setIdCompra(rs.getInt("id_compra"));
        d.setNoFactura(rs.getString("no_factura"));
        Timestamp fc = rs.getTimestamp("fecha_compra");
        if (fc != null) {
            d.setFechaCompra(fc.toLocalDateTime());
        }
        d.setNombreProducto(rs.getString("nombre_producto"));
        d.setCantidadProducto(rs.getInt("cantidad_producto"));
        d.setMetodoPago(rs.getString("metodo_pago"));
        d.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
        d.setTotalAntesImpuesto(rs.getBigDecimal("total_antes_impuesto"));
        d.setImpuesto(rs.getBigDecimal("impuesto"));
        d.setTotalAPagar(rs.getBigDecimal("total_a_pagar"));
        return d;
    }
}
