package com.mycompany.agricola.controllers.compras;

import java.awt.Component;
import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.dao.implement.CompraDaoApl;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ComprasDetalleEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.compras.FormularioEditarCompraVista;

public class FormularioEditarCompraController {

    private final CompraDaoApl compraDao = new CompraDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    private CompraEntity compra;

    public void abrir(int id, Component parent) {
        FormularioEditarCompraVista vista = new FormularioEditarCompraVista();
        compra = obtenerPorId(id);
        if (compra == null) {
            vista.panelFormulario.setVisible(false);
            vista.etiquetaError.setText("Compra no encontrada");
            vista.etiquetaError.setVisible(true);
            vista.botonGuardar.setEnabled(false);
        } else {
            inicializarVista(vista, id);
            cargarFuncionalidades(vista);
        }
        navegacion.abrirVistaSiPermitida("FormularioEditarCompraVista", vista, parent);
    }

    public CompraEntity obtenerPorId(int id) {
        return compraDao.getById(id);
    }

    public ComprasDetalleEntity obtenerDetallePorId(int id) {
        return compraDao.getByIdDetalle(id);
    }

    public ResultadoPersistencia actualizar(CompraEntity compra) {
        return compraDao.update(compra);
    }

    private void inicializarVista(FormularioEditarCompraVista vista, int id) {
        ComprasDetalleEntity detalle = obtenerDetallePorId(id);
        vista.etiquetaFacturaValor.setText(compra.getNoFactura() != null ? compra.getNoFactura() : "");
        vista.etiquetaProductoValor.setText(detalle != null ? detalle.getNombreProducto()
                : String.valueOf(compra.getIdForaneaProducto()));
        vista.inputCantidad.setText(String.valueOf(compra.getCantidadCompra()));
        vista.inputPrecio.setText(compra.getPrecioCompra().toPlainString());
        vista.comboboxMetodoPago.setSelectedItem(compra.getMetodoPago());
    }

    private void cargarFuncionalidades(FormularioEditarCompraVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    private void cancelar(FormularioEditarCompraVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioEditarCompraVista vista) {
        try {
            int cantidad = Integer.parseInt(vista.inputCantidad.getText().trim());
            BigDecimal precio = new BigDecimal(vista.inputPrecio.getText().trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(vista, "La cantidad debe ser mayor a cero");
                return;
            }
            compra.setCantidadCompra(cantidad);
            compra.setPrecioCompra(precio);
            compra.setMetodoPago((String) vista.comboboxMetodoPago.getSelectedItem());
            ResultadoPersistencia resultado = actualizar(compra);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(vista, "Compra actualizada correctamente");
                cancelar(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Cantidad o precio invalido");
        }
    }
}
