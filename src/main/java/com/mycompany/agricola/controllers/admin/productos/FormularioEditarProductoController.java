package com.mycompany.agricola.controllers.admin.productos;

import java.awt.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.productos.FormularioEditarProductoVista;

public class FormularioEditarProductoController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final ProductoDaoApl productoDao = new ProductoDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    private ProductoEntity producto;

    public void abrir(int id, Component parent) {
        FormularioEditarProductoVista vista = new FormularioEditarProductoVista();
        producto = obtenerPorId(id);
        if (producto == null) {
            vista.panelFormulario.setVisible(false);
            vista.etiquetaNoEncontrado.setVisible(true);
        } else {
            inicializarVista(vista);
            cargarFuncionalidades(vista);
        }
        navegacion.abrirFrame(vista, "Editar Producto");
    }

    private void inicializarVista(FormularioEditarProductoVista vista) {
        vista.inputNombre.setText(producto.getNombreProducto());
        vista.inputCategoria.setText(producto.getCategoriaProducto());
        vista.inputDepartamento.setText(producto.getDepartamentoOrigen());
        vista.inputPrecio.setText(producto.getPrecioVenta().toPlainString());
        if (producto.getFechaVencimiento() != null) {
            vista.inputVencimiento.setText(producto.getFechaVencimiento().format(FECHA_FORMATO));
        }
    }

    private void cargarFuncionalidades(FormularioEditarProductoVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    public ProductoEntity obtenerPorId(int id) {
        return productoDao.getById(id);
    }

    public ResultadoPersistencia actualizar(ProductoEntity productoActualizado) {
        if (productoActualizado.getNombreProducto() == null || productoActualizado.getNombreProducto().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre del producto es obligatorio"), "actualizar el producto");
        }
        if (productoActualizado.getPrecioVenta() == null
                || productoActualizado.getPrecioVenta().compareTo(BigDecimal.ZERO) <= 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El precio de venta debe ser mayor a cero"), "actualizar el producto");
        }
        if (productoActualizado.getFechaVencimiento() == null) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La fecha de vencimiento es obligatoria"), "actualizar el producto");
        }
        return productoDao.update(productoActualizado);
    }

    private void cancelar(FormularioEditarProductoVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioEditarProductoVista vista) {
        try {
            producto.setNombreProducto(vista.inputNombre.getText().trim());
            producto.setCategoriaProducto(vista.inputCategoria.getText().trim());
            producto.setDepartamentoOrigen(vista.inputDepartamento.getText().trim());
            producto.setPrecioVenta(new BigDecimal(vista.inputPrecio.getText().trim()));
            producto.setFechaVencimiento(LocalDateTime.parse(vista.inputVencimiento.getText().trim(), FECHA_FORMATO));

            ResultadoPersistencia resultado = actualizar(producto);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(vista, "Producto actualizado correctamente");
                cancelar(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio invalido");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vista, "Fecha de vencimiento invalida");
        }
    }
}
