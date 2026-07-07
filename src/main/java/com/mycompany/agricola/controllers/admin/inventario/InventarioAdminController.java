package com.mycompany.agricola.controllers.admin.inventario;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.services.AlertaCantidadBajaService;

public class InventarioAdminController {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();
    private final ProductoDaoApl productoDao = new ProductoDaoApl();
    private final AlertaCantidadBajaService alertaService = new AlertaCantidadBajaService();

    public List<InventarioEntity> listarInventario() {
        return inventarioDao.getAll();
    }

    public List<AdvertenciaStockBajoEntity> listarAlertasStockBajo() {
        return alertaService.obtenerAlertas();
    }

    public String obtenerNombreProducto(int idProducto) {
        ProductoEntity producto = productoDao.getById(idProducto);
        return producto != null ? producto.getNombreProducto() : String.valueOf(idProducto);
    }
}
