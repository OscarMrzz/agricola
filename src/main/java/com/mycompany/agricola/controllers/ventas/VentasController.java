package com.mycompany.agricola.controllers.ventas;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.VentaDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.FacturaVentaEntity;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;

public class VentasController {

    private final VentaDaoApl ventaDao = new VentaDaoApl();

    public List<FacturaVentaEntity> listarFacturas() {
        return ventaDao.getAllFacturas();
    }

    public List<VentasDetalleEntity> listarDetalleFactura(String noFactura) {
        return ventaDao.getDetalleByFactura(noFactura);
    }

    public List<VentasDetalleEntity> listarVentasDetalle() {
        return ventaDao.getAllDetalle();
    }

    public VentaEntity obtenerPorId(int id) {
        return ventaDao.getById(id);
    }

    public ResultadoPersistencia eliminarFactura(String noFactura) {
        return ventaDao.deleteByFactura(noFactura);
    }

    public ResultadoPersistencia eliminar(int id) {
        return ventaDao.delete(id);
    }
}
