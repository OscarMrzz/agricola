package com.mycompany.agricola.services;

import java.util.List;
import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;

public class AlertaCantidadBajaService {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();

    public List<AdvertenciaStockBajoEntity> obtenerAlertas() {
        return inventarioDao.getStockBajo();
    }
}
