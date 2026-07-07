package com.mycompany.agricola.controllers.admin.inventario;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.dao.implement.InventarioConfigDaoApl;
import com.mycompany.agricola.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.inventario.FormularioEditarInventarioVista;

public class FormularioEditarInventarioController {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();
    private final InventarioConfigDaoApl configDao = new InventarioConfigDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    private InventarioEntity inventario;

    public void abrir(int idProducto, Component parent) {
        FormularioEditarInventarioVista vista = new FormularioEditarInventarioVista();
        inventario = obtenerPorId(idProducto);
        if (inventario == null) {
            vista.panelFormulario.setVisible(false);
            vista.etiquetaNoEncontrado.setVisible(true);
        } else {
            inicializarVista(vista);
            cargarFuncionalidades(vista);
        }
        navegacion.abrirFrame(vista, "Editar Stock Minimo");
    }

    private void inicializarVista(FormularioEditarInventarioVista vista) {
        vista.etiquetaIdProductoValor.setText(String.valueOf(inventario.getIdProducto()));
        vista.etiquetaStockActualValor.setText(String.valueOf(inventario.getStock()));
        vista.inputStockMinimo.setText(String.valueOf(inventario.getStockMinimo()));
    }

    private void cargarFuncionalidades(FormularioEditarInventarioVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    public InventarioEntity obtenerPorId(int idProducto) {
        return inventarioDao.getByProductoId(idProducto);
    }

    public ResultadoPersistencia actualizarStockMinimo(int idProducto, int stockMinimo) {
        if (stockMinimo < 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El stock minimo no puede ser negativo"),
                    "actualizar el stock minimo");
        }

        InventarioEntity inv = inventarioDao.getByProductoId(idProducto);
        if (inv == null) {
            configDao.asegurarConfig(idProducto);
        }

        return configDao.actualizarStockMinimo(idProducto, stockMinimo);
    }

    private void cancelar(FormularioEditarInventarioVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioEditarInventarioVista vista) {
        try {
            int stockMinimo = Integer.parseInt(vista.inputStockMinimo.getText().trim());
            ResultadoPersistencia resultado = actualizarStockMinimo(inventario.getIdProducto(), stockMinimo);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(vista, "Stock minimo actualizado correctamente");
                cancelar(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Stock minimo invalido");
        }
    }
}
