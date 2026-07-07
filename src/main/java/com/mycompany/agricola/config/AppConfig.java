package com.mycompany.agricola.config;

public class AppConfig {

    public static final String DB_HOST = "localhost";
    public static final int DB_PORT = 1433;
    public static final String DB_NAME = "db_agricola";
    public static final String DB_USER = "oscar";
    public static final String DB_PASSWORD = "password12345678";

    public static final String JDBC_URL = "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT
            + ";databaseName=" + DB_NAME
            + ";encrypt=true;trustServerCertificate=true";

    public static final String MASTER_JDBC_URL = "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT
            + ";databaseName=master;encrypt=true;trustServerCertificate=true";

    public static final String SA_USER = "sa";
    public static final String SA_PASSWORD = "Password12345678!";

    public static final double ISV_PORCENTAJE = 0.15;
    public static final int DIAS_ALERTA_VENCIMIENTO = 30;

    private AppConfig() {
    }
}
