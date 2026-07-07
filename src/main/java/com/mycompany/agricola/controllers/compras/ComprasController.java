package com.mycompany.agricola.controllers.compras;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.CompraDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ComprasDetalleEntity;

public class ComprasController {

    private final CompraDaoApl compraDao = new CompraDaoApl();

    public List<ComprasDetalleEntity> listarComprasDetalle() {
        return compraDao.getAllDetalle();
    }

    public CompraEntity obtenerPorId(int id) {
        return compraDao.getById(id);
    }

    public ResultadoPersistencia eliminar(int id) {
        return compraDao.delete(id);
    }
}
