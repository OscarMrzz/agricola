package com.mycompany.agricola.controllers.admin.clientes;

import java.awt.Component;
import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.model.dao.implement.ClienteDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.clientes.FormularioEditarClienteVista;

public class FormularioEditarClienteController {

    private final ClienteDaoApl clienteDao = new ClienteDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    private ClienteEntity cliente;

    public void abrir(int id, Component parent) {
        FormularioEditarClienteVista vista = new FormularioEditarClienteVista();
        cliente = obtenerPorId(id);
        if (cliente == null) {
            vista.panelFormulario.setVisible(false);
            vista.etiquetaNoEncontrado.setVisible(true);
        } else {
            inicializarVista(vista);
            cargarFuncionalidades(vista);
        }
        navegacion.abrirFrame(vista, "Editar Cliente");
    }

    private void inicializarVista(FormularioEditarClienteVista vista) {
        vista.inputNombre.setText(cliente.getNombreCliente());
        vista.inputApellido.setText(cliente.getApellidoCliente());
        vista.inputLimiteCredito.setText(cliente.getLimiteCredito().toPlainString());
        vista.checkboxActivo.setSelected(cliente.isEstado());
    }

    private void cargarFuncionalidades(FormularioEditarClienteVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    public ClienteEntity obtenerPorId(int id) {
        return clienteDao.getById(id);
    }

    public ResultadoPersistencia actualizar(ClienteEntity clienteActualizado) {
        if (clienteActualizado.getNombreCliente() == null || clienteActualizado.getNombreCliente().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre del cliente es obligatorio"), "actualizar el cliente");
        }
        if (clienteActualizado.getLimiteCredito() == null
                || clienteActualizado.getLimiteCredito().compareTo(BigDecimal.ZERO) < 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El limite de credito debe ser mayor o igual a cero"),
                    "actualizar el cliente");
        }
        return clienteDao.update(clienteActualizado);
    }

    private void cancelar(FormularioEditarClienteVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioEditarClienteVista vista) {
        try {
            cliente.setNombreCliente(vista.inputNombre.getText().trim());
            cliente.setApellidoCliente(vista.inputApellido.getText().trim());
            cliente.setLimiteCredito(new BigDecimal(vista.inputLimiteCredito.getText().trim()));
            cliente.setEstado(vista.checkboxActivo.isSelected());

            ResultadoPersistencia resultado = actualizar(cliente);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(vista, "Cliente actualizado correctamente");
                cancelar(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Limite de credito invalido");
        }
    }
}
