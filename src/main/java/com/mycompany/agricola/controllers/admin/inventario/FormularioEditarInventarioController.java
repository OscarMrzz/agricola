package com.mycompany.agricola.controllers.admin.inventario;

import com.mycompany.agricola.model.dao.implement.InventarioConfigDaoApl;
import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class FormularioEditarInventarioController {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();
    private final InventarioConfigDaoApl configDao = new InventarioConfigDaoApl();

    public InventarioEntity obtenerPorId(int idProducto) {
        return inventarioDao.getByProductoId(idProducto);
    }

    public ResultadoPersistencia actualizarStockMinimo(int idProducto, int stockMinimo) {
        if (stockMinimo < 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El stock minimo no puede ser negativo"),
                    "actualizar el stock minimo");
        }

        InventarioEntity inventario = inventarioDao.getByProductoId(idProducto);
        if (inventario == null) {
            configDao.asegurarConfig(idProducto);
        }

        return configDao.actualizarStockMinimo(idProducto, stockMinimo);
    }
}
