package com.mycompany.agricola.services;

import java.util.List;

import com.mycompany.agricola.dao.implement.AlertaVencidoDaoApl;
import com.mycompany.agricola.model.entity.AdvertenciaVencidoEntity;

public class AlertaProductoVencidoService {

    private final AlertaVencidoDaoApl alertaDao = new AlertaVencidoDaoApl();

    public List<AdvertenciaVencidoEntity> obtenerAlertas() {
        return alertaDao.getVencidos();
    }
}
