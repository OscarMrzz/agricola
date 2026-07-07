package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.InventarioEntity;

public interface InventarioDao {

    List<InventarioEntity> getAll();

    InventarioEntity getById(int id);

    InventarioEntity getByProductoId(int idProducto);

    ResultadoPersistencia create(InventarioEntity inventario);

    ResultadoPersistencia update(InventarioEntity inventario);

    ResultadoPersistencia delete(int id);

    List<AdvertenciaStockBajoEntity> getStockBajo();
}
