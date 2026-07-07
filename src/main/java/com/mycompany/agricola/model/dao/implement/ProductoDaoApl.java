package com.mycompany.agricola.model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.model.dao.interfaces.ProductoDao;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class ProductoDaoApl implements ProductoDao {

    @Override
    public List<ProductoEntity> getAll() {
        List<ProductoEntity> productos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM productos")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return productos;
    }

    @Override
    public ProductoEntity getById(int id) {
        ProductoEntity producto = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM productos WHERE id_producto = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                producto = mapearProducto(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public ResultadoPersistencia create(ProductoEntity producto) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO productos (nombre_producto, categoria_producto, departamento_origen, precio_venta, fecha_vencimiento) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, producto.getNombreProducto());
            statement.setString(2, producto.getCategoriaProducto());
            statement.setString(3, producto.getDepartamentoOrigen());
            statement.setBigDecimal(4, producto.getPrecioVenta());
            statement.setTimestamp(5, Timestamp.valueOf(producto.getFechaVencimiento()));
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el producto");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(ProductoEntity producto) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE productos SET nombre_producto = ?, categoria_producto = ?, departamento_origen = ?, precio_venta = ?, fecha_vencimiento = ? WHERE id_producto = ?")) {
            statement.setString(1, producto.getNombreProducto());
            statement.setString(2, producto.getCategoriaProducto());
            statement.setString(3, producto.getDepartamentoOrigen());
            statement.setBigDecimal(4, producto.getPrecioVenta());
            statement.setTimestamp(5, Timestamp.valueOf(producto.getFechaVencimiento()));
            statement.setInt(6, producto.getIdProducto());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el producto");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM productos WHERE id_producto = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el producto");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public List<ProductoEntity> getAptosParaVenta() {
        List<ProductoEntity> productos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT p.* FROM productos p "
                                + "INNER JOIN vista_inventario vi ON p.id_producto = vi.id_producto "
                                + "WHERE vi.stock_vendible > 0")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return productos;
    }

    @Override
    public List<AdvertenciaVencimientoEntity> getProximosAVencer(int dias) {
        List<AdvertenciaVencimientoEntity> advertencias = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_advertencia_vencimiento WHERE dias_restantes <= ?")) {
            statement.setInt(1, dias);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                advertencias.add(mapearAdvertenciaVencimiento(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return advertencias;
    }

    private ProductoEntity mapearProducto(ResultSet rs) throws SQLException {
        ProductoEntity p = new ProductoEntity();
        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombreProducto(rs.getString("nombre_producto"));
        p.setCategoriaProducto(rs.getString("categoria_producto"));
        p.setDepartamentoOrigen(rs.getString("departamento_origen"));
        p.setPrecioVenta(rs.getBigDecimal("precio_venta"));
        Timestamp fv = rs.getTimestamp("fecha_vencimiento");
        if (fv != null) {
            p.setFechaVencimiento(fv.toLocalDateTime());
        }
        return p;
    }

    private AdvertenciaVencimientoEntity mapearAdvertenciaVencimiento(ResultSet rs) throws SQLException {
        AdvertenciaVencimientoEntity a = new AdvertenciaVencimientoEntity();
        a.setIdProducto(rs.getInt("id_producto"));
        a.setNombreProducto(rs.getString("nombre_producto"));
        Timestamp fv = rs.getTimestamp("fecha_vencimiento");
        if (fv != null) {
            a.setFechaVencimiento(fv.toLocalDateTime());
        }
        a.setDiasRestantes(rs.getInt("dias_restantes"));
        a.setCantidad(rs.getInt("cantidad"));
        return a;
    }
}
