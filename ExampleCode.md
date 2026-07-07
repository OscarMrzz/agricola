# Guía de código — Proyecto Agrícola

Ejemplos con la convención de sufijos: `Entity`, `Dao`, `DaoApl`, `Controller`, `Vista`.

---

## ConexionDB

```java
package com.mycompany.agricola.model.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=db_agricola;"
            + "encrypt=true;"
            + "trustServerCertificate=true";

    private static final String USER = "oscar";
    private static final String PASSWORD = "password12345678";
    private static Connection conexion = null;

    private ConexionDB() {
    }

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
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
```

---

## ResultadoPersistencia

```java
package com.mycompany.agricola.model.dao.resultados;

public class ResultadoPersistencia {

    private final boolean exito;
    private final String mensaje;
    private final Exception error;

    private ResultadoPersistencia(boolean exito, String mensaje, Exception error) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.error = error;
    }

    public static ResultadoPersistencia exito() {
        return new ResultadoPersistencia(true, "Operacion exitosa", null);
    }

    public static ResultadoPersistencia error(Exception e, String accion) {
        return new ResultadoPersistencia(false, "Error al " + accion, e);
    }

    public boolean isExito() { return exito; }
    public String getMensaje() { return mensaje; }
    public Exception getError() { return error; }
}
```

---

## ProductoEntity (con toString para JComboBox)

```java
package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoEntity {

    private int idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private String departamentoOrigen;
    private BigDecimal precioVenta;
    private LocalDateTime fechaVencimiento;

    // getters y setters ...

    @Override
    public String toString() {
        return nombreProducto;
    }
}
```

---

## ProductoDao (interfaz)

```java
package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;

public interface ProductoDao {

    List<ProductoEntity> getAll();

    ProductoEntity getById(int id);

    ResultadoPersistencia create(ProductoEntity producto);

    ResultadoPersistencia update(ProductoEntity producto);

    ResultadoPersistencia delete(int id);

    List<ProductoEntity> getAptosParaVenta();

    List<AdvertenciaVencimientoEntity> getProximosAVencer(int dias);
}
```

---

## ProductoDaoApl (implementación)

```java
package com.mycompany.agricola.model.dao.implement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.agricola.model.conexion.ConexionDB;
import com.mycompany.agricola.model.dao.interfaces.ProductoDao;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class ProductoDaoApl implements ProductoDao {

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
    public List<ProductoEntity> getAptosParaVenta() {
        List<ProductoEntity> productos = new ArrayList<>();
        try (Connection connection = ConexionDB.getConexion();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM productos WHERE fecha_vencimiento > GETDATE()")) {
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

    // getAll, update, delete, getProximosAVencer ...

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
}
```

---

## FormularioAgregarVentaController

```java
package com.mycompany.agricola.controllers.ventas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.agricola.model.entity.CarritoVentaEntity;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.services.CreditosClientesService;
import com.mycompany.agricola.model.dao.implement.VentaDaoApl;

public class FormularioAgregarVentaController {

    private static final BigDecimal ISV = new BigDecimal("0.15");
    private final VentaDaoApl ventaDao = new VentaDaoApl();
    private final CreditosClientesService creditosService = new CreditosClientesService();
    private final List<CarritoVentaEntity> carrito = new ArrayList<>();

    public void agregarLinea(ProductoEntity producto, int cantidad, ClienteEntity cliente, String metodoPago) {
        BigDecimal precioUnitario = producto.getPrecioVenta();
        BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal impuesto = subtotal.multiply(ISV).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(impuesto);

        CarritoVentaEntity linea = new CarritoVentaEntity();
        linea.setNombreProducto(producto.getNombreProducto());
        linea.setCantidadProducto(cantidad);
        linea.setPrecioUnitario(precioUnitario);
        linea.setImpuesto(impuesto);
        linea.setTotalAPagar(total);
        linea.setMetodoPago(metodoPago);
        carrito.add(linea);
    }

    public void guardarVenta(String noFactura, ClienteEntity cliente, int idVendedor) throws Exception {
        BigDecimal totalFactura = carrito.stream()
                .map(CarritoVentaEntity::getTotalAPagar)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if ("credito".equalsIgnoreCase(carrito.get(0).getMetodoPago())) {
            creditosService.validarLimiteCredito(cliente.getIdCliente(), totalFactura);
        }

        for (CarritoVentaEntity linea : carrito) {
            VentaEntity venta = new VentaEntity();
            venta.setNoFactura(noFactura);
            venta.setIdCliente(cliente.getIdCliente());
            venta.setIdForaneaVendedor(idVendedor);
            venta.setCantidadProducto(linea.getCantidadProducto());
            venta.setTipo(linea.getMetodoPago());
            venta.setPrecioAntesImpuesto(linea.getPrecioUnitario());
            venta.setImpuesto(linea.getImpuesto());
            venta.setTotalPagar(linea.getTotalAPagar());

            try (var conn = com.mycompany.agricola.model.conexion.ConexionDB.getConexion()) {
                ventaDao.create(venta);
            }
        }
        carrito.clear();
    }
}
```

---

## FormularioAgregarVentaVista (sin SQL)

```java
package com.mycompany.agricola.views.ventas;

import com.mycompany.agricola.controllers.ventas.FormularioAgregarVentaController;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class FormularioAgregarVentaVista extends javax.swing.JPanel {

    private final FormularioAgregarVentaController controller = new FormularioAgregarVentaController();

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ProductoEntity producto = (ProductoEntity) cmbProducto.getSelectedItem();
            ClienteEntity cliente = (ClienteEntity) cmbCliente.getSelectedItem();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            controller.agregarLinea(producto, cantidad, cliente, cmbMetodoPago.getSelectedItem().toString());
            actualizarTablaCarrito();
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Cantidad invalida");
        }
    }
}
```

---

## db/Seed.java (esqueleto)

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Seed {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=db_agricola;encrypt=true;trustServerCertificate=true";
    private static final String USER = "oscar";
    private static final String PASSWORD = "password12345678";

    public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            insertarRoles(conn);
            insertarUsuarios(conn);
            insertarProductos(conn);
            insertarClientes(conn);
            insertarInventario(conn);
            System.out.println("Seed completado");
        }
    }

    private static void insertarRoles(Connection conn) throws Exception {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO roles (nombre_rol, estado_rol) VALUES (?, ?)")) {
            ps.setString(1, "Administrador");
            ps.setBoolean(2, true);
            ps.executeUpdate();
        }
    }

    // insertarUsuarios, insertarProductos, insertarClientes, insertarInventario ...
}
```
