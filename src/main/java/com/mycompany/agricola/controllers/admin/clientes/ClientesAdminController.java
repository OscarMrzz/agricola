package com.mycompany.agricola.controllers.admin.clientes;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.ClienteDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;
import com.mycompany.agricola.services.CreditosClientesService;

public class ClientesAdminController {

    private final ClienteDaoApl clienteDao = new ClienteDaoApl();
    private final CreditosClientesService creditosService = new CreditosClientesService();

    public List<ClienteEntity> listarClientes() {
        return clienteDao.getAll();
    }

    public List<CreditosClientesDetalleEntity> listarCreditosDetalle() {
        return creditosService.obtenerTodos();
    }

    public ClienteEntity obtenerPorId(int id) {
        return clienteDao.getById(id);
    }

    public ResultadoPersistencia eliminar(int id) {
        return clienteDao.delete(id);
    }
}
