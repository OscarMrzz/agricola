package com.mycompany.agricola.services;

import java.util.List;
import com.mycompany.agricola.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class ProductosAptosParaVenderService {

    private final ProductoDaoApl productoDao = new ProductoDaoApl();

    public List<ProductoEntity> obtenerProductosAptos() {
        return productoDao.getAptosParaVenta();
    }
}
