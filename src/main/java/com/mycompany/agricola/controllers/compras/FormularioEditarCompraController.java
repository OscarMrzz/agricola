package com.mycompany.agricola.controllers.compras;

import com.mycompany.agricola.model.dao.implement.CompraDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ComprasDetalleEntity;

public class FormularioEditarCompraController {

    private final CompraDaoApl compraDao = new CompraDaoApl();

    public CompraEntity obtenerPorId(int id) {
        return compraDao.getById(id);
    }

    public ComprasDetalleEntity obtenerDetallePorId(int id) {
        return compraDao.getByIdDetalle(id);
    }

    public ResultadoPersistencia actualizar(CompraEntity compra) {
        return compraDao.update(compra);
    }
}
