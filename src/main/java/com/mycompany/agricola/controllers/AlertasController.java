package com.mycompany.agricola.controllers;

import java.util.List;

import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.services.AlertaCantidadBajaService;
import com.mycompany.agricola.services.AlertaProductoPorVencerService;

public class AlertasController {

    private final AlertaProductoPorVencerService alertaVencimientoService = new AlertaProductoPorVencerService();
    private final AlertaCantidadBajaService alertaStockService = new AlertaCantidadBajaService();

    public List<AdvertenciaVencimientoEntity> listarProductosPorVencer() {
        return alertaVencimientoService.obtenerAlertas();
    }

    public List<AdvertenciaStockBajoEntity> listarStockBajo() {
        return alertaStockService.obtenerAlertas();
    }
}
