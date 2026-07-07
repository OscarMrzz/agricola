package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.model.entity.ComprasDetalleEntity;

public interface CompraDao {

    List<CompraEntity> getAll();

    CompraEntity getById(int id);

    ResultadoPersistencia create(CompraEntity compra);

    ResultadoPersistencia update(CompraEntity compra);

    ResultadoPersistencia delete(int id);

    List<ComprasDetalleEntity> getAllDetalle();

    ComprasDetalleEntity getByIdDetalle(int id);
}
