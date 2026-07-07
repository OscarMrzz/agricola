package com.mycompany.agricola.controllers.admin.clientes;

import java.awt.Component;
import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.model.dao.implement.ClienteDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.clientes.FormularioAgregarClienteVista;

public class FormularioAgregarClienteController {

    private final ClienteDaoApl clienteDao = new ClienteDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    public void abrir(Component parent) {
        FormularioAgregarClienteVista vista = new FormularioAgregarClienteVista();
        cargarFuncionalidades(vista);
        navegacion.abrirFrame(vista, "Agregar Cliente");
    }

    private void cargarFuncionalidades(FormularioAgregarClienteVista vista) {
        vista.botonGuardar.addActionListener(e -> guardar(vista));
        vista.botonCancelar.addActionListener(e -> cancelar(vista));
    }

    public ResultadoPersistencia crear(ClienteEntity cliente) {
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre del cliente es obligatorio"), "crear el cliente");
        }
        if (cliente.getLimiteCredito() == null || cliente.getLimiteCredito().compareTo(BigDecimal.ZERO) < 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El limite de credito debe ser mayor o igual a cero"),
                    "crear el cliente");
        }
        return clienteDao.create(cliente);
    }

    private void cancelar(FormularioAgregarClienteVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void guardar(FormularioAgregarClienteVista vista) {
        try {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNombreCliente(vista.inputNombre.getText().trim());
            cliente.setApellidoCliente(vista.inputApellido.getText().trim());
            cliente.setLimiteCredito(new BigDecimal(vista.inputLimiteCredito.getText().trim()));
            cliente.setEstado(vista.checkboxActivo.isSelected());

            ResultadoPersistencia resultado = crear(cliente);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(vista, "Cliente creado correctamente");
                cancelar(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Limite de credito invalido");
        }
    }
}
