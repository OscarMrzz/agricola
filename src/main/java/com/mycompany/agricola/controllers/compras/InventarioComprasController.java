package com.mycompany.agricola.controllers.compras;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class InventarioComprasController {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();

    public List<InventarioEntity> listarInventario() {
        return inventarioDao.getAll();
    }
}
