package com.mycompany.agricola.controllers.ventas;

import java.awt.Component;
import java.awt.Frame;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.dao.implement.VentaDaoApl;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.FacturaVentaEntity;
import com.mycompany.agricola.model.entity.VentaEntity;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.util.UiUtil;
import com.mycompany.agricola.views.ventas.VentasVista;
import com.mycompany.agricola.views.ventas.VerFacturaDialog;

public class VentasController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String[] COLUMNAS = {"No", "No. Factura", "Fecha", "Cliente", "Total"};

    private final VentaDaoApl ventaDao = new VentaDaoApl();
    private final NavegacionService navegacion = new NavegacionService();
    private final FormularioAgregarVentaController formularioAgregarController = new FormularioAgregarVentaController();

    private List<FacturaVentaEntity> facturasCache = new ArrayList<>();

    public void abrir(Component parent) {
        VentasVista vista = new VentasVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista, parent);
        navegacion.abrirVistaSiPermitida("VentasVista", vista, parent);
    }

    public List<FacturaVentaEntity> listarFacturas() {
        return ventaDao.getAllFacturas();
    }

    public List<VentasDetalleEntity> listarDetalleFactura(String noFactura) {
        return ventaDao.getDetalleByFactura(noFactura);
    }

    public List<VentasDetalleEntity> listarVentasDetalle() {
        return ventaDao.getAllDetalle();
    }

    public VentaEntity obtenerPorId(int id) {
        return ventaDao.getById(id);
    }

    public ResultadoPersistencia eliminarFactura(String noFactura) {
        return ventaDao.deleteByFactura(noFactura);
    }

    public ResultadoPersistencia eliminar(int id) {
        return ventaDao.delete(id);
    }

    private void inicializarVista(VentasVista vista) {
        vista.tablaVentas.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
        refrescarTabla(vista);
    }

    private void cargarFuncionalidades(VentasVista vista, Component parent) {
        vista.botonAgregar.addActionListener(e -> formularioAgregarController.abrir(parent));
        vista.botonVer.addActionListener(e -> ver(vista));
        vista.botonEliminar.addActionListener(e -> eliminar(vista));
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    private void volver(VentasVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void refrescarTabla(VentasVista vista) {
        facturasCache = listarFacturas();
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (FacturaVentaEntity factura : facturasCache) {
            String fecha = factura.getFechaVenta() != null
                    ? factura.getFechaVenta().format(FECHA_FORMATO) : "-";
            filas.add(new Object[]{
                no++,
                factura.getNoFactura(),
                fecha,
                factura.getCliente(),
                factura.getTotal()
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) vista.tablaVentas.getModel(), filas);
    }

    private void ver(VentasVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaVentas);
        if (fila >= 0 && fila < facturasCache.size()) {
            FacturaVentaEntity factura = facturasCache.get(fila);
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(vista);
            abrirVerFactura(owner, factura);
        }
    }

    public void abrirVerFactura(Frame owner, FacturaVentaEntity factura) {
        List<VentasDetalleEntity> lineas = listarDetalleFactura(factura.getNoFactura());
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (VentasDetalleEntity linea : lineas) {
            filas.add(new Object[]{
                no++,
                linea.getNombreProducto(),
                linea.getCantidadProducto(),
                linea.getPrecioUnitario(),
                linea.getSubtotal(),
                linea.getImpuesto(),
                linea.getTotalAPagar()
            });
        }
        String fecha = factura.getFechaVenta() != null
                ? factura.getFechaVenta().format(FECHA_FORMATO) : "-";
        String subtotal = factura.getSubtotal() != null ? factura.getSubtotal().toPlainString() : "0.00";
        String isv = factura.getImpuesto() != null ? factura.getImpuesto().toPlainString() : "0.00";
        String total = factura.getTotal() != null ? factura.getTotal().toPlainString() : "0.00";
        VerFacturaDialog dialog = new VerFacturaDialog(
                owner,
                factura.getNoFactura(),
                factura.getCliente() != null ? factura.getCliente() : "-",
                fecha,
                filas,
                subtotal,
                isv,
                total);
        dialog.setVisible(true);
    }

    private void eliminar(VentasVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaVentas);
        if (fila >= 0 && fila < facturasCache.size() && UiUtil.confirmarEliminar(vista)) {
            String noFactura = facturasCache.get(fila).getNoFactura();
            ResultadoPersistencia resultado = eliminarFactura(noFactura);
            if (resultado.isExito()) {
                refrescarTabla(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
