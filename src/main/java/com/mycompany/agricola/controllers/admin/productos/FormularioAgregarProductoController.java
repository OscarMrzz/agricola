package com.mycompany.agricola.controllers.admin.productos;

import java.math.BigDecimal;

import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ProductoEntity;

public class FormularioAgregarProductoController {

    private final ProductoDaoApl productoDao = new ProductoDaoApl();

    public ResultadoPersistencia crear(ProductoEntity producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isBlank()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El nombre del producto es obligatorio"), "crear el producto");
        }
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta().compareTo(BigDecimal.ZERO) <= 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("El precio de venta debe ser mayor a cero"), "crear el producto");
        }
        if (producto.getFechaVencimiento() == null) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La fecha de vencimiento es obligatoria"), "crear el producto");
        }
        return productoDao.create(producto);
    }
}
