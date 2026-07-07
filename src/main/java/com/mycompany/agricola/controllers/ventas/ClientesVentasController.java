package com.mycompany.agricola.controllers.ventas;

import java.util.List;

import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;
import com.mycompany.agricola.services.CreditosClientesService;

public class ClientesVentasController {

    private final CreditosClientesService creditosService = new CreditosClientesService();

    public List<CreditosClientesDetalleEntity> listarCreditos() {
        return creditosService.obtenerTodos();
    }
}
