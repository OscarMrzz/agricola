package com.mycompany.agricola.controllers.util;

/**
 * Contrato MVC del proyecto Agricola.
 *
 * <p><b>Vista (*Vista.java):</b>
 * <ul>
 *   <li>Solo construye UI: initComponents(), aplicarEstilos(), layout.</li>
 *   <li>Componentes publicos con nombres descriptivos en espanol.</li>
 *   <li>Sin imports de controllers, model.entity ni services.</li>
 *   <li>Sin logica de negocio, listeners con logica, ni acceso a DAO.</li>
 * </ul>
 *
 * <p><b>Controlador (*Controller.java):</b>
 * <ul>
 *   <li>abrir(parent) o crearVista(): new Vista() → inicializarVista() (opcional) → cargarFuncionalidades().</li>
 *   <li>cargarFuncionalidades(vista): unico lugar con addActionListener; una linea por componente.</li>
 *   <li>Metodos privados de accion: guardar, cancelar, volver, editar, eliminar, refrescar, etc.</li>
 *   <li>Persistencia y validacion en esos metodos privados, no en los listeners.</li>
 * </ul>
 *
 * <p><b>Convencion de nombres de componentes:</b>
 * <ul>
 *   <li>botonAgregar, botonGuardar, botonVolver</li>
 *   <li>inputNombre, inputApellido</li>
 *   <li>comboboxProducto, comboboxCliente</li>
 *   <li>checkboxActivo</li>
 *   <li>etiquetaTitulo, etiquetaNombre</li>
 *   <li>tablaClientes, scrollTabla</li>
 * </ul>
 */
public final class ContratoMvc {

    private ContratoMvc() {
    }
}
