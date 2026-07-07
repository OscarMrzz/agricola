package com.mycompany.agricola.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.dao.interfaces.AlertaVencidoDao;
import com.mycompany.agricola.model.entity.AdvertenciaVencidoEntity;

public class AlertaVencidoDaoApl implements AlertaVencidoDao {

    @Override
    public List<AdvertenciaVencidoEntity> getVencidos() {
        List<AdvertenciaVencidoEntity> advertencias = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM vista_advertencia_vencidos")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AdvertenciaVencidoEntity alerta = new AdvertenciaVencidoEntity();
                alerta.setIdProducto(rs.getInt("id_producto"));
                alerta.setNombreProducto(rs.getString("nombre_producto"));
                alerta.setCantidad(rs.getInt("cantidad"));
                advertencias.add(alerta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConexionDB.cerrarConexion();
        }
        return advertencias;
    }
}
