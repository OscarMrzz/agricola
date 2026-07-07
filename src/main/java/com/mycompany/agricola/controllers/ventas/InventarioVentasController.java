package com.mycompany.agricola.controllers.ventas;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class InventarioVentasController {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();

    public List<InventarioEntity> listarInventario() {
        return inventarioDao.getAll();
    }
}
