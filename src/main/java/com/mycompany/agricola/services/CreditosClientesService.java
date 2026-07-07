package com.mycompany.agricola.services;

import java.math.BigDecimal;
import java.util.List;
import com.mycompany.agricola.dao.implement.ClienteDaoApl;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;

public class CreditosClientesService {

    private final ClienteDaoApl clienteDao = new ClienteDaoApl();

    public List<CreditosClientesDetalleEntity> obtenerTodos() {
        return clienteDao.getAllCreditosDetalle();
    }

    public CreditosClientesDetalleEntity obtenerPorCliente(int idCliente) {
        return clienteDao.getCreditoDetalleByCliente(idCliente);
    }

    public void validarLimiteCredito(int idCliente, BigDecimal montoNuevaVenta) throws CreditoExcedidoException {
        CreditosClientesDetalleEntity credito = clienteDao.getCreditoDetalleByCliente(idCliente);
        if (credito == null) {
            return;
        }
        BigDecimal nuevoSaldo = credito.getCreditoActual().add(montoNuevaVenta);
        if (nuevoSaldo.compareTo(credito.getCreditoMaximo()) > 0) {
            throw new CreditoExcedidoException(
                    "El cliente excede su limite de credito. Disponible: Lps "
                            + credito.getDiferencia());
        }
    }
}
