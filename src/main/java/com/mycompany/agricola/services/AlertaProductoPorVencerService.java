package com.mycompany.agricola.services;

import java.util.List;
import com.mycompany.agricola.config.AppConfig;
import com.mycompany.agricola.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;

public class AlertaProductoPorVencerService {

    private final ProductoDaoApl productoDao = new ProductoDaoApl();

    public List<AdvertenciaVencimientoEntity> obtenerAlertas() {
        return productoDao.getProximosAVencer(AppConfig.DIAS_ALERTA_VENCIMIENTO);
    }
}
