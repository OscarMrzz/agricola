package com.mycompany.agricola.controllers.admin.inventario;

import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class FormularioEditarInventarioController {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();

    public InventarioEntity obtenerPorId(int id) {
        return inventarioDao.getById(id);
    }

    public ResultadoPersistencia actualizarStockMinimo(int idInventario, int stockMinimo) {
        if (stockMinimo < 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El stock minimo no puede ser negativo"),
                    "actualizar el stock minimo");
        }

        InventarioEntity inventario = inventarioDao.getById(idInventario);
        if (inventario == null) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("Inventario no encontrado"),
                    "actualizar el stock minimo");
        }

        inventario.setStockMinimo(stockMinimo);
        return inventarioDao.update(inventario);
    }
}
