package com.mycompany.agricola.controllers.admin.clientes;

import java.math.BigDecimal;

import com.mycompany.agricola.model.dao.implement.ClienteDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;

public class FormularioEditarClienteController {

    private final ClienteDaoApl clienteDao = new ClienteDaoApl();

    public ClienteEntity obtenerPorId(int id) {
        return clienteDao.getById(id);
    }

    public ResultadoPersistencia actualizar(ClienteEntity cliente) {
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre del cliente es obligatorio"), "actualizar el cliente");
        }
        if (cliente.getLimiteCredito() == null || cliente.getLimiteCredito().compareTo(BigDecimal.ZERO) < 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El limite de credito debe ser mayor o igual a cero"),
                    "actualizar el cliente");
        }
        return clienteDao.update(cliente);
    }
}
