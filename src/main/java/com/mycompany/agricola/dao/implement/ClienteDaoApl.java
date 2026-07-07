package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.ClienteDao;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;

public class ClienteDaoApl implements ClienteDao {

    @Override
    public List<ClienteEntity> getAll() {
        List<ClienteEntity> clientes = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return clientes;
    }

    @Override
    public ClienteEntity getById(int id) {
        ClienteEntity cliente = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM clientes WHERE id_cliente = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                cliente = mapearCliente(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public ResultadoPersistencia create(ClienteEntity cliente) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO clientes (nombre_cliente, apellido_cliente, estado, limite_credito) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, cliente.getNombreCliente());
            statement.setString(2, cliente.getApellidoCliente());
            statement.setBoolean(3, cliente.isEstado());
            statement.setBigDecimal(4, cliente.getLimiteCredito());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "crear el cliente");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia update(ClienteEntity cliente) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE clientes SET nombre_cliente = ?, apellido_cliente = ?, estado = ?, limite_credito = ? WHERE id_cliente = ?")) {
            statement.setString(1, cliente.getNombreCliente());
            statement.setString(2, cliente.getApellidoCliente());
            statement.setBoolean(3, cliente.isEstado());
            statement.setBigDecimal(4, cliente.getLimiteCredito());
            statement.setInt(5, cliente.getIdCliente());
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "actualizar el cliente");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public ResultadoPersistencia delete(int id) {
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM clientes WHERE id_cliente = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return ResultadoPersistencia.exito();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultadoPersistencia.error(e, "eliminar el cliente");
        } finally {
            ConexionDB.cerrarConexion();
        }
    }

    @Override
    public List<CreditosClientesDetalleEntity> getAllCreditosDetalle() {
        List<CreditosClientesDetalleEntity> creditos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_creditos_clientes")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                creditos.add(mapearCreditoDetalle(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return creditos;
    }

    @Override
    public CreditosClientesDetalleEntity getCreditoDetalleByCliente(int idCliente) {
        CreditosClientesDetalleEntity credito = null;
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_creditos_clientes WHERE id_cliente = ?")) {
            statement.setInt(1, idCliente);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                credito = mapearCreditoDetalle(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credito;
    }

    private ClienteEntity mapearCliente(ResultSet rs) throws SQLException {
        ClienteEntity c = new ClienteEntity();
        c.setIdCliente(rs.getInt("id_cliente"));
        c.setNombreCliente(rs.getString("nombre_cliente"));
        c.setApellidoCliente(rs.getString("apellido_cliente"));
        c.setEstado(rs.getBoolean("estado"));
        c.setLimiteCredito(rs.getBigDecimal("limite_credito"));
        return c;
    }

    private CreditosClientesDetalleEntity mapearCreditoDetalle(ResultSet rs) throws SQLException {
        CreditosClientesDetalleEntity c = new CreditosClientesDetalleEntity();
        c.setIdCliente(rs.getInt("id_cliente"));
        c.setNombreCliente(rs.getString("nombre_cliente"));
        c.setApellidoCliente(rs.getString("apellido_cliente"));
        c.setCreditoMaximo(rs.getBigDecimal("credito_maximo"));
        c.setCreditoActual(rs.getBigDecimal("credito_actual"));
        c.setDiferencia(rs.getBigDecimal("diferencia"));
        return c;
    }
}
