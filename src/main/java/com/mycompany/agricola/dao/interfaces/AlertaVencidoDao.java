package com.mycompany.agricola.dao.interfaces;

import java.util.List;

import com.mycompany.agricola.model.entity.AdvertenciaVencidoEntity;

public interface AlertaVencidoDao {

    List<AdvertenciaVencidoEntity> getVencidos();
}
