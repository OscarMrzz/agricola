package com.mycompany.agricola.controllers.ventas;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.mycompany.agricola.config.AppConfig;
import com.mycompany.agricola.model.dao.implement.VentaDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.VentaEntity;

public class FormularioEditarVentaController {

    private final VentaDaoApl ventaDao = new VentaDaoApl();

    public VentaEntity obtenerPorId(int id) {
        return ventaDao.getById(id);
    }

    public ResultadoPersistencia actualizar(VentaEntity venta) {
        BigDecimal subtotal = venta.getPrecioAntesImpuesto()
                .multiply(BigDecimal.valueOf(venta.getCantidadProducto()));
        BigDecimal impuesto = subtotal.multiply(BigDecimal.valueOf(AppConfig.ISV_PORCENTAJE))
                .setScale(2, RoundingMode.HALF_UP);

        venta.setImpuesto(impuesto);
        venta.setTotalPagar(subtotal.add(impuesto));
        return ventaDao.update(venta);
    }
}
