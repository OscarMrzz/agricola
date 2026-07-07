package com.mycompany.agricola.controllers.admin.productos;

import java.awt.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.productos.FormularioAgregarProductoVista;

public class FormularioAgregarProductoController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final ProductoDaoApl productoDao = new ProductoDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    public void abrir(Component parent) {
        FormularioAgregarProductoVista vista = new FormularioAgregarProductoVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista);
        navegacion.abrirFrame(vista, "Agregar Producto");
    }

    private void inicializarVista(FormularioAgregarProductoVista vista) {
        vista.inputVencimiento.setText(LocalDateTime.now().plusYears(1).format(FECHA_FORMATO));
    }

    private void cargarFuncionalidades(FormularioAgregarProductoVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    public ResultadoPersistencia crear(ProductoEntity producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre del producto es obligatorio"), "crear el producto");
        }
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta().compareTo(BigDecimal.ZERO) <= 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El precio de venta debe ser mayor a cero"), "crear el producto");
        }
        if (producto.getFechaVencimiento() == null) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La fecha de vencimiento es obligatoria"), "crear el producto");
        }
        return productoDao.create(producto);
    }

    private void cancelar(FormularioAgregarProductoVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioAgregarProductoVista vista) {
        try {
            ProductoEntity producto = new ProductoEntity();
            producto.setNombreProducto(vista.inputNombre.getText().trim());
            producto.setCategoriaProducto(vista.inputCategoria.getText().trim());
            producto.setDepartamentoOrigen(vista.inputDepartamento.getText().trim());
            producto.setPrecioVenta(new BigDecimal(vista.inputPrecio.getText().trim()));
            producto.setFechaVencimiento(LocalDateTime.parse(vista.inputVencimiento.getText().trim(), FECHA_FORMATO));

            ResultadoPersistencia resultado = crear(producto);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(vista, "Producto creado correctamente");
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
