import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Crea la base de datos, tablas y vistas de db_agricola.
 * Ejecutar: java -cp "db;target/dependency/mssql-jdbc-*.jar" Migracion
 */
public class Migracion {

    private static final String MASTER_URL = "jdbc:sqlserver://localhost:1433;databaseName=master;encrypt=true;trustServerCertificate=true";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=db_agricola;encrypt=true;trustServerCertificate=true";
    private static final String SA_USER = "sa";
    private static final String SA_PASSWORD = "Password12345678!";

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            crearBaseYUsuario();
            try (Connection conn = DriverManager.getConnection(DB_URL, SA_USER, SA_PASSWORD)) {
                crearTablas(conn);
                crearVistas(conn);
                System.out.println("Migracion completada exitosamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void crearBaseYUsuario() throws SQLException {
        try (Connection conn = DriverManager.getConnection(MASTER_URL, SA_USER, SA_PASSWORD);
                Statement st = conn.createStatement()) {
            st.execute("IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'db_agricola') CREATE DATABASE db_agricola");
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, SA_USER, SA_PASSWORD);
                Statement st = conn.createStatement()) {
            st.execute("IF NOT EXISTS (SELECT name FROM sys.server_principals WHERE name = N'oscar') "
                    + "CREATE LOGIN oscar WITH PASSWORD = 'password12345678', CHECK_POLICY = OFF");
            st.execute("IF NOT EXISTS (SELECT name FROM sys.database_principals WHERE name = N'oscar') "
                    + "CREATE USER oscar FOR LOGIN oscar");
            st.execute("IF NOT EXISTS (SELECT 1 FROM sys.database_role_members drm "
                    + "JOIN sys.database_principals r ON drm.role_principal_id = r.principal_id "
                    + "JOIN sys.database_principals m ON drm.member_principal_id = m.principal_id "
                    + "WHERE r.name = N'db_owner' AND m.name = N'oscar') ALTER ROLE db_owner ADD MEMBER oscar");
        }
    }

    private static void crearTablas(Connection conn) throws SQLException {
        String[] sqls = {
            """
            IF OBJECT_ID('pago_credito_cliente', 'U') IS NOT NULL DROP TABLE pago_credito_cliente
            """,
            """
            IF OBJECT_ID('ventas', 'U') IS NOT NULL DROP TABLE ventas
            """,
            """
            IF OBJECT_ID('compras', 'U') IS NOT NULL DROP TABLE compras
            """,
            """
            IF OBJECT_ID('inventario', 'U') IS NOT NULL DROP TABLE inventario
            """,
            """
            IF OBJECT_ID('permisos_vista', 'U') IS NOT NULL DROP TABLE permisos_vista
            """,
            """
            IF OBJECT_ID('permisos_accion', 'U') IS NOT NULL DROP TABLE permisos_accion
            """,
            """
            IF OBJECT_ID('[user]', 'U') IS NOT NULL DROP TABLE [user]
            """,
            """
            IF OBJECT_ID('clientes', 'U') IS NOT NULL DROP TABLE clientes
            """,
            """
            IF OBJECT_ID('productos', 'U') IS NOT NULL DROP TABLE productos
            """,
            """
            IF OBJECT_ID('vistas', 'U') IS NOT NULL DROP TABLE vistas
            """,
            """
            IF OBJECT_ID('roles', 'U') IS NOT NULL DROP TABLE roles
            """,
            """
            CREATE TABLE roles (
                id_rol INT IDENTITY(1,1) PRIMARY KEY,
                nombre_rol VARCHAR(100) NOT NULL,
                estado_rol BIT NOT NULL DEFAULT 1
            )
            """,
            """
            CREATE TABLE vistas (
                id_vista INT IDENTITY(1,1) PRIMARY KEY,
                nombre_vista VARCHAR(100) NOT NULL UNIQUE
            )
            """,
            """
            CREATE TABLE [user] (
                id_user INT IDENTITY(1,1) PRIMARY KEY,
                nombre_user VARCHAR(100) NOT NULL UNIQUE,
                password_user VARCHAR(255) NOT NULL,
                id_foranea_rol INT NOT NULL,
                estado BIT NOT NULL DEFAULT 1,
                FOREIGN KEY (id_foranea_rol) REFERENCES roles(id_rol)
            )
            """,
            """
            CREATE TABLE permisos_accion (
                id_permiso_accion INT IDENTITY(1,1) PRIMARY KEY,
                id_foranea_rol INT NOT NULL,
                tabla VARCHAR(100) NOT NULL,
                accion VARCHAR(20) NOT NULL,
                estado BIT NOT NULL DEFAULT 1,
                FOREIGN KEY (id_foranea_rol) REFERENCES roles(id_rol)
            )
            """,
            """
            CREATE TABLE permisos_vista (
                id_permiso_vista INT IDENTITY(1,1) PRIMARY KEY,
                id_foranea_rol INT NOT NULL,
                id_foranea_vista INT NOT NULL,
                estado BIT NOT NULL DEFAULT 1,
                FOREIGN KEY (id_foranea_rol) REFERENCES roles(id_rol),
                FOREIGN KEY (id_foranea_vista) REFERENCES vistas(id_vista)
            )
            """,
            """
            CREATE TABLE productos (
                id_producto INT IDENTITY(1,1) PRIMARY KEY,
                nombre_producto VARCHAR(150) NOT NULL,
                categoria_producto VARCHAR(100) NOT NULL,
                departamento_origen VARCHAR(100) NOT NULL,
                precio_venta DECIMAL(18,2) NOT NULL,
                fecha_vencimiento DATETIME NOT NULL
            )
            """,
            """
            CREATE TABLE clientes (
                id_cliente INT IDENTITY(1,1) PRIMARY KEY,
                nombre_cliente VARCHAR(100) NOT NULL,
                apellido_cliente VARCHAR(100) NOT NULL,
                estado BIT NOT NULL DEFAULT 1,
                limite_credito DECIMAL(18,2) NOT NULL DEFAULT 0
            )
            """,
            """
            CREATE TABLE inventario (
                id_inventario INT IDENTITY(1,1) PRIMARY KEY,
                id_producto INT NOT NULL UNIQUE,
                fecha_ultima_entrada DATETIME NULL,
                fecha_ultima_salida DATETIME NULL,
                stock INT NOT NULL DEFAULT 0,
                stock_minimo INT NOT NULL DEFAULT 0,
                FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
            )
            """,
            """
            CREATE TABLE compras (
                id_compra INT IDENTITY(1,1) PRIMARY KEY,
                id_foranea_producto INT NOT NULL,
                id_foranea_usuario INT NOT NULL,
                cantidad_compra INT NOT NULL,
                precio_compra DECIMAL(18,2) NOT NULL,
                fecha_expiracion DATETIME NULL,
                fecha_compra DATETIME NOT NULL DEFAULT GETDATE(),
                no_factura VARCHAR(50) NULL,
                metodo_pago VARCHAR(20) NOT NULL,
                FOREIGN KEY (id_foranea_producto) REFERENCES productos(id_producto),
                FOREIGN KEY (id_foranea_usuario) REFERENCES [user](id_user)
            )
            """,
            """
            CREATE TABLE ventas (
                id_venta INT IDENTITY(1,1) PRIMARY KEY,
                id_foranea_vendedor INT NOT NULL,
                id_foranea_producto INT NOT NULL,
                no_factura VARCHAR(50) NOT NULL,
                fecha_venta DATETIME NOT NULL DEFAULT GETDATE(),
                id_cliente INT NOT NULL,
                cantidad_producto INT NOT NULL,
                tipo VARCHAR(20) NOT NULL,
                metodo_pago VARCHAR(20) NOT NULL,
                precio_antes_impuesto DECIMAL(18,2) NOT NULL,
                impuesto DECIMAL(18,2) NOT NULL,
                total_pagar DECIMAL(18,2) NOT NULL,
                FOREIGN KEY (id_foranea_vendedor) REFERENCES [user](id_user),
                FOREIGN KEY (id_foranea_producto) REFERENCES productos(id_producto),
                FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
            )
            """,
            """
            CREATE TABLE pago_credito_cliente (
                id_pago_credito INT IDENTITY(1,1) PRIMARY KEY,
                id_foranea_cliente INT NOT NULL,
                fecha_pago DATETIME NOT NULL DEFAULT GETDATE(),
                cantidad DECIMAL(18,2) NOT NULL,
                FOREIGN KEY (id_foranea_cliente) REFERENCES clientes(id_cliente)
            )
            """
        };
        try (Statement st = conn.createStatement()) {
            for (String sql : sqls) {
                st.execute(sql);
            }
        }
    }

    private static void crearVistas(Connection conn) throws SQLException {
        String[] sqls = {
            """
            CREATE OR ALTER VIEW vista_ventas_detalle AS
            SELECT v.id_venta, v.no_factura,
                CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS cliente,
                v.fecha_venta, p.nombre_producto, v.cantidad_producto, v.metodo_pago,
                v.precio_antes_impuesto AS precio_unitario,
                (v.precio_antes_impuesto * v.cantidad_producto) AS subtotal,
                v.impuesto, v.total_pagar
            FROM ventas v
            INNER JOIN clientes c ON v.id_cliente = c.id_cliente
            INNER JOIN productos p ON v.id_foranea_producto = p.id_producto
            """,
            """
            CREATE OR ALTER VIEW vista_compras_detalle AS
            SELECT co.id_compra, co.no_factura, co.fecha_compra, p.nombre_producto,
                co.cantidad_compra AS cantidad_producto, co.metodo_pago,
                co.precio_compra AS precio_unitario,
                (co.precio_compra * co.cantidad_compra) AS subtotal,
                (co.precio_compra * co.cantidad_compra) * 0.15 AS impuesto,
                (co.precio_compra * co.cantidad_compra) * 1.15 AS total_a_pagar
            FROM compras co
            INNER JOIN productos p ON co.id_foranea_producto = p.id_producto
            """,
            """
            CREATE OR ALTER VIEW vista_creditos_clientes AS
            SELECT c.id_cliente, c.nombre_cliente, c.apellido_cliente,
                c.limite_credito AS credito_maximo,
                ISNULL(SUM(CASE WHEN v.tipo = 'credito' THEN v.total_pagar ELSE 0 END), 0)
                    - ISNULL((SELECT SUM(pc.cantidad) FROM pago_credito_cliente pc WHERE pc.id_foranea_cliente = c.id_cliente), 0) AS credito_actual,
                c.limite_credito - (
                    ISNULL(SUM(CASE WHEN v.tipo = 'credito' THEN v.total_pagar ELSE 0 END), 0)
                    - ISNULL((SELECT SUM(pc.cantidad) FROM pago_credito_cliente pc WHERE pc.id_foranea_cliente = c.id_cliente), 0)
                ) AS diferencia
            FROM clientes c
            LEFT JOIN ventas v ON c.id_cliente = v.id_cliente
            GROUP BY c.id_cliente, c.nombre_cliente, c.apellido_cliente, c.limite_credito
            """,
            """
            CREATE OR ALTER VIEW vista_advertencia_vencimiento AS
            SELECT p.id_producto, p.nombre_producto, p.fecha_vencimiento,
                DATEDIFF(DAY, GETDATE(), p.fecha_vencimiento) AS dias_restantes
            FROM productos p
            WHERE p.fecha_vencimiento > GETDATE()
              AND DATEDIFF(DAY, GETDATE(), p.fecha_vencimiento) <= 30
            """,
            """
            CREATE OR ALTER VIEW vista_advertencia_stock_bajo AS
            SELECT p.id_producto, p.nombre_producto, i.stock AS stock_actual, p.departamento_origen
            FROM inventario i
            INNER JOIN productos p ON i.id_producto = p.id_producto
            WHERE i.stock < i.stock_minimo
            """
        };
        try (Statement st = conn.createStatement()) {
            for (String sql : sqls) {
                st.execute(sql);
            }
        }
    }
}
