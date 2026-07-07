package com.mycompany.agricola.controllers.ventas;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.VentaDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;

public class VentasController {

    private final VentaDaoApl ventaDao = new VentaDaoApl();

    public List<VentasDetalleEntity> listarVentasDetalle() {
        return ventaDao.getAllDetalle();
    }

    public VentaEntity obtenerPorId(int id) {
        return ventaDao.getById(id);
    }

    public ResultadoPersistencia eliminar(int id) {
        return ventaDao.delete(id);
    }
}
