package com.mycompany.agricola.controllers;

import java.math.BigDecimal;

public final class TotalesLinea {

    private final BigDecimal precioUnitario;
    private final BigDecimal subtotal;
    private final BigDecimal isv;
    private final BigDecimal total;

    public TotalesLinea(BigDecimal precioUnitario, BigDecimal subtotal, BigDecimal isv, BigDecimal total) {
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.isv = isv;
        this.total = total;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getIsv() {
        return isv;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
