package com.mycompany.agricola.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mycompany.agricola.config.AppConfig;
import com.mycompany.agricola.services.db.MigracionService;
import com.mycompany.agricola.services.db.SeedService;

public class InicializacionService {

    public boolean baseDeDatosInicializada() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (!existeBaseDeDatos()) {
                return false;
            }
            try (Connection conn = DriverManager.getConnection(
                    AppConfig.JDBC_URL, AppConfig.DB_USER, AppConfig.DB_PASSWORD);
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(
                            "SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'roles'")) {
                return rs.next();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void iniciarConDatosEnBlanco() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        new MigracionService().ejecutar();
    }

    public void iniciarConDatosDePrueba() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        new MigracionService().ejecutar();
        new SeedService().ejecutar();
    }

    private boolean existeBaseDeDatos() throws Exception {
        try (Connection conn = DriverManager.getConnection(
                AppConfig.MASTER_JDBC_URL, AppConfig.SA_USER, AppConfig.SA_PASSWORD);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT 1 FROM sys.databases WHERE name = N'" + AppConfig.DB_NAME + "'")) {
            return rs.next();
        }
    }
}
