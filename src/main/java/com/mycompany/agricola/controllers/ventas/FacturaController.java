package com.mycompany.agricola.controllers.ventas;

import com.mycompany.agricola.reportes.ReporteService;

public class FacturaController {

    private final ReporteService reporteService = new ReporteService();

    public byte[] generarFacturaVenta(String noFactura) throws Exception {
        return reporteService.generarFacturaVenta(noFactura);
    }

    public byte[] generarReporteInsumosCriticos() throws Exception {
        return reporteService.generarReporteInsumosCriticos();
    }
}
