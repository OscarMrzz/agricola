package com.mycompany.agricola.controllers;

import java.util.List;

import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencidoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.services.AlertaCantidadBajaService;
import com.mycompany.agricola.services.AlertaProductoPorVencerService;
import com.mycompany.agricola.services.AlertaProductoVencidoService;

public class AlertasController {

    private final AlertaProductoPorVencerService alertaVencimientoService = new AlertaProductoPorVencerService();
    private final AlertaProductoVencidoService alertaVencidoService = new AlertaProductoVencidoService();
    private final AlertaCantidadBajaService alertaStockService = new AlertaCantidadBajaService();

    public List<AdvertenciaVencimientoEntity> listarProductosPorVencer() {
        return alertaVencimientoService.obtenerAlertas();
    }

    public List<AdvertenciaVencidoEntity> listarProductosVencidos() {
        return alertaVencidoService.obtenerAlertas();
    }

    public List<AdvertenciaStockBajoEntity> listarStockBajo() {
        return alertaStockService.obtenerAlertas();
    }
}
