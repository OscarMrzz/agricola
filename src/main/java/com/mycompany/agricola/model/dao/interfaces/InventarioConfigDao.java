package com.mycompany.agricola.model.dao.interfaces;

import java.time.LocalDateTime;

import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioConfigEntity;

public interface InventarioConfigDao {

    InventarioConfigEntity getByProductoId(int idProducto);

    void asegurarConfig(int idProducto);

    ResultadoPersistencia actualizarStockMinimo(int idProducto, int stockMinimo);

    void actualizarFechaUltimaEntrada(int idProducto, LocalDateTime fecha);

    void actualizarFechaUltimaSalida(int idProducto, LocalDateTime fecha);
}
