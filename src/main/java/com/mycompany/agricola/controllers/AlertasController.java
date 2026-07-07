package com.mycompany.agricola.controllers;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencidoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.services.AlertaCantidadBajaService;
import com.mycompany.agricola.services.AlertaProductoPorVencerService;
import com.mycompany.agricola.services.AlertaProductoVencidoService;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.AlertasVista;

public class AlertasController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final AlertaProductoPorVencerService alertaVencimientoService = new AlertaProductoPorVencerService();
    private final AlertaProductoVencidoService alertaVencidoService = new AlertaProductoVencidoService();
    private final AlertaCantidadBajaService alertaStockService = new AlertaCantidadBajaService();
    private final NavegacionService navegacion = new NavegacionService();

    public void abrir(Component parent) {
        AlertasVista vista = new AlertasVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista);
        navegacion.abrirVistaSiPermitida("AlertasVista", vista, parent);
    }

    public List<AdvertenciaVencimientoEntity> listarProductosPorVencer() {
        return alertaVencimientoService.obtenerAlertas();
    }

    public List<AdvertenciaVencidoEntity> listarProductosVencidos() {
        return alertaVencidoService.obtenerAlertas();
    }

    public List<AdvertenciaStockBajoEntity> listarStockBajo() {
        return alertaStockService.obtenerAlertas();
    }

    private void inicializarVista(AlertasVista vista) {
        cargarDatos(vista);
    }

    private void cargarFuncionalidades(AlertasVista vista) {
        vista.botonRefrescar.addActionListener(e -> cargarDatos(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    private void volver(AlertasVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void cargarDatos(AlertasVista vista) {
        vista.tablaVencimiento.setModel(crearModeloVencimiento());
        vista.tablaVencidos.setModel(crearModeloVencidos());
        vista.tablaStock.setModel(crearModeloStock());
    }

    private DefaultTableModel crearModeloVencimiento() {
        DefaultTableModel modelo = TablaCrudHelper.crearModeloNoEditable(
                new String[]{"No", "Producto", "Cantidad", "Fecha vencimiento", "Dias restantes"});
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (AdvertenciaVencimientoEntity alerta : listarProductosPorVencer()) {
            String fecha = alerta.getFechaVencimiento() != null
                    ? alerta.getFechaVencimiento().format(FECHA_FORMATO) : "-";
            filas.add(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getCantidad(),
                fecha,
                alerta.getDiasRestantes()
            });
        }
        TablaCrudHelper.limpiarYLLenar(modelo, filas);
        return modelo;
    }

    private DefaultTableModel crearModeloVencidos() {
        DefaultTableModel modelo = TablaCrudHelper.crearModeloNoEditable(
                new String[]{"No", "Producto", "Cantidad vencida"});
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (AdvertenciaVencidoEntity alerta : listarProductosVencidos()) {
            filas.add(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getCantidad()
            });
        }
        TablaCrudHelper.limpiarYLLenar(modelo, filas);
        return modelo;
    }

    private DefaultTableModel crearModeloStock() {
        DefaultTableModel modelo = TablaCrudHelper.crearModeloNoEditable(
                new String[]{"No", "Producto", "Stock actual"});
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (AdvertenciaStockBajoEntity alerta : listarStockBajo()) {
            filas.add(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getStockActual()
            });
        }
        TablaCrudHelper.limpiarYLLenar(modelo, filas);
        return modelo;
    }
}
