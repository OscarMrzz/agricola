package com.mycompany.agricola.model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.model.dao.interfaces.VentaDao;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.FacturaVentaEntity;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;

public class VentaDaoApl implements VentaDao {

    @Override
    public List<VentaEntity> getAll() {
        List<VentaEntity> ventas = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ventas")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ventas.add(mapearVenta(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return ventas;
    }

    @Override
    public VentaEntity getById(int id) {
        VentaEntity venta = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM ventas WHERE id_venta = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                venta = mapearVenta(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return venta;
    }

    @Override
    public ResultadoPersistencia create(VentaEntity venta) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO ventas (id_foranea_vendedor, id_foranea_producto, no_factura, fecha_venta, id_cliente, cantidad_producto, tipo, metodo_pago, precio_antes_impuesto, impuesto, total_pagar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, venta.getIdForaneaVendedor());
            statement.setInt(2, venta.getIdForaneaProducto());
            statement.setString(3, venta.getNoFactura());
            LocalDateTime fechaVenta = venta.getFechaVenta() != null
                    ? venta.getFechaVenta() : LocalDateTime.now();
            statement.setTimestamp(4, Timestamp.valueOf(fechaVenta));
            statement.setInt(5, venta.getIdCliente());
            statement.setInt(6, venta.getCantidadProducto());
            statement.setString(7, venta.getTipo());
            statement.setString(8, venta.getMetodoPago());
            statement.setBigDecimal(9, venta.getPrecioAntesImpuesto());
            statement.setBigDecimal(10, venta.getImpuesto());
            statement.setBigDecimal(11, venta.getTotalPagar());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear la venta");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(VentaEntity venta) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE ventas SET id_foranea_vendedor = ?, id_foranea_producto = ?, no_factura = ?, fecha_venta = ?, id_cliente = ?, cantidad_producto = ?, tipo = ?, metodo_pago = ?, precio_antes_impuesto = ?, impuesto = ?, total_pagar = ? WHERE id_venta = ?")) {
            statement.setInt(1, venta.getIdForaneaVendedor());
            statement.setInt(2, venta.getIdForaneaProducto());
            statement.setString(3, venta.getNoFactura());
            statement.setTimestamp(4, Timestamp.valueOf(venta.getFechaVenta()));
            statement.setInt(5, venta.getIdCliente());
            statement.setInt(6, venta.getCantidadProducto());
            statement.setString(7, venta.getTipo());
            statement.setString(8, venta.getMetodoPago());
            statement.setBigDecimal(9, venta.getPrecioAntesImpuesto());
            statement.setBigDecimal(10, venta.getImpuesto());
            statement.setBigDecimal(11, venta.getTotalPagar());
            statement.setInt(12, venta.getIdVenta());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar la venta");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM ventas WHERE id_venta = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar la venta");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia deleteByFactura(String noFactura) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM ventas WHERE no_factura = ?")) {
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
    public List<FacturaVentaEntity> getAllFacturas() {
        List<FacturaVentaEntity> facturas = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_facturas_venta ORDER BY fecha_venta DESC")) {
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
    public List<VentasDetalleEntity> getAllDetalle() {
        List<VentasDetalleEntity> detalles = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_ventas_detalle")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                detalles.add(mapearVentaDetalle(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return detalles;
    }

    @Override
    public List<VentasDetalleEntity> getDetalleByFactura(String noFactura) {
        List<VentasDetalleEntity> detalles = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_ventas_detalle WHERE no_factura = ?")) {
            statement.setString(1, noFactura);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                detalles.add(mapearVentaDetalle(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return detalles;
    }

    private FacturaVentaEntity mapearFactura(ResultSet rs) throws SQLException {
        FacturaVentaEntity f = new FacturaVentaEntity();
        f.setNoFactura(rs.getString("no_factura"));
        Timestamp fv = rs.getTimestamp("fecha_venta");
        if (fv != null) {
            f.setFechaVenta(fv.toLocalDateTime());
        }
        f.setCliente(rs.getString("cliente"));
        f.setSubtotal(rs.getBigDecimal("subtotal"));
        f.setImpuesto(rs.getBigDecimal("impuesto"));
        f.setTotal(rs.getBigDecimal("total"));
        return f;
    }

    private VentaEntity mapearVenta(ResultSet rs) throws SQLException {
        VentaEntity v = new VentaEntity();
        v.setIdVenta(rs.getInt("id_venta"));
        v.setIdForaneaVendedor(rs.getInt("id_foranea_vendedor"));
        v.setIdForaneaProducto(rs.getInt("id_foranea_producto"));
        v.setNoFactura(rs.getString("no_factura"));
        Timestamp fv = rs.getTimestamp("fecha_venta");
        if (fv != null) {
            v.setFechaVenta(fv.toLocalDateTime());
        }
        v.setIdCliente(rs.getInt("id_cliente"));
        v.setCantidadProducto(rs.getInt("cantidad_producto"));
        v.setTipo(rs.getString("tipo"));
        v.setMetodoPago(rs.getString("metodo_pago"));
        v.setPrecioAntesImpuesto(rs.getBigDecimal("precio_antes_impuesto"));
        v.setImpuesto(rs.getBigDecimal("impuesto"));
        v.setTotalPagar(rs.getBigDecimal("total_pagar"));
        return v;
    }

    private VentasDetalleEntity mapearVentaDetalle(ResultSet rs) throws SQLException {
        VentasDetalleEntity d = new VentasDetalleEntity();
        d.setIdVenta(rs.getInt("id_venta"));
        d.setNoFactura(rs.getString("no_factura"));
        d.setCliente(rs.getString("cliente"));
        Timestamp fv = rs.getTimestamp("fecha_venta");
        if (fv != null) {
            d.setFechaVenta(fv.toLocalDateTime());
        }
        d.setNombreProducto(rs.getString("nombre_producto"));
        d.setCantidadProducto(rs.getInt("cantidad_producto"));
        d.setMetodoPago(rs.getString("metodo_pago"));
        d.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
        d.setSubtotal(rs.getBigDecimal("subtotal"));
        d.setImpuesto(rs.getBigDecimal("impuesto"));
        d.setTotalAPagar(rs.getBigDecimal("total_pagar"));
        return d;
    }
}
