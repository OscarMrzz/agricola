package com.mycompany.agricola.controllers.admin.productos;

import java.util.List;

import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class ProductosController {

    private final ProductoDaoApl productoDao = new ProductoDaoApl();

    public List<ProductoEntity> listar() {
        return productoDao.getAll();
    }

    public ProductoEntity obtenerPorId(int id) {
        return productoDao.getById(id);
    }

    public ResultadoPersistencia eliminar(int id) {
        return productoDao.delete(id);
    }
}
