# Distribuidora Agrícola

Aplicación de escritorio en **Java Swing** para gestionar una distribuidora de suministros agrícolas: ventas, compras, clientes, productos, inventario por lotes, créditos y alertas operativas.

Arquitectura **MVC + DAO** sobre **SQL Server**, con interfaz en tema oscuro **FlatDarcula** e iconos SVG.

---

## Características principales

### Ventas
- Facturación por carrito con **ISV 15%** calculado en Java.
- Una factura = **un solo cliente**; al agregar la primera línea el cliente y el método de pago quedan bloqueados.
- Listado por factura con diálogo **Ver** para el detalle de líneas.
- Venta en **contado** o **crédito**, con validación de límite de crédito.
- Solo se venden productos con **stock vendible** (excluye unidades vencidas).
- Descuento de inventario en lotes con criterio **FEFO**.

### Compras
- Registro por factura con carrito de líneas.
- Cada compra crea un **lote** en inventario con su `fecha_expiracion`.
- Listado por factura con diálogo **Ver** para el detalle.

### Inventario por lotes
- **`inventario_lote`**: cantidades y fecha de vencimiento por lote.
- **`inventario_config`**: stock mínimo y fechas de última entrada/salida por producto.
- **`vista_inventario`**: resumen consolidado (stock, por vencer, vencido, vendible, próximo vencimiento).
- **Admin**: edición de stock mínimo, columnas de vencimiento y botón **Retirar**.
- **Ventas / Compras**: consulta de inventario en solo lectura.

### Alertas
- **Por vencer**: productos con unidades que vencen en ≤ 30 días.
- **Vencidos**: productos con stock vencido en almacén.
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

## Requisitos y ejecución

- **JDK 25**
- **Maven** (o ejecutar desde el IDE)
- **SQL Server** en `localhost:1433`

Opción rápida con Docker:

```powershell
docker compose up -d
```

Compilar y ejecutar:

```powershell
mvn compile exec:java
```

O ejecutar la clase principal `com.mycompany.agricola.Agricola` desde el IDE.

En el primer arranque, elegir **datos en blanco** o **datos de prueba** en la pantalla de inicialización.

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

Scripts manuales opcionales en `db/`:

```powershell
$jdbc = "$env:USERPROFILE\.m2\repository\com\microsoft\sqlserver\mssql-jdbc\13.2.1.jre11\mssql-jdbc-13.2.1.jre11.jar"
javac -cp $jdbc Migracion.java Seed.java
java -cp ".;$jdbc" Migracion
java -cp ".;$jdbc" Seed
```

También existen [`db/schema.sql`](db/schema.sql) y [`db/views.sql`](db/views.sql) como referencia del esquema.

---

## Usuarios de prueba (seed)

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| `admin` | `12345678` | Administrador |
| `vendedor1` | `venta123` | Vendedor |
| `compras1` | `compra123` | Compras |

---

## Navegación por rol

| Rol | Pantalla inicial | Accesos |
|-----|------------------|---------|
| Administrador | `HomeAdminVista` | Ventas, Compras, Usuarios, Clientes, Productos, Inventario, Alertar |
| Vendedor | `HomeVentasVista` | Ventas, Clientes, Inventario (lectura), Alertar |
| Compras | `HomeComprasVista` | Compras, Inventario (lectura), Alertar |

```
Agricola.main()
  └─► InicializacionController  (¿BD lista?)
        ├─ NO  → InicializacionVista
        └─ SÍ  → LoginController → Home según rol
```

`NavegacionService` centraliza la apertura de pantallas y la verificación de permisos por vista.

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

`productos.fecha_vencimiento` es dato de catálogo; la lógica operativa usa los **lotes**.

### Ventas y crédito
- Cliente es el **primer campo** del formulario de nueva venta.
- Tras agregar al carrito, no se puede cambiar cliente ni método de pago hasta vaciar el carrito.
- Subtotal e ISV se calculan en el controller antes de persistir.
- Si el total supera el crédito disponible del cliente, la venta se rechaza.

---

## Modelo de datos (resumen)

**Tablas principales:** `productos`, `clientes`, `compras`, `ventas`, `pago_credito_cliente`, `roles`, `vistas`, `[user]`, `permisos_vista`, `permisos_accion`, `inventario_config`, `inventario_lote`, `retiro_inventario`.

**Vistas SQL:** `vista_inventario`, `vista_facturas_venta`, `vista_ventas_detalle`, `vista_facturas_compra`, `vista_compras_detalle`, `vista_creditos_clientes`, `vista_advertencia_vencimiento`, `vista_advertencia_vencidos`, `vista_advertencia_stock_bajo`.

---

## Arquitectura MVC

```
┌─────────────┐     manipula      ┌──────────────────┐
│   Vista     │ ◄──────────────── │   Controlador    │
│  (solo UI)  │   botones, tablas │  (toda la lógica)│
└─────────────┘   inputs, labels  └────────┬─────────┘
                                           │
                              ┌────────────┼────────────┐
                              ▼            ▼            ▼
                         Services        DAO        Entity
                      (reglas extra)  (persistencia) (modelo)
```

| Capa | Paquete | Responsabilidad |
|------|---------|-----------------|
| **Vista** | `views.*` | Solo interfaz gráfica |
| **Controlador** | `controllers.*` | Listeners, tablas, validación, navegación |
| **DAO** | `dao.interfaces` / `dao.implement` | Acceso a base de datos |
| **Entity** | `model.entity` | Objetos de dominio |
| **Services** | `services` | Reglas de negocio compartidas |
| **Util** | `util`, `views.util`, `controllers.util` | Helpers reutilizables |

---

## Estructura del proyecto

```
agricola/
├── db/                              # Scripts SQL y utilidades de BD
├── docker-compose.yml               # SQL Server local
├── pom.xml
└── src/main/java/com/mycompany/agricola/
    ├── Agricola.java                # Punto de entrada
    ├── config/                      # AppConfig
    ├── controllers/                 # Lógica de pantallas
    │   ├── util/                    # TablaCrudHelper, ContratoMvc
    │   ├── admin/, ventas/, compras/, inventario/
    │   ├── LoginController.java
    │   ├── AlertasController.java
    │   └── InicializacionController.java
    ├── views/                       # Solo UI + util (UiTheme, UiStyle, UiIcons)
    ├── dao/                         # Capa de acceso a datos (activa)
    │   ├── interfaces/
    │   └── implement/
    ├── model/
    │   ├── entity/
    │   └── conexion/                # ConexionDB
    ├── services/                    # AuthService, NavegacionService, Alerta*Service, etc.
    │   └── db/                      # MigracionService, SeedService
    └── util/                        # ResultadoPersistencia
```

> Existe una copia legada en `model/dao/` que ya no se usa en controladores nuevos.  
> Siempre importar desde `com.mycompany.agricola.dao.*`.

### Servicios destacados
- `AuthService` — sesión del usuario logueado
- `NavegacionService` — apertura de pantallas y permisos
- `InventarioLoteService` — lotes, retiros y descuento FEFO en ventas
- `ProductosAptosParaVenderService` — productos con `stock_vendible > 0`
- `AlertaProductoPorVencerService`, `AlertaProductoVencidoService`, `AlertaCantidadBajaService`
- `CreditosClientesService` — validación de límite de crédito
- `InicializacionService` — detección e instalación de la BD

---

## Convenciones de código

### Nombres por capa

| Capa | Sufijo | Ejemplo |
|------|--------|---------|
| Entidad | `Entity` | `ProductoEntity` |
| DAO interfaz | `Dao` | `ProductoDao` |
| DAO implementación | `DaoApl` | `ProductoDaoApl` |
| Controller | `Controller` | `VentasController` |
| Vista | `Vista` | `VentasVista` |
| Servicio | `Service` | `AuthService` |

### Nombres de componentes Swing

| Tipo | Prefijo | Ejemplos |
|------|---------|----------|
| Botón | `boton` | `botonGuardar`, `botonCancelar` |
| Campo de texto | `input` | `inputNombre`, `inputLimiteCredito` |
| ComboBox | `combobox` | `comboboxProducto` |
| CheckBox | `checkbox` | `checkboxActivo` |
| Etiqueta | `etiqueta` | `etiquetaTitulo` |
| Tabla | `tabla` | `tablaClientes` |

### Vista — solo UI

**Sí va:** `initComponents()`, `aplicarEstilos()`, componentes públicos en español.

**No va:** listeners, imports de `controllers`/`dao`/`entity`/`services`, validación, `JOptionPane`, SQL.

### Controlador — toda la lógica

Flujo estándar de apertura:

```
abrir(parent)
  ├─► new XxxVista()
  ├─► inicializarVista(vista)       ← tabla, combos, datos iniciales
  ├─► cargarFuncionalidades(vista)  ← SOLO listeners, una línea por componente
  └─► navegacion.abrirVistaSiPermitida(...) / abrirFrame(...)
```

`cargarFuncionalidades` es el **único lugar** con listeners. Sin lógica inline; solo delega a métodos privados (`guardar`, `cancelar`, `volver`, `eliminar`, etc.).

```java
private void cargarFuncionalidades(FormularioAgregarClienteVista vista) {
    vista.botonGuardar.addActionListener(e -> guardar(vista));
    vista.botonCancelar.addActionListener(e -> cancelar(vista));
}
```

### DAO

```java
private final ClienteDaoApl clienteDao = new ClienteDaoApl();

ResultadoPersistencia resultado = clienteDao.create(cliente);
if (resultado.isExito()) { ... }
```

---

## Checklist: agregar una pantalla nueva

1. **Vista** — `views/<modulo>/MiNuevaVista.java`: componentes públicos, solo `initComponents()` + `aplicarEstilos()`.
2. **Controlador** — `controllers/<modulo>/MiNuevaController.java`: flujo `abrir` → `inicializarVista` → `cargarFuncionalidades` → navegación.
3. **Permisos** — registrar la vista en BD si aplica; conectar desde el home o pantalla padre.
4. **Verificar** — la vista no importa lógica; listeners solo en `cargarFuncionalidades`; compila con `mvn compile`.

---

## Datos de prueba en inventario (seed)

- **Semilla Maíz Híbrido**: 2 lotes (20 u. vencen en 10 días + 80 u. en 1 año).
- **Fertilizante NPK**: 20 u. vencidas + 30 u. por vencer.
- **Semilla Frijol Rojo**: 8 u. por vencer y stock bajo (mínimo 10).

---

## Lo que no incluye este proyecto

- Generación de PDF / JasperReports.
- Retiro de inventario fuera del módulo Admin.
- Doble escritura de stock: solo se escribe en `inventario_lote`.

---

## Comandos útiles

```powershell
mvn compile          # Compilar
mvn exec:java        # Ejecutar
mvn package          # Empaquetar JAR
```

---

## Resumen rápido

| Pregunta | Respuesta |
|----------|-----------|
| ¿Dónde va el layout? | `views` → `initComponents()` + `aplicarEstilos()` |
| ¿Dónde van los listeners? | `controllers` → `cargarFuncionalidades()` |
| ¿Dónde va la lógica al pulsar un botón? | `controllers` → método privado (`guardar`, `eliminar`, etc.) |
| ¿Dónde va el SQL? | `dao/implement` |
| ¿Dónde van las entidades? | `model/entity` |
| ¿Dónde va lógica reutilizable? | `services` |

**Regla de oro:** si un botón hace algo, el `addActionListener` está en `cargarFuncionalidades` y el código real está en un método privado aparte.
