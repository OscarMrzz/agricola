package com.mycompany.agricola.model.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mycompany.agricola.config.AppConfig;

public class ConexionDB {

    private static Connection conexion = null;

    private ConexionDB() {
    }

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(
                        AppConfig.JDBC_URL, AppConfig.DB_USER, AppConfig.DB_PASSWORD);
                System.out.println("Conexion exitosa");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion cerrada exitosamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
