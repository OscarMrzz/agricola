package com.mycompany.agricola.model.dao.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.InventarioLoteEntity;

public interface InventarioLoteDao {

    int insertarLote(int idProducto, int cantidad, LocalDateTime fechaVencimiento, Integer idCompra);

    List<InventarioLoteEntity> listarLotesVendiblesFefo(int idProducto);

    List<InventarioLoteEntity> listarLotesParaRetiro(int idProducto);

    void actualizarCantidadLote(int idLote, int nuevaCantidad);

    ResultadoPersistencia descontarParaVenta(int idProducto, int cantidad);

    ResultadoPersistencia retirar(int idProducto, int cantidad, int idUsuario, String motivo);
}
