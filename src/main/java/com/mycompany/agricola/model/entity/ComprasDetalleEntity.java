package com.mycompany.agricola.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ComprasDetalleEntity {

    private int idCompra;
    private String noFactura;
    private LocalDateTime fechaCompra;
    private String nombreProducto;
    private int cantidadProducto;
    private String metodoPago;
    private BigDecimal precioUnitario;
    private BigDecimal totalAntesImpuesto;
    private BigDecimal impuesto;
    private BigDecimal totalAPagar;

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }
    public String getNoFactura() { return noFactura; }
    public void setNoFactura(String noFactura) { this.noFactura = noFactura; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public int getCantidadProducto() { return cantidadProducto; }
    public void setCantidadProducto(int cantidadProducto) { this.cantidadProducto = cantidadProducto; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getTotalAntesImpuesto() { return totalAntesImpuesto; }
    public void setTotalAntesImpuesto(BigDecimal totalAntesImpuesto) { this.totalAntesImpuesto = totalAntesImpuesto; }
    public BigDecimal getImpuesto() { return impuesto; }
    public void setImpuesto(BigDecimal impuesto) { this.impuesto = impuesto; }
    public BigDecimal getTotalAPagar() { return totalAPagar; }
    public void setTotalAPagar(BigDecimal totalAPagar) { this.totalAPagar = totalAPagar; }
}
