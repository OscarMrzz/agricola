package com.mycompany.agricola.controllers.admin.inventario;

import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.services.InventarioLoteService;

public class InventarioRetiroController {

    private final InventarioLoteService inventarioLoteService = new InventarioLoteService();

    public ResultadoPersistencia retirar(InventarioEntity inventario, int cantidad, int idUsuario) {
        if (inventario == null) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("Producto no encontrado"), "retirar inventario");
        }
        if (cantidad <= 0) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La cantidad debe ser mayor a cero"), "retirar inventario");
        }
        if (cantidad > inventario.getStock()) {
            return ResultadoPersistencia.error(
                    new IllegalArgumentException("La cantidad no puede superar el stock total"),
                    "retirar inventario");
        }
        return inventarioLoteService.retirar(inventario.getIdProducto(), cantidad, idUsuario);
    }
}
