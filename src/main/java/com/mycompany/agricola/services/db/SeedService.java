package com.mycompany.agricola.services.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.agricola.config.AppConfig;

public class SeedService {

    private static final String[] TODAS_LAS_VISTAS = {
        "HomeAdminVista",
        "HomeVentasVista", "VentasVista", "FormularioAgregarVentaVista", "FormularioEditarVentaVista",
        "ClientesVentasVista", "InventarioVentasVista",
        "HomeComprasVista", "ComprasVista", "FormularioAgregarCompraVista", "FormularioEditarCompraVista",
        "InventarioComprasVista",
        "UsuariosVista", "FormularioAgregarUsuarioVista", "FormularioEditarUsuarioVista",
        "ClientesAdminVista", "FormularioAgregarClienteVista", "FormularioEditarClienteVista",
        "ProductosVista", "FormularioAgregarProductoVista", "FormularioEditarProductoVista",
        "InventarioAdminVista", "FormularioEditarInventarioVista",
        "AlertasVista"
    };

    private static final String[] VISTAS_VENDEDOR = {
        "HomeVentasVista", "VentasVista", "FormularioAgregarVentaVista", "FormularioEditarVentaVista",
        "ClientesVentasVista", "InventarioVentasVista", "AlertasVista"
    };

    private static final String[] VISTAS_COMPRAS = {
        "HomeComprasVista", "ComprasVista", "FormularioAgregarCompraVista", "FormularioEditarCompraVista",
        "InventarioComprasVista", "AlertasVista"
    };

    public void ejecutar() throws Exception {
        try (Connection conn = DriverManager.getConnection(
                AppConfig.JDBC_URL, AppConfig.DB_USER, AppConfig.DB_PASSWORD)) {
            if (tieneDatos(conn)) {
                return;
            }
            int rolAdmin = insertarRol(conn, "Administrador");
            int rolVendedor = insertarRol(conn, "Vendedor");
            int rolCompras = insertarRol(conn, "Compras");

            Map<String, Integer> vistaIds = insertarVistas(conn);

            insertarPermisosVista(conn, rolAdmin, TODAS_LAS_VISTAS, vistaIds);
            insertarPermisosVista(conn, rolVendedor, VISTAS_VENDEDOR, vistaIds);
            insertarPermisosVista(conn, rolCompras, VISTAS_COMPRAS, vistaIds);

            insertarPermisosAccionAdmin(conn, rolAdmin);
            insertarPermisosAccionVendedor(conn, rolVendedor);
            insertarPermisosAccionCompras(conn, rolCompras);

            insertarUsuario(conn, "admin", "12345678", rolAdmin);
            insertarUsuario(conn, "vendedor1", "venta123", rolVendedor);
            insertarUsuario(conn, "compras1", "compra123", rolCompras);

            int[] productos = insertarProductos(conn);
            insertarClientes(conn);
            insertarInventario(conn, productos);
        }
    }

    private boolean tieneDatos(Connection conn) throws Exception {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM roles")) {
            rs.next();
            return rs.getInt("total") > 0;
        }
    }

    private int insertarRol(Connection conn, String nombre) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO roles (nombre_rol, estado_rol) VALUES (?, 1)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
        }
    }

    private Map<String, Integer> insertarVistas(Connection conn) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO vistas (nombre_vista) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            for (String v : TODAS_LAS_VISTAS) {
                ps.setString(1, v);
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                map.put(v, keys.getInt(1));
            }
        }
        return map;
    }

    private void insertarPermisosVista(Connection conn, int idRol, String[] vistas, Map<String, Integer> vistaIds)
            throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO permisos_vista (id_foranea_rol, id_foranea_vista, estado) VALUES (?, ?, 1)")) {
            for (String v : vistas) {
                ps.setInt(1, idRol);
                ps.setInt(2, vistaIds.get(v));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertarPermisoAccion(Connection conn, int idRol, String tabla, String accion) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO permisos_accion (id_foranea_rol, tabla, accion, estado) VALUES (?, ?, ?, 1)")) {
            ps.setInt(1, idRol);
            ps.setString(2, tabla);
            ps.setString(3, accion);
            ps.executeUpdate();
        }
    }

    private void insertarPermisosAccionAdmin(Connection conn, int idRol) throws Exception {
        String[] tablas = {"productos", "clientes", "ventas", "compras", "inventario", "user", "roles"};
        String[] acciones = {"select", "insert", "update", "delete"};
        for (String tabla : tablas) {
            for (String accion : acciones) {
                insertarPermisoAccion(conn, idRol, tabla, accion);
            }
        }
    }

    private void insertarPermisosAccionVendedor(Connection conn, int idRol) throws Exception {
        insertarPermisoAccion(conn, idRol, "ventas", "select");
        insertarPermisoAccion(conn, idRol, "ventas", "insert");
        insertarPermisoAccion(conn, idRol, "ventas", "update");
        insertarPermisoAccion(conn, idRol, "clientes", "select");
        insertarPermisoAccion(conn, idRol, "inventario", "select");
        insertarPermisoAccion(conn, idRol, "productos", "select");
    }

    private void insertarPermisosAccionCompras(Connection conn, int idRol) throws Exception {
        String[] acciones = {"select", "insert", "update", "delete"};
        for (String accion : acciones) {
            insertarPermisoAccion(conn, idRol, "compras", accion);
        }
        insertarPermisoAccion(conn, idRol, "inventario", "select");
        insertarPermisoAccion(conn, idRol, "inventario", "update");
        insertarPermisoAccion(conn, idRol, "productos", "select");
    }

    private void insertarUsuario(Connection conn, String user, String pass, int rol) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO [user] (nombre_user, password_user, id_foranea_rol, estado) VALUES (?, ?, ?, 1)")) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, rol);
            ps.executeUpdate();
        }
    }

    private int[] insertarProductos(Connection conn) throws Exception {
        Object[][] datos = {
            {"Semilla Maiz Hibrido", "Semillas", "Comayagua", new BigDecimal("450.00"), LocalDateTime.now().plusDays(120)},
            {"Fertilizante NPK 15-15-15", "Fertilizantes", "Francisco Morazan", new BigDecimal("890.00"), LocalDateTime.now().plusDays(25)},
            {"Herbicida Selectivo 1L", "Agroquimicos", "Yoro", new BigDecimal("320.00"), LocalDateTime.now().plusDays(60)},
            {"Azadon Agricola", "Herramientas", "Olancho", new BigDecimal("180.00"), LocalDateTime.now().plusDays(365)},
            {"Semilla Frijol Rojo", "Semillas", "La Paz", new BigDecimal("280.00"), LocalDateTime.now().plusDays(15)}
        };
        int[] ids = new int[datos.length];
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO productos (nombre_producto, categoria_producto, departamento_origen, precio_venta, fecha_vencimiento) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < datos.length; i++) {
                ps.setString(1, (String) datos[i][0]);
                ps.setString(2, (String) datos[i][1]);
                ps.setString(3, (String) datos[i][2]);
                ps.setBigDecimal(4, (BigDecimal) datos[i][3]);
                ps.setTimestamp(5, Timestamp.valueOf((LocalDateTime) datos[i][4]));
                ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                ids[i] = keys.getInt(1);
            }
        }
        return ids;
    }

    private void insertarClientes(Connection conn) throws Exception {
        Object[][] clientes = {
            {"Juan", "Perez", new BigDecimal("50000.00")},
            {"Maria", "Lopez", new BigDecimal("30000.00")},
            {"Carlos", "Martinez", new BigDecimal("15000.00")}
        };
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO clientes (nombre_cliente, apellido_cliente, estado, limite_credito) VALUES (?, ?, 1, ?)")) {
            for (Object[] c : clientes) {
                ps.setString(1, (String) c[0]);
                ps.setString(2, (String) c[1]);
                ps.setBigDecimal(3, (BigDecimal) c[2]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertarInventario(Connection conn, int[] productoIds) throws Exception {
        int[] minimos = {10, 15, 10, 5, 10};
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO inventario_config (id_producto, fecha_ultima_entrada, stock_minimo) VALUES (?, GETDATE(), ?)")) {
            for (int i = 0; i < productoIds.length; i++) {
                ps.setInt(1, productoIds[i]);
                ps.setInt(2, minimos[i]);
                ps.addBatch();
            }
            ps.executeBatch();
        }

        insertarLote(conn, productoIds[0], 20, LocalDateTime.now().plusDays(10));
        insertarLote(conn, productoIds[0], 80, LocalDateTime.now().plusDays(365));
        insertarLote(conn, productoIds[1], 20, LocalDateTime.now().minusDays(5));
        insertarLote(conn, productoIds[1], 30, LocalDateTime.now().plusDays(25));
        insertarLote(conn, productoIds[2], 30, LocalDateTime.now().plusDays(60));
        insertarLote(conn, productoIds[3], 20, LocalDateTime.now().plusDays(200));
        insertarLote(conn, productoIds[4], 8, LocalDateTime.now().plusDays(12));
    }

    private void insertarLote(Connection conn, int idProducto, int cantidad,
            LocalDateTime fechaVencimiento) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO inventario_lote (id_producto, cantidad, fecha_vencimiento, fecha_entrada) "
                        + "VALUES (?, ?, ?, GETDATE())")) {
            ps.setInt(1, idProducto);
            ps.setInt(2, cantidad);
            ps.setTimestamp(3, Timestamp.valueOf(fechaVencimiento));
            ps.executeUpdate();
        }
    }
}
