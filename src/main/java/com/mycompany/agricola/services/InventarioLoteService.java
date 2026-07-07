package com.mycompany.agricola.services;

import java.time.LocalDateTime;

import com.mycompany.agricola.model.dao.implement.InventarioConfigDaoApl;
import com.mycompany.agricola.model.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.model.dao.implement.InventarioLoteDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class InventarioLoteService {

    public static final String MOTIVO_RETIRO =
            "Retiro de producto dañado, en mal estado, vencido o demasiado proximo a vencer";

    private final InventarioLoteDaoApl loteDao = new InventarioLoteDaoApl();
    private final InventarioConfigDaoApl configDao = new InventarioConfigDaoApl();
    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();

    public void crearLoteDesdeCompra(int idProducto, int cantidad, LocalDateTime fechaVencimiento, int idCompra) {
        if (fechaVencimiento == null) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria");
        }
        configDao.asegurarConfig(idProducto);
        loteDao.insertarLote(idProducto, cantidad, fechaVencimiento, idCompra);
        configDao.actualizarFechaUltimaEntrada(idProducto, LocalDateTime.now());
    }

    public InventarioEntity obtenerResumenPorProducto(int idProducto) {
        return inventarioDao.getByProductoId(idProducto);
    }

    public int obtenerCantidadVendible(int idProducto) {
        InventarioEntity resumen = obtenerResumenPorProducto(idProducto);
        return resumen != null ? resumen.getStockVendible() : 0;
    }

    public ResultadoPersistencia descontarParaVenta(int idProducto, int cantidad) {
        return loteDao.descontarParaVenta(idProducto, cantidad);
    }

    public ResultadoPersistencia retirar(int idProducto, int cantidad, int idUsuario) {
        return loteDao.retirar(idProducto, cantidad, idUsuario, MOTIVO_RETIRO);
    }
}
