package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.PagoCreditoClienteEntity;

public interface PagoCreditoClienteDao {

    List<PagoCreditoClienteEntity> getAll();

    List<PagoCreditoClienteEntity> getByCliente(int idCliente);

    ResultadoPersistencia create(PagoCreditoClienteEntity pago);

    ResultadoPersistencia delete(int id);
}
