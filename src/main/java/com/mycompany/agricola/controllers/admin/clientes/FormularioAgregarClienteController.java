package com.mycompany.agricola.controllers.admin.clientes;

import java.math.BigDecimal;

import com.mycompany.agricola.model.dao.implement.ClienteDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;

public class FormularioAgregarClienteController {

    private final ClienteDaoApl clienteDao = new ClienteDaoApl();

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
}
