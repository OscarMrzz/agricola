# Proyecto Agrícola — Distribuidora de Suministros

## Requisitos

### Ventas

- [ ] Al ingresar un producto se debe calcular el 15% (ISV)
- [ ] Al ingresar un producto se debe calcular el subTotal de manera local (no en la DB)

### Tecnología

- Java 2025
- Docker Compose
- SQL Server
- Git

### Base de datos (Docker)

```
usuario:   oscar
password:  password12345678
database:  db_agricola
puerto:    1433
```

### Patrones

- MVC con DAO

### Dependencias

```xml
<dependencies>
    <dependency>
        <groupId>com.microsoft.sqlserver</groupId>
        <artifactId>mssql-jdbc</artifactId>
        <version>13.2.1.jre11</version>
    </dependency>
    <dependency>
        <groupId>net.sf.jasperreports</groupId>
        <artifactId>jasperreports</artifactId>
        <version>7.0.1</version>
    </dependency>
</dependencies>
```

---

## Convención de nombres (sufijos obligatorios)

| Capa | Carpeta | Sufijo | Ejemplo |
|------|---------|--------|---------|
| Entidad (tabla) | `model/entity/` | `Entity` | `ProductoEntity`, `ClienteEntity` |
| Entidad (vista DB) | `model/entity/` | `Entity` | `VentasDetalleEntity` |
| DAO interfaz | `model/dao/interfaces/` | `Dao` | `ProductoDao`, `VentaDao` |
| DAO implementación | `model/dao/implement/` | `DaoApl` | `ProductoDaoApl`, `VentaDaoApl` |
| Controller | `controllers/` | `Controller` | `LoginController`, `VentaController` |
| Vista Swing | `views/` | `Vista` | `LoginVista`, `VentasVista` |
| Servicio | `services/` | `Service` | `AuthService` |

**Reglas:**

- Nombre base en singular PascalCase: `Producto`, `Compra`, `Venta`, `Usuario`, `Cliente`, `Rol`.
- Las interfaces DAO solo llevan sufijo `Dao` (sin `Interface`).
- Las implementaciones DAO solo llevan sufijo `DaoApl`.
- Controller y Vista comparten nombre base: `FormularioAgregarVentaController` ↔ `FormularioAgregarVentaVista`.
- Tablas SQL en `snake_case`; el sufijo `Entity` es solo para clases Java.

### Reglas de arquitectura (Trabajo.md)

- Ningún archivo en `views` instancia `Connection` ni contiene SQL.
- Cálculo ISV 15% y subtotales **en Java local** antes del `try-with-resources` de persistencia.
- `JComboBox` recibe objetos `ProductoEntity`, `ClienteEntity`, `UsuarioEntity` con `@Override toString()`.
- Venta al crédito valida `limite_credito` vs saldo acumulado; si excede, lanzar excepción controlada.
- Entradas numéricas inválidas deben manejarse sin crashear (validación en controller).
- Cada producto tiene `fecha_vencimiento`. ProductoDao provee método para listar insumos aptos para venta y alertar los que vencen en menos de 30 días.
- Reporte Jasper de insumos críticos ordenado por `departamento_origen`.

---

## Estructura

### Model

1. **Entity**
   1. `ProductoEntity`
   2. `CompraEntity`
   3. `InventarioEntity`
   4. `VentaEntity`
   5. `RolEntity`
   6. `PermisoAccionEntity`
   7. `PermisoVistaEntity`
   8. `VistaEntity`
   9. `UsuarioEntity`
   10. `ClienteEntity`
   11. `PagoCreditoClienteEntity`
   12. `VentasDetalleEntity` (vista DB)
   13. `CarritoVentaEntity` (en memoria)
   14. `ComprasDetalleEntity` (vista DB)
   15. `CarritoCompraEntity` (en memoria)
   16. `CreditosClientesDetalleEntity` (vista DB)
   17. `AdvertenciaVencimientoEntity` (vista DB)
   18. `AdvertenciaStockBajoEntity` (vista DB)

2. **Dao**
   1. `ProductoDao`
   2. `CompraDao`
   3. `InventarioDao`
   4. `VentaDao`
   5. `RolDao`
   6. `PermisoAccionDao`
   7. `PermisoVistaDao`
   8. `VistaDao`
   9. `UsuarioDao`
   10. `ClienteDao`
   11. `PagoCreditoClienteDao`
   12. **Implement** (folder)
      1. `ProductoDaoApl`
      2. `CompraDaoApl`
      3. `InventarioDaoApl`
      4. `VentaDaoApl`
      5. `RolDaoApl`
      6. `PermisoAccionDaoApl`
      7. `PermisoVistaDaoApl`
      8. `VistaDaoApl`
      9. `UsuarioDaoApl`
      10. `ClienteDaoApl`
      11. `PagoCreditoClienteDaoApl`

3. **conexion** — `ConexionDB`
4. **dao/resultados** — `ResultadoPersistencia`

### Controllers

1. `LoginController`
2. Compras (folder)
   1. `HomeComprasController`
   2. `ComprasController`
   3. `FormularioAgregarCompraController`
   4. `FormularioEditarCompraController`
   5. `InventarioComprasController`
3. Ventas (folder)
   1. `HomeVentasController`
   2. `VentasController`
   3. `FormularioAgregarVentaController`
   4. `FormularioEditarVentaController`
   5. `InventarioVentasController`
   6. `ClientesVentasController`
   7. `FacturaController`
4. HomeAdmin (folder)
   1. usuarios (folder)
      1. `UsuariosController`
      2. `FormularioAgregarUsuarioController`
      3. `FormularioEditarUsuarioController`
   2. Clientes (folder)
      1. `ClientesAdminController`
      2. `FormularioAgregarClienteController`
      3. `FormularioEditarClienteController`
   3. Inventario (folder)
      1. `InventarioAdminController`
      2. `FormularioEditarInventarioController`

### Vista

1. `LoginVista`
2. Compras (folder)
   1. `HomeComprasVista`
   2. `ComprasVista`
   3. `FormularioAgregarCompraVista`
   4. `FormularioEditarCompraVista`
   5. `InventarioComprasVista`
3. Ventas (folder)
   1. `HomeVentasVista`
   2. `VentasVista`
   3. `FormularioAgregarVentaVista`
   4. `FormularioEditarVentaVista`
   5. `InventarioVentasVista`
   6. `ClientesVentasVista`
   7. `FacturaVista` (JasperReports)
4. HomeAdmin (folder)
   1. usuarios (folder)
      1. `UsuariosVista`
      2. `FormularioAgregarUsuarioVista`
      3. `FormularioEditarUsuarioVista`
   2. Clientes (folder)
      1. `ClientesAdminVista`
      2. `FormularioAgregarClienteVista`
      3. `FormularioEditarClienteVista`
   3. Inventario (folder)
      1. `InventarioAdminVista`
      2. `FormularioEditarInventarioVista` (cambiar stock mínimo para alertas)

### Reportes

1. `FacturaVenta.jrxml`
2. `ReporteInsumosCriticos.jrxml`

### Config

1. `AppConfig` — variables de conexión

### Base de datos (scripts Java)

1. [`db/Migracion.java`](db/Migracion.java) — crea tablas y vistas
2. [`db/Seed.java`](db/Seed.java) — datos de ejemplo, permisos_vista, permisos_accion, usuarios

**Usuario admin:** `admin` / `12345678`

### Navegación post-login

- `Administrador` → `HomeAdminVista` (hub)
- `Vendedor` → `HomeVentasVista`
- `Compras` → `HomeComprasVista`
- Cada Home tiene botón **Alertar** → `AlertasVista` (vencimiento + stock bajo)

### Servicios

1. `AlertaCantidadBajaService`
2. `AlertaProductoPorVencerService`
3. `ProductosAptosParaVenderService`
4. `CreditosClientesService`
5. `AuthService`

---

## Entity (tablas)

### ProductoEntity → tabla `productos`

| Campo | Tipo |
|-------|------|
| id_producto | int |
| nombre_producto | varchar |
| categoria_producto | varchar |
| departamento_origen | varchar |
| precio_venta | decimal |
| fecha_vencimiento | datetime |

### CompraEntity → tabla `compras`

| Campo | Tipo |
|-------|------|
| id_compra | int |
| id_foranea_producto | int |
| id_foranea_usuario | int |
| cantidad_compra | int |
| precio_compra | decimal |
| fecha_expiracion | datetime |
| fecha_compra | datetime |
| no_factura | varchar |
| metodo_pago | varchar |

### InventarioEntity → tabla `inventario`

| Campo | Tipo |
|-------|------|
| id_inventario | int |
| id_producto | int |
| fecha_ultima_entrada | datetime |
| fecha_ultima_salida | datetime |
| stock | int |
| stock_minimo | int |

### VentaEntity → tabla `ventas`

| Campo | Tipo |
|-------|------|
| id_venta | int |
| id_foranea_vendedor | int |
| id_foranea_producto | int |
| no_factura | varchar (puede repetirse, agrupa la venta) |
| fecha_venta | datetime |
| id_cliente | int |
| cantidad_producto | int |
| tipo | varchar (credito, contado) |
| metodo_pago | varchar (alias de tipo) |
| precio_antes_impuesto | decimal |
| impuesto | decimal |
| total_pagar | decimal |

### RolEntity → tabla `roles`

| Campo | Tipo |
|-------|------|
| id_rol | int |
| nombre_rol | varchar |
| estado_rol | bit |

### PermisoAccionEntity → tabla `permisos_accion`

| Campo | Tipo |
|-------|------|
| id_permiso_accion | int |
| id_foranea_rol | int |
| tabla | varchar |
| accion | varchar (select, create, update, delete) |
| estado | bit |

### VistaEntity → tabla `vistas`

| Campo | Tipo |
|-------|------|
| id_vista | int |
| nombre_vista | varchar |

### PermisoVistaEntity → tabla `permisos_vista`

| Campo | Tipo |
|-------|------|
| id_permiso_vista | int |
| id_foranea_rol | int |
| id_foranea_vista | int |
| estado | bit |

### UsuarioEntity → tabla `user`

| Campo | Tipo |
|-------|------|
| id_user | int |
| nombre_user | varchar |
| password_user | varchar |
| id_foranea_rol | int |
| estado | bit |

### ClienteEntity → tabla `clientes`

| Campo | Tipo |
|-------|------|
| id_cliente | int |
| nombre_cliente | varchar |
| apellido_cliente | varchar |
| estado | bit |
| limite_credito | decimal |

### PagoCreditoClienteEntity → tabla `pago_credito_cliente`

| Campo | Tipo |
|-------|------|
| id_pago_credito | int |
| id_foranea_cliente | int |
| fecha_pago | datetime |
| cantidad | decimal |

---

## Entity (vistas DB / en memoria)

> [!NOTE]
> Las siguientes no tienen estructura de tabla propia; mapean vistas SQL o se usan en memoria para el carrito.

### VentasDetalleEntity

1. id_venta
2. no_factura
3. cliente (nombre y apellido)
4. fecha_venta
5. nombre_producto
6. cantidad_producto
7. metodo_pago
8. precio_unitario
9. subtotal
10. impuesto
11. total_a_pagar

### CarritoVentaEntity (en memoria)

1. no_factura
2. cliente (nombre y apellido)
3. fecha_venta
4. nombre_producto
5. cantidad_producto
6. metodo_pago
7. precio_unitario
8. subtotal
9. impuesto
10. total_a_pagar

### ComprasDetalleEntity

1. id_compra
2. no_factura
3. fecha_compra
4. nombre_producto
5. cantidad_producto
6. metodo_pago
7. precio_unitario
8. total_antes_impuesto
9. impuesto
10. total_a_pagar

### CarritoCompraEntity (en memoria)

1. nombre_producto
2. cantidad_producto
3. precio_unitario
4. total_antes_descuento

### CreditosClientesDetalleEntity

1. id_cliente
2. nombre_cliente
3. apellido_cliente
4. credito_maximo
5. credito_actual
6. diferencia

### AdvertenciaVencimientoEntity

1. id_producto
2. nombre_producto
3. fecha_vencimiento
4. dias_restantes

### AdvertenciaStockBajoEntity

1. id_producto
2. nombre_producto
3. stock_actual

---

## Tablas vistas (UI — columnas JTable)

### Ventas

1. no
2. no_factura
3. cliente (nombre y apellido)
4. fecha_venta
5. nombre_producto
6. cantidad_producto
7. metodo_pago
8. precio_unitario
9. total_despues_descuento
10. impuesto
11. total_a_pagar

### CarritoVenta

1. no
2. producto
3. cantidad
4. precio
5. total

> [!NOTE]
> Después del carrito va el subtotal, el impuesto (ISV 15%) y el total a pagar.

### Compras

1. no
2. no_factura
3. fecha_compra
4. nombre_producto
5. cantidad_producto
6. metodo_pago
7. precio_unitario
8. total_antes_impuesto
9. impuesto
10. total_a_pagar

### CarritoCompra

1. no_factura
2. nombre_producto
3. cantidad_producto
4. precio_unitario
5. total_antes_descuento

> [!NOTE]
> Después aparece el subtotal, impuesto y total a pagar.

### Admin — Usuarios

1. no
2. nombre_usuario
3. rol_usuario
4. estatus_usuario

### Admin — Clientes

1. no
2. nombre_cliente
3. apellido_cliente
4. estatus

### Admin — CreditosClientes

1. no
2. nombre_cliente
3. apellido_cliente
4. credito_maximo
5. credito_actual
6. diferencia

### tablaAdvertenciaProductosProntosAVencer

1. no
2. nombre_producto
3. fecha_vencimiento
4. dias_restantes

### tablaAdvertenciaStockBajo

1. no
2. nombre_producto
3. stock_actual
