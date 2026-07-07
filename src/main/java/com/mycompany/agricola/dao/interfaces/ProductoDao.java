package com.mycompany.agricola.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;

public interface ProductoDao {

    List<ProductoEntity> getAll();

    ProductoEntity getById(int id);

    ResultadoPersistencia create(ProductoEntity producto);

    ResultadoPersistencia update(ProductoEntity producto);

    ResultadoPersistencia delete(int id);

    List<ProductoEntity> getAptosParaVenta();

    List<AdvertenciaVencimientoEntity> getProximosAVencer(int dias);
}
