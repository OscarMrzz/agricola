package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;

public interface ClienteDao {

    List<ClienteEntity> getAll();

    ClienteEntity getById(int id);

    ResultadoPersistencia create(ClienteEntity cliente);

    ResultadoPersistencia update(ClienteEntity cliente);

    ResultadoPersistencia delete(int id);

    List<CreditosClientesDetalleEntity> getAllCreditosDetalle();

    CreditosClientesDetalleEntity getCreditoDetalleByCliente(int idCliente);
}
