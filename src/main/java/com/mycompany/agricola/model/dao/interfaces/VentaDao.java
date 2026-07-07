package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.FacturaVentaEntity;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;

public interface VentaDao {

    List<VentaEntity> getAll();

    VentaEntity getById(int id);

    ResultadoPersistencia create(VentaEntity venta);

    ResultadoPersistencia update(VentaEntity venta);

    ResultadoPersistencia delete(int id);

    ResultadoPersistencia deleteByFactura(String noFactura);

    List<FacturaVentaEntity> getAllFacturas();

    List<VentasDetalleEntity> getAllDetalle();

    List<VentasDetalleEntity> getDetalleByFactura(String noFactura);
}
