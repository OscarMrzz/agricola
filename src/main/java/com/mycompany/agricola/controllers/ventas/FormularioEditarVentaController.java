package com.mycompany.agricola.controllers.ventas;

import java.awt.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.config.AppConfig;
import com.mycompany.agricola.model.dao.implement.VentaDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.ventas.FormularioEditarVentaVista;

public class FormularioEditarVentaController {

    private final VentaDaoApl ventaDao = new VentaDaoApl();
    private final NavegacionService navegacion = new NavegacionService();
    private VentaEntity venta;

    public void abrir(int id, Component parent) {
        FormularioEditarVentaVista vista = new FormularioEditarVentaVista();
        venta = obtenerPorId(id);
        if (venta == null) {
            vista.etiquetaError.setText("Venta no encontrada");
            vista.etiquetaError.setVisible(true);
            vista.botonGuardar.setEnabled(false);
            vista.inputCantidad.setEnabled(false);
            vista.comboboxMetodoPago.setEnabled(false);
        } else {
            inicializarVista(vista);
            cargarFuncionalidades(vista);
        }
        navegacion.abrirFrame(vista, "Editar Venta");
    }

    public VentaEntity obtenerPorId(int id) {
        return ventaDao.getById(id);
    }

    public ResultadoPersistencia actualizar(VentaEntity venta) {
        BigDecimal subtotal = venta.getPrecioAntesImpuesto()
                .multiply(BigDecimal.valueOf(venta.getCantidadProducto()));
        BigDecimal impuesto = subtotal.multiply(BigDecimal.valueOf(AppConfig.ISV_PORCENTAJE))
                .setScale(2, RoundingMode.HALF_UP);

        venta.setImpuesto(impuesto);
        venta.setTotalPagar(subtotal.add(impuesto));
        return ventaDao.update(venta);
    }

    private void inicializarVista(FormularioEditarVentaVista vista) {
        vista.inputCantidad.setText(String.valueOf(venta.getCantidadProducto()));
        vista.comboboxMetodoPago.setSelectedItem(venta.getMetodoPago());
    }

    private void cargarFuncionalidades(FormularioEditarVentaVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    private void cancelar(FormularioEditarVentaVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioEditarVentaVista vista) {
        try {
            int cantidad = Integer.parseInt(vista.inputCantidad.getText().trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(vista, "La cantidad debe ser mayor a cero");
                return;
            }
            venta.setCantidadProducto(cantidad);
            String metodo = (String) vista.comboboxMetodoPago.getSelectedItem();
            venta.setMetodoPago(metodo);
            venta.setTipo(metodo);
            ResultadoPersistencia resultado = actualizar(venta);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(vista, "Venta actualizada correctamente");
                cancelar(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Cantidad invalida");
        }
    }
}
