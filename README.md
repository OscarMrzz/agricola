# Agricola — Plan de construcción (MVC + DAO)

Aplicación de escritorio Java Swing para la gestión de una distribuidora agrícola.  
Este README es la guía con la que se construye la app: qué va en cada capa, cómo se organizan las carpetas y el patrón que siguen todos los controladores.

---

## Arquitectura general

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
| **Controlador** | `controllers.*` | Lógica de pantalla: listeners, tablas, validación, navegación |
| **DAO** | `dao.interfaces` / `dao.implement` | Acceso a base de datos |
| **Entity** | `model.entity` | Objetos de dominio |
| **Services** | `services` | Reglas de negocio compartidas entre controladores |
| **Util** | `util`, `views.util`, `controllers.util` | Helpers reutilizables |

---

## Estructura de carpetas (real)

```
agricola/
├── pom.xml
├── db/                              # Scripts SQL y utilidades de BD
│   ├── schema.sql
│   ├── views.sql
│   ├── Migracion.java
│   └── Seed.java
│
└── src/main/
    ├── java/com/mycompany/agricola/
    │   ├── Agricola.java            # Punto de entrada (main)
    │   │
    │   ├── config/
    │   │   └── AppConfig.java
    │   │
    │   ├── controllers/             # TODA la lógica de pantalla
    │   │   ├── util/
    │   │   │   ├── ContratoMvc.java       # Documentación del contrato MVC
    │   │   │   └── TablaCrudHelper.java   # Helper para tablas no editables
    │   │   ├── inventario/
    │   │   │   └── InventarioListadoController.java
    │   │   ├── admin/
    │   │   │   ├── HomeAdminController.java
    │   │   │   ├── clientes/
    │   │   │   │   ├── ClientesAdminController.java
    │   │   │   │   ├── FormularioAgregarClienteController.java
    │   │   │   │   └── FormularioEditarClienteController.java
    │   │   │   ├── productos/
    │   │   │   ├── usuarios/
    │   │   │   └── inventario/
    │   │   ├── ventas/
    │   │   ├── compras/
    │   │   ├── LoginController.java
    │   │   ├── AlertasController.java
    │   │   └── InicializacionController.java
    │   │
    │   ├── views/                   # SOLO UI
    │   │   ├── util/
    │   │   │   ├── UiStyle.java
    │   │   │   ├── UiTheme.java
    │   │   │   ├── UiIcons.java
    │   │   │   ├── UiUtil.java
    │   │   │   └── FormPanel.java
    │   │   ├── admin/
    │   │   │   ├── HomeAdminVista.java
    │   │   │   ├── clientes/
    │   │   │   ├── productos/
    │   │   │   ├── usuarios/
    │   │   │   └── inventario/
    │   │   ├── ventas/
    │   │   ├── compras/
    │   │   ├── LoginVista.java
    │   │   ├── AlertasVista.java
    │   │   └── InicializacionVista.java
    │   │
    │   ├── dao/                     # Capa de acceso a datos (activa)
    │   │   ├── interfaces/          # Contratos: ClienteDao, ProductoDao, etc.
    │   │   └── implement/           # Implementaciones: ClienteDaoApl, etc.
    │   │
    │   ├── model/
    │   │   ├── entity/              # ClienteEntity, ProductoEntity, etc.
    │   │   └── conexion/
    │   │       └── ConexionDB.java
    │   │
    │   ├── services/
    │   │   ├── AuthService.java
    │   │   ├── NavegacionService.java
    │   │   ├── CreditosClientesService.java
    │   │   ├── InicializacionService.java
    │   │   ├── Alerta*Service.java
    │   │   └── db/
    │   │       ├── MigracionService.java
    │   │       └── SeedService.java
    │   │
    │   ├── util/
    │   │   └── ResultadoPersistencia.java
    │   │
    │   └── reportes/                # (reservado para reportes futuros)
    │
    └── resources/
        ├── com/mycompany/agricola/icons/
        └── reportes/
```

> **Nota:** existe una copia legada en `model/dao/` que ya no se usa en controladores nuevos.  
> Siempre importar desde `com.mycompany.agricola.dao.*` y `com.mycompany.agricola.util.ResultadoPersistencia`.

---

## Vista (`*Vista.java`) — solo UI

La vista **no piensa**. Solo dibuja la pantalla.

### Qué SÍ va en la vista

- `initComponents()` — creación de componentes Swing (generado por NetBeans o a mano).
- `aplicarEstilos()` — colores, fuentes, textos por defecto, llamadas a `UiStyle`.
- Componentes **públicos** con nombres descriptivos en español.
- Archivo `.form` de NetBeans (si aplica).

### Qué NO va en la vista

- `addActionListener`, `DocumentListener` ni ningún listener con lógica.
- Imports de `controllers`, `dao`, `model.entity`, `services`.
- Validación, `JOptionPane`, acceso a base de datos.
- Lógica de negocio de cualquier tipo.

### Convención de nombres de componentes

| Tipo | Prefijo | Ejemplos |
|------|---------|----------|
| Botón | `boton` | `botonGuardar`, `botonCancelar`, `botonAgregar`, `botonVolver`, `botonRefrescar` |
| Campo de texto | `input` | `inputNombre`, `inputApellido`, `inputLimiteCredito` |
| ComboBox | `combobox` | `comboboxProducto`, `comboboxCliente` |
| CheckBox | `checkbox` | `checkboxActivo` |
| Etiqueta | `etiqueta` | `etiquetaTitulo`, `etiquetaNombre` |
| Tabla | `tabla` | `tablaClientes`, `tablaProductos` |
| Scroll | `scroll` | `scrollTabla` |

### Ejemplo de vista correcta

```java
public class FormularioAgregarClienteVista extends javax.swing.JPanel {

    public javax.swing.JButton botonCancelar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JCheckBox checkboxActivo;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JTextField inputNombre;
    public javax.swing.JTextField inputApellido;
    public javax.swing.JTextField inputLimiteCredito;

    public FormularioAgregarClienteVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        inputLimiteCredito.setText("0");
        UiStyle.aplicarVistaFormulario(this, etiquetaTitulo, botonGuardar, botonCancelar, ...);
    }

    private void initComponents() {
        // solo layout y creación de componentes
    }
}
```

---

## Controlador (`*Controller.java`) — toda la lógica

El controlador es quien **manipula** los botones, tablas, inputs y labels de la vista.

### Responsabilidades del controlador

- Instanciar la vista.
- Configurar datos iniciales (modelo de tabla, combos, etc.).
- Conectar listeners en `cargarFuncionalidades`.
- Leer y escribir valores de los componentes de la vista.
- Validar, mostrar `JOptionPane`, llamar DAO/Services.
- Navegar entre pantallas (`NavegacionService`).

### Flujo estándar de apertura

Todo controlador con pantalla sigue este orden:

```
abrir(parent)
  │
  ├─► new XxxVista()
  │
  ├─► inicializarVista(vista)       ← opcional: tabla, combos, datos iniciales
  │
  ├─► cargarFuncionalidades(vista)    ← SOLO listeners, una línea por componente
  │
  └─► navegacion.abrirVistaSiPermitida(...) / abrirFrame(...)
```

### `cargarFuncionalidades` — regla central

Es el **único lugar** donde se registran listeners (`addActionListener`, `DocumentListener`, etc.).

**Reglas:**

1. Una línea por componente.
2. **Sin lógica inline** dentro del listener.
3. Solo llamadas a métodos privados que contienen la lógica real.

```java
private void cargarFuncionalidades(FormularioAgregarClienteVista vista) {
    vista.botonGuardar.addActionListener(e -> guardar(vista));
    vista.botonCancelar.addActionListener(e -> cancelar(vista));
}
```

### Métodos privados de acción — aquí va la lógica

Los listeners **no** tienen código. Solo delegan:

| Botón / evento | Método privado típico |
|----------------|----------------------|
| Guardar | `guardar(vista)` |
| Cancelar / Cerrar | `cancelar(vista)` |
| Volver | `volver(vista)` |
| Agregar | `agregar(parent)` |
| Editar | `editar(vista, parent)` |
| Eliminar | `eliminar(vista)` |
| Refrescar | `refrescarTabla(vista)` |
| Login | `iniciarSesion(vista)` |
| Búsqueda en tiempo real | `filtrar(vista)` |

```java
private void guardar(FormularioAgregarClienteVista vista) {
    // 1. Leer inputs de la vista
    // 2. Validar
    // 3. Llamar DAO o Service
    // 4. Mostrar JOptionPane
    // 5. Cerrar o refrescar
}

private void cancelar(FormularioAgregarClienteVista vista) {
    SwingUtilities.getWindowAncestor(vista).dispose();
}
```

### Ejemplo completo — listado CRUD

```java
public void abrir(Component parent) {
    ClientesAdminVista vista = new ClientesAdminVista();
    inicializarVista(vista);
    cargarFuncionalidades(vista, parent);
    navegacion.abrirVistaSiPermitida("ClientesAdminVista", vista, parent);
}

private void inicializarVista(ClientesAdminVista vista) {
    vista.tablaClientes.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
    refrescarTabla(vista);
}

private void cargarFuncionalidades(ClientesAdminVista vista, Component parent) {
    vista.botonAgregar.addActionListener(e -> agregar(parent));
    vista.botonEditar.addActionListener(e -> editar(vista, parent));
    vista.botonEliminar.addActionListener(e -> eliminar(vista));
    vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
    vista.botonVolver.addActionListener(e -> volver(vista));
}
```

### Ejemplo completo — formulario simple

```java
public void abrir(Component parent) {
    FormularioAgregarClienteVista vista = new FormularioAgregarClienteVista();
    cargarFuncionalidades(vista);
    navegacion.abrirFrame(vista, "Agregar Cliente");
}

private void cargarFuncionalidades(FormularioAgregarClienteVista vista) {
    vista.botonGuardar.addActionListener(e -> guardar(vista));
    vista.botonCancelar.addActionListener(e -> cancelar(vista));
}
```

---

## DAO — persistencia

```
dao/interfaces/ClienteDao.java      → contrato (create, update, delete, findAll...)
dao/implement/ClienteDaoApl.java    → implementación con SQL via ConexionDB
```

Los controladores instancian la implementación directamente:

```java
private final ClienteDaoApl clienteDao = new ClienteDaoApl();
```

Los métodos del DAO devuelven `ResultadoPersistencia` (`util/ResultadoPersistencia.java`):

```java
ResultadoPersistencia resultado = clienteDao.create(cliente);
if (resultado.isExito()) { ... }
else { JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", ...); }
```

---

## Services — lógica compartida

Usar cuando la misma regla de negocio la necesitan varios controladores:

- `NavegacionService` — abrir pantallas, verificar permisos.
- `AuthService` — autenticación.
- `CreditosClientesService` — créditos de clientes.
- `Alerta*Service` — alertas de inventario.
- `InventarioLoteService` — lotes de inventario.

El controlador llama al service; el service puede llamar DAO internamente.

---

## Navegación y arranque

```
Agricola.main()
  └─► InicializacionController  (¿BD lista?)
        ├─ NO  → InicializacionVista
        └─ SÍ  → LoginController → Home según rol
                      ├─ Admin  → HomeAdminController
                      ├─ Ventas → HomeVentasController
                      └─ Compras → HomeComprasController
```

`NavegacionService` centraliza la apertura de pantallas y la verificación de permisos por vista.

---

## Checklist: agregar una pantalla nueva

### 1. Vista

- [ ] Crear `views/<modulo>/MiNuevaVista.java` (+ `.form` si usas NetBeans).
- [ ] Componentes públicos con nombres en español (`botonGuardar`, `inputNombre`, etc.).
- [ ] Solo `initComponents()` + `aplicarEstilos()`.
- [ ] Sin listeners ni imports de lógica.

### 2. Controlador

- [ ] Crear `controllers/<modulo>/MiNuevaController.java`.
- [ ] Método `abrir(Component parent)` con el flujo estándar.
- [ ] `inicializarVista(vista)` si necesita datos iniciales.
- [ ] `cargarFuncionalidades(vista)` con una línea por botón/evento.
- [ ] Métodos privados: `guardar`, `cancelar`, `volver`, etc. con toda la lógica.
- [ ] DAO y Services como campos privados del controlador.

### 3. Conectar navegación

- [ ] Registrar la vista en permisos/BD si aplica.
- [ ] Llamar al controlador desde el home o pantalla padre.

### 4. Verificar

- [ ] La vista no importa `controllers`, `dao`, `entity` ni `services`.
- [ ] No hay `addActionListener` fuera de `cargarFuncionalidades`.
- [ ] Los listeners solo delegan a métodos privados (sin lógica inline).
- [ ] Compila: `mvn compile`

---

## Comandos útiles

```bash
# Compilar
mvn compile

# Ejecutar
mvn exec:java

# Empaquetar JAR
mvn package
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
