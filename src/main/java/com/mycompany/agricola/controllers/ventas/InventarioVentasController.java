package com.mycompany.agricola.controllers.ventas;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class InventarioVentasController {

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();
    private final ProductoDaoApl productoDao = new ProductoDaoApl();

    public List<InventarioEntity> listarInventario() {
        return inventarioDao.getAll();
    }

    public String obtenerNombreProducto(int idProducto) {
        ProductoEntity producto = productoDao.getById(idProducto);
        return producto != null ? producto.getNombreProducto() : String.valueOf(idProducto);
    }
}
