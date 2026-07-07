package com.mycompany.agricola.controllers.admin.inventario;

import java.awt.Frame;

import javax.swing.JOptionPane;

import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.services.AuthService;
import com.mycompany.agricola.services.InventarioLoteService;
import com.mycompany.agricola.views.admin.inventario.RetiroInventarioDialog;

public class InventarioRetiroController {

    private final InventarioLoteService inventarioLoteService = new InventarioLoteService();

    public void abrir(Frame owner, InventarioEntity inventario, Runnable alRetirar) {
        RetiroInventarioDialog dialog = new RetiroInventarioDialog(owner);
        inicializarVista(dialog, inventario);
        cargarFuncionalidades(dialog, inventario, alRetirar);
        dialog.setVisible(true);
    }

    private void inicializarVista(RetiroInventarioDialog dialog, InventarioEntity inventario) {
        dialog.etiquetaProductoValor.setText(inventario.getNombreProducto());
        dialog.etiquetaStockValor.setText(String.valueOf(inventario.getStock()));
    }

    private void cargarFuncionalidades(RetiroInventarioDialog dialog, InventarioEntity inventario, Runnable alRetirar) {
        dialog.botonAceptar.addActionListener(e -> aceptar(dialog, inventario, alRetirar));
        dialog.botonCancelar.addActionListener(e -> cancelar(dialog));
    }

    public ResultadoPersistencia retirar(InventarioEntity inventario, int cantidad, int idUsuario) {
        if (inventario == null) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("Producto no encontrado"), "retirar inventario");
        }
        if (cantidad <= 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La cantidad debe ser mayor a cero"), "retirar inventario");
        }
        if (cantidad > inventario.getStock()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La cantidad no puede superar el stock total"),
                    "retirar inventario");
        }
        return inventarioLoteService.retirar(inventario.getIdProducto(), cantidad, idUsuario);
    }

    private void cancelar(RetiroInventarioDialog dialog) {
        dialog.dispose();
    }

    private void aceptar(RetiroInventarioDialog dialog, InventarioEntity inventario, Runnable alRetirar) {
        try {
            int cantidad = Integer.parseInt(dialog.inputCantidad.getText().trim());
            var usuario = AuthService.getUsuarioActual();
            int idUsuario = usuario != null ? usuario.getIdUser() : 1;
            ResultadoPersistencia resultado = retirar(inventario, cantidad, idUsuario);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(dialog, "Retiro registrado correctamente");
                if (alRetirar != null) {
                    alRetirar.run();
                }
                cancelar(dialog);
            } else {
                String mensaje = resultado.getError() != null
                        ? resultado.getError().getMessage() : resultado.getMensaje();
                JOptionPane.showMessageDialog(dialog, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Cantidad invalida");
        }
    }
}
