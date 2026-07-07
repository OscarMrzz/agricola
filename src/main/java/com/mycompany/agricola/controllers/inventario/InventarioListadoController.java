package com.mycompany.agricola.controllers.inventario;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.dao.implement.InventarioDaoApl;
import com.mycompany.agricola.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class InventarioListadoController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String[] COLUMNAS = {
        "No", "Producto", "Stock", "Minimo", "Prox. vencimiento", "Por vencer", "Vencido"
    };

    private final InventarioDaoApl inventarioDao = new InventarioDaoApl();
    private final ProductoDaoApl productoDao = new ProductoDaoApl();
    private final NavegacionService navegacion = new NavegacionService();

    public void abrirLectura(Component parent, String nombreVista, InventarioLecturaVista vista) {
        inicializarVista(vista);
        cargarFuncionalidades(vista);
        navegacion.abrirVistaSiPermitida(nombreVista, vista, parent);
    }

    public List<InventarioEntity> listarInventario() {
        return inventarioDao.getAll();
    }

    public String obtenerNombreProducto(int idProducto) {
        ProductoEntity producto = productoDao.getById(idProducto);
        return producto != null ? producto.getNombreProducto() : String.valueOf(idProducto);
    }

    private void inicializarVista(InventarioLecturaVista vista) {
        DefaultTableModel modelo = TablaCrudHelper.crearModeloNoEditable(COLUMNAS);
        vista.tablaInventario.setModel(modelo);
        refrescarTabla(vista.tablaInventario);
    }

    private void cargarFuncionalidades(InventarioLecturaVista vista) {
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista.tablaInventario));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    private void volver(InventarioLecturaVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    public void refrescarTabla(JTable tabla) {
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (InventarioEntity inv : listarInventario()) {
            String nombre = inv.getNombreProducto() != null
                    ? inv.getNombreProducto() : obtenerNombreProducto(inv.getIdProducto());
            String proximoVencimiento = inv.getProximoVencimiento() != null
                    ? inv.getProximoVencimiento().format(FECHA_FORMATO) : "-";
            filas.add(new Object[]{
                no++,
                nombre,
                inv.getStock(),
                inv.getStockMinimo(),
                proximoVencimiento,
                inv.getCantidadPorVencer(),
                inv.getCantidadVencida()
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) tabla.getModel(), filas);
    }

    public static abstract class InventarioLecturaVista extends javax.swing.JPanel {
        public JLabel etiquetaTitulo;
        public JButton botonVolver;
        public JButton botonRefrescar;
        public JScrollPane scrollTabla;
        public JTable tablaInventario;

        protected void aplicarEstilosLectura() {
            botonRefrescar = UiStyle.crearBotonRefrescar();
            UiStyle.estilizarTabla(tablaInventario);
            UiStyle.estilizarBotonNav(botonVolver);
            UiStyle.conIcono(botonVolver, UiIcons.EXIT);
            UiStyle.aplicarLayoutLista(this, etiquetaTitulo, scrollTabla, botonRefrescar, botonVolver);
        }
    }
}
