# Distribuidora Agrícola

Aplicación de escritorio en **Java Swing** para gestionar una distribuidora de suministros agrícolas: ventas, compras, clientes, productos, inventario por lotes, créditos y alertas operativas.

Arquitectura **MVC + DAO** sobre **SQL Server**, con interfaz en tema oscuro **FlatDarcula** e iconos SVG.

---

## Características principales

### Ventas
- Facturación por carrito con **ISV 15%** calculado en Java (no en la base de datos).
- Una factura = **un solo cliente**; al agregar la primera línea al carrito el cliente y el método de pago quedan bloqueados.
- Listado por **factura** con diálogo **Ver** para el detalle de líneas.
- Venta en **contado** o **crédito**, con validación de límite de crédito del cliente.
- Solo se venden productos con **stock vendible** (excluye unidades vencidas en almacén).
- Descuento de inventario en lotes con criterio **FEFO** (primero los que vencen antes).

### Compras
- Registro por factura con carrito de líneas.
- Cada compra crea un **lote** en inventario con su `fecha_expiracion`.
- Listado por factura con diálogo **Ver** para el detalle.

### Inventario por lotes
- **`inventario_lote`**: única tabla física con cantidades y fecha de vencimiento por lote.
- **`inventario_config`**: stock mínimo y fechas de última entrada/salida por producto.
- **`vista_inventario`**: resumen consolidado (stock, por vencer, vencido, vendible, próximo vencimiento).
- **Admin**: edición de stock mínimo, columnas de vencimiento y botón **Retirar** (producto dañado, vencido o próximo a vencer).
- **Ventas / Compras**: consulta de inventario en solo lectura.

### Alertas
- **Por vencer**: productos con unidades que vencen en ≤ 30 días (con cantidad).
- **Vencidos**: productos con stock vencido en almacén (con cantidad).
- **Stock bajo**: stock total por debajo del mínimo configurado.

### Seguridad y roles
- Login con permisos por **rol** (`permisos_vista` y `permisos_accion`).
- Tres perfiles: **Administrador**, **Vendedor** y **Compras**.

### Inicialización
- Si la base no existe, la app muestra **InicializacionVista** para crear esquema vacío o con datos de prueba.

---

## Tecnologías

| Componente | Versión / detalle |
|------------|-------------------|
| Java | 25 |
| Build | Maven |
| UI | Swing + FlatLaf (FlatDarcula) + JSVG |
| Base de datos | SQL Server 2022 (Docker opcional) |
| JDBC | `mssql-jdbc` 13.2.1 |
| Patrón | MVC + DAO |

---

## Requisitos

- **JDK 25**
- **Maven** (o ejecutar desde el IDE)
- **SQL Server** en `localhost:1433`  
  Opción rápida con Docker:

```powershell
docker compose up -d
```

---

## Configuración de base de datos

Valores en [`AppConfig.java`](src/main/java/com/mycompany/agricola/config/AppConfig.java):

| Parámetro | Valor |
|-----------|-------|
| Host | `localhost` |
| Puerto | `1433` |
| Base de datos | `db_agricola` |
| Usuario app | `oscar` |
| Contraseña app | `password12345678` |
| Usuario SA (migración) | `sa` |
| Contraseña SA | `Password12345678!` |

La migración crea el login `oscar` y le asigna permisos sobre `db_agricola`.

### Scripts manuales (opcional)

Desde la carpeta `db/`:

```powershell
$jdbc = "$env:USERPROFILE\.m2\repository\com\microsoft\sqlserver\mssql-jdbc\13.2.1.jre11\mssql-jdbc-13.2.1.jre11.jar"
javac -cp $jdbc Migracion.java Seed.java
java -cp ".;$jdbc" Migracion
java -cp ".;$jdbc" Seed
```

También existen [`db/schema.sql`](db/schema.sql) y [`db/views.sql`](db/views.sql) como referencia del esquema.

---

## Cómo ejecutar

```powershell
mvn compile exec:java
```

O ejecutar la clase principal `com.mycompany.agricola.Agricola` desde el IDE.

En el primer arranque, elegir **datos en blanco** o **datos de prueba** en la pantalla de inicialización.

---

## Usuarios de prueba (seed)

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| `admin` | `12345678` | Administrador |
| `vendedor1` | `venta123` | Vendedor |
| `compras1` | `compra123` | Compras |

---

## Navegación por rol

### Administrador → `HomeAdminVista`
Cuadrícula con acceso a: Ventas, Compras, Usuarios, Clientes, Productos, Inventario y Alertar.

### Vendedor → `HomeVentasVista`
Ventas, Clientes, Inventario (lectura) y Alertar.

### Compras → `HomeComprasVista`
Compras, Inventario (lectura) y Alertar.

---

## Reglas de negocio relevantes

### Inventario
| Escenario | Comportamiento |
|-----------|----------------|
| Compra registrada | Crea lote con `fecha_expiracion` de la línea |
| 120 en stock, 20 por vencer en 10 días | Alerta: 20 por vencer |
| 120 en stock, 20 vencidos | Alerta vencidos: 20; vendible: 100 |
| Intentar vender 120 con 20 vencidos | Bloqueo: solo se pueden vender 100 |
| Retiro en Admin | Consume vencidos primero, luego los más próximos a vencer |

`productos.fecha_vencimiento` se conserva como dato de catálogo; la lógica operativa usa los **lotes**.

### Ventas
- Cliente es el **primer campo** del formulario de nueva venta.
- Tras agregar al carrito, no se puede cambiar cliente ni método de pago hasta vaciar el carrito.
- Subtotal e ISV se calculan en el controller antes de persistir.

### Crédito
- Si el total de la factura supera el crédito disponible del cliente, la venta se rechaza con mensaje controlado.

---

## Modelo de datos (resumen)

### Tablas principales
- `productos`, `clientes`, `compras`, `ventas`, `pago_credito_cliente`
- `roles`, `vistas`, [user], `permisos_vista`, `permisos_accion`
- `inventario_config`, `inventario_lote`, `retiro_inventario`

### Vistas SQL
- `vista_inventario` — resumen de stock por producto
- `vista_facturas_venta`, `vista_ventas_detalle`
- `vista_facturas_compra`, `vista_compras_detalle`
- `vista_creditos_clientes`
- `vista_advertencia_vencimiento`, `vista_advertencia_vencidos`, `vista_advertencia_stock_bajo`

---

## Estructura del proyecto

```
agricola/
├── db/                    # Migracion.java, Seed.java, schema.sql, views.sql
├── docker-compose.yml     # SQL Server local
├── pom.xml
└── src/main/java/com/mycompany/agricola/
    ├── Agricola.java      # Punto de entrada
    ├── config/            # AppConfig
    ├── controllers/       # Lógica de pantallas
    ├── model/
    │   ├── conexion/      # ConexionDB
    │   ├── dao/           # Interfaces + implementaciones (DaoApl)
    │   └── entity/        # Entidades de tablas y vistas
    ├── services/          # Reglas de negocio compartidas
    │   └── db/            # MigracionService, SeedService
    └── views/             # Pantallas Swing + util (UiTheme, UiStyle, UiIcons)
```

### Servicios destacados
- `AuthService` — sesión del usuario logueado
- `InventarioLoteService` — lotes, retiros y descuento FEFO en ventas
- `ProductosAptosParaVenderService` — productos con `stock_vendible > 0`
- `AlertaProductoPorVencerService`, `AlertaProductoVencidoService`, `AlertaCantidadBajaService`
- `CreditosClientesService` — validación de límite de crédito
- `InicializacionService` — detección e instalación de la BD

---

## Convención de nombres

| Capa | Carpeta | Sufijo | Ejemplo |
|------|---------|--------|---------|
| Entidad | `model/entity/` | `Entity` | `ProductoEntity` |
| DAO interfaz | `model/dao/interfaces/` | `Dao` | `ProductoDao` |
| DAO implementación | `model/dao/implement/` | `DaoApl` | `ProductoDaoApl` |
| Controller | `controllers/` | `Controller` | `VentasController` |
| Vista | `views/` | `Vista` | `VentasVista` |
| Servicio | `services/` | `Service` | `AuthService` |

**Reglas:**
- Las vistas no ejecutan SQL ni abren conexiones directamente.
- Los `JComboBox` usan entidades (`ProductoEntity`, `ClienteEntity`, etc.) con `toString()` definido.
- Cálculos de ISV y subtotales en Java, antes de la persistencia.
- Tablas SQL en `snake_case`; clases Java en PascalCase.

---

## Datos de prueba en inventario (seed)

El seed crea escenarios útiles para probar alertas y ventas:

- **Semilla Maíz Híbrido**: 2 lotes (20 u. vencen en 10 días + 80 u. en 1 año).
- **Fertilizante NPK**: 20 u. vencidas + 30 u. por vencer.
- **Semilla Frijol Rojo**: 8 u. por vencer y stock bajo (mínimo 10).

---

## Lo que no incluye este proyecto

- Generación de PDF / JasperReports (removido).
- Retiro de inventario fuera del módulo Admin.
- Doble escritura de stock: solo se escribe en `inventario_lote`.
