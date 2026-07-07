package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ComprasDetalleEntity;
import com.mycompany.agricola.model.entity.FacturaCompraEntity;

public interface CompraDao {

    List<CompraEntity> getAll();

    CompraEntity getById(int id);

    ResultadoPersistencia create(CompraEntity compra);

    int createReturningId(CompraEntity compra);

    ResultadoPersistencia update(CompraEntity compra);

    ResultadoPersistencia delete(int id);

    ResultadoPersistencia deleteByFactura(String noFactura);

    List<FacturaCompraEntity> getAllFacturas();

    List<ComprasDetalleEntity> getAllDetalle();

    List<ComprasDetalleEntity> getDetalleByFactura(String noFactura);

    ComprasDetalleEntity getByIdDetalle(int id);
}
