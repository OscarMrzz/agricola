package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.PagoCreditoClienteDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.PagoCreditoClienteEntity;

public class PagoCreditoClienteDaoApl implements PagoCreditoClienteDao {

    @Override
    public List<PagoCreditoClienteEntity> getAll() {
        List<PagoCreditoClienteEntity> pagos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM pago_credito_cliente")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                pagos.add(mapearPago(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return pagos;
    }

    @Override
    public List<PagoCreditoClienteEntity> getByCliente(int idCliente) {
        List<PagoCreditoClienteEntity> pagos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM pago_credito_cliente WHERE id_foranea_cliente = ?")) {
            statement.setInt(1, idCliente);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                pagos.add(mapearPago(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return pagos;
    }

    @Override
    public ResultadoPersistencia create(PagoCreditoClienteEntity pago) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO pago_credito_cliente (id_foranea_cliente, fecha_pago, cantidad) VALUES (?, ?, ?)")) {
            statement.setInt(1, pago.getIdForaneaCliente());
            LocalDateTime fechaPago = pago.getFechaPago() != null
                    ? pago.getFechaPago() : LocalDateTime.now();
            statement.setTimestamp(2, Timestamp.valueOf(fechaPago));
            statement.setBigDecimal(3, pago.getCantidad());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el pago de credito");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM pago_credito_cliente WHERE id_pago_credito = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el pago de credito");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    private PagoCreditoClienteEntity mapearPago(ResultSet rs) throws SQLException {
        PagoCreditoClienteEntity p = new PagoCreditoClienteEntity();
        p.setIdPagoCredito(rs.getInt("id_pago_credito"));
        p.setIdForaneaCliente(rs.getInt("id_foranea_cliente"));
        Timestamp fp = rs.getTimestamp("fecha_pago");
        if (fp != null) {
            p.setFechaPago(fp.toLocalDateTime());
        }
        p.setCantidad(rs.getBigDecimal("cantidad"));
        return p;
    }
}
