# Sistema de diseño UI — Distribuidora Agrícola

Guía de referencia para mantener consistencia visual en la aplicación Swing.

## Look & Feel

- **Tema:** FlatDarculaLaf (estilo IntelliJ IDEA oscuro)
- **Librería:** [FlatLaf](https://github.com/JFormDesigner/FlatLaf) 3.7.1
- **Iconos SVG:** FlatLaf Extras + JSVG
- **Activación:** `FlatDarculaLaf.setup()` en `Agricola.main()` antes de crear componentes Swing

## Dependencias Maven (`pom.xml`)

```xml
<dependency>
    <groupId>com.formdev</groupId>
    <artifactId>flatlaf</artifactId>
    <version>3.7.1</version>
</dependency>
<dependency>
    <groupId>com.formdev</groupId>
    <artifactId>flatlaf-extras</artifactId>
    <version>3.7.1</version>
</dependency>
<dependency>
    <groupId>com.github.weisj</groupId>
    <artifactId>jsvg</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Escala de espaciado (px)

| Constante (`UiTheme`) | Valor | Uso |
|---------------------|-------|-----|
| `SPACE_XS` | 4 | Gap mínimo (icono ↔ texto) |
| `SPACE_SM` | 8 | Entre botones en fila |
| `SPACE_MD` | 12 | Entre filas de formulario |
| `SPACE_LG` | 16 | Entre bloques dentro de un panel |
| `SPACE_XL` | 20 | Padding de página |
| `SPACE_XXL` | 24 | Título → contenido |
| `SPACE_XXXL` | 32 | Entre secciones grandes (home) |

## Márgenes y padding

| Constante | Valor | Uso |
|-----------|-------|-----|
| `PAGE_INSET` | 20 | Margen exterior en vistas normales (`UiStyle.aplicarPagina`) |
| `FORM_INSET` | 16 | Padding interno de `FormPanel` |
| `DIALOG_INSET` | 24 | Login, inicialización (`UiStyle.aplicarDialogo`) |

## Tipografía

| Rol | Constante / método | Fuente | Tamaño | Peso |
|-----|-------------------|--------|--------|------|
| Título de pantalla | `UiTheme.fontTitle()` | Segoe UI | 18pt | Bold |
| Sección | `UiTheme.fontSection()` | Segoe UI | 13pt | Bold |
| Cuerpo | `UiTheme.fontBody()` | Segoe UI | 12pt | Plain |
| Pequeño | `UiTheme.fontSmall()` | Segoe UI | 11pt | Plain |
| Totales | `UiTheme.fontTotal()` | Segoe UI | 13pt | Bold |

## Tamaños de componentes

| Componente | Constante | Valor |
|------------|-----------|-------|
| Altura input/combo | `INPUT_HEIGHT` | 28px |
| Ancho input estándar | `INPUT_WIDTH` | 260px |
| Ancho columna label | `LABEL_WIDTH` | 120px |
| Botón pequeño | `BTN_SM` | 90 × 28 |
| Botón estándar | `BTN_MD` | 110 × 32 |
| Botón primario | `BTN_LG` | 130 × 36 |
| Icono en botón | `ICON_SIZE` | 16px |
| Icono grande | `ICON_SIZE_LG` | 20px |
| Fila de tabla | `TABLE_ROW_HEIGHT` | 28px |

## Colores semánticos

| Rol | Constante | Valor |
|-----|-----------|-------|
| Error | `COLOR_ERROR` | `#FF6B6B` |
| Éxito | `COLOR_SUCCESS` | `#6BCB77` |

## Tamaños de ventana

| Pantalla | Dimensiones |
|----------|-------------|
| Login | 480 × 300 |
| Inicialización | 520 × 280 |
| Home / listas | 960 × 640 |
| Formularios grandes (carrito) | 1120 × 680 |
| Formularios simples | 520 × 420 |

## Botones

- Tipo visual: `square` (FlatLaf client property `JButton.buttonType`)
- **Primario** (Guardar, Ingresar): `UiStyle.estilizarBoton(btn, TipoBoton.PRIMARIO)`
- **Secundario** (Cancelar, Volver): `UiStyle.estilizarBoton(btn, TipoBoton.SECUNDARIO)`
- **Navegación** (home, listas): `UiStyle.estilizarBotonNav(btn)`

## Iconos SVG

Ubicación: `src/main/resources/com/mycompany/agricola/icons/`

Rutas centralizadas en `UiIcons.java`. Uso:

```java
boton.setIcon(UiIcons.icono(UiIcons.SAVE));
// o
UiStyle.conIcono(boton, UiIcons.SAVE);
```

| Constante | Archivo | Uso |
|-----------|---------|-----|
| `LOGIN` | login.svg | Ingresar |
| `ADD` | add.svg | Agregar, Nueva |
| `EDIT` | edit.svg | Editar |
| `DELETE` | delete.svg | Eliminar |
| `SAVE` | save.svg | Guardar |
| `CANCEL` | cancel.svg | Cancelar, Volver |
| `USERS` | users.svg | Usuarios |
| `PRODUCTS` | box.svg | Productos |
| `INVENTORY` | inventory.svg | Inventario |
| `ALERT` | alert.svg | Alertar |
| `CART` | cart.svg | Carrito |
| `BACK` | back.svg | Volver |
| `REFRESH` | refresh.svg | Refrescar tablas |
| `PDF` | pdf.svg | Generar PDF |
| `SALE` | sale.svg | Ventas |
| `PURCHASE` | purchase.svg | Compras |
| `CLIENT` | client.svg | Clientes |

## Clases utilitarias

| Clase | Responsabilidad |
|-------|-----------------|
| `UiTheme` | Constantes numéricas, fuentes y colores |
| `UiStyle` | Aplicar estilos a componentes y vistas |
| `UiIcons` | Rutas y factory de `FlatSVGIcon` |
| `FormPanel` | Panel con borde titulado para agrupar inputs |
| `UiUtil` | Frames, diálogos, tablas. Usar `abrirFrameFormulario()` para formularios simples (520×420) |

## Patrón en vistas

No modificar bloques `//GEN-BEGIN:initComponents`. Aplicar estilos después de `initComponents()`:

```java
public MiVista() {
    initComponents();
    aplicarEstilos();
    inicializarLogica();
}

private void aplicarEstilos() {
    UiStyle.aplicarVistaLista(this, lblTitulo, tabla, scroll,
            btnAgregar, btnEditar, btnEliminar, btnVolver);
    UiStyle.conIcono(btnAgregar, UiIcons.ADD);
    // ...
}
```

## Formularios con panel

Para formularios de edición que ya tienen `panelFormulario`:

```java
UiStyle.estilizarFormPanel(panelFormulario, "Datos");
```
