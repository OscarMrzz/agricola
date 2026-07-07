package com.mycompany.agricola.controllers.admin.productos;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.dao.implement.ProductoDaoApl;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.admin.productos.ProductosVista;
import com.mycompany.agricola.views.util.UiUtil;

public class ProductosController {

    private static final String[] COLUMNAS = {
        "No", "Nombre", "Categoria", "Departamento", "Precio", "Vencimiento"
    };

    private final ProductoDaoApl productoDao = new ProductoDaoApl();
    private final NavegacionService navegacion = new NavegacionService();
    private final FormularioAgregarProductoController formularioAgregarController = new FormularioAgregarProductoController();
    private final FormularioEditarProductoController formularioEditarController = new FormularioEditarProductoController();

    private List<ProductoEntity> productosCache = new ArrayList<>();

    public void abrir(Component parent) {
        ProductosVista vista = new ProductosVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista, parent);
        navegacion.abrirVistaSiPermitida("ProductosVista", vista, parent);
    }

    private void inicializarVista(ProductosVista vista) {
        vista.tablaProductos.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
        refrescarTabla(vista);
    }

    private void cargarFuncionalidades(ProductosVista vista, Component parent) {
        vista.botonAgregar.addActionListener(e -> agregar(parent));
        vista.botonEditar.addActionListener(e -> editar(vista, parent));
        vista.botonEliminar.addActionListener(e -> eliminar(vista));
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    public List<ProductoEntity> listar() {
        return productoDao.getAll();
    }

    public ProductoEntity obtenerPorId(int id) {
        return productoDao.getById(id);
    }

    public ResultadoPersistencia eliminar(int id) {
        return productoDao.delete(id);
    }

    private void agregar(Component parent) {
        formularioAgregarController.abrir(parent);
    }

    private void volver(ProductosVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void refrescarTabla(ProductosVista vista) {
        productosCache = listar();
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (ProductoEntity producto : productosCache) {
            filas.add(new Object[]{
                no++,
                producto.getNombreProducto(),
                producto.getCategoriaProducto(),
                producto.getDepartamentoOrigen(),
                producto.getPrecioVenta(),
                producto.getFechaVencimiento()
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) vista.tablaProductos.getModel(), filas);
    }

    private void editar(ProductosVista vista, Component parent) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaProductos);
        if (fila >= 0 && fila < productosCache.size()) {
            int id = productosCache.get(fila).getIdProducto();
            formularioEditarController.abrir(id, parent);
        }
    }

    private void eliminar(ProductosVista vista) {
        int fila = UiUtil.obtenerFilaSeleccionada(vista.tablaProductos);
        if (fila >= 0 && fila < productosCache.size() && UiUtil.confirmarEliminar(vista)) {
            int id = productosCache.get(fila).getIdProducto();
            ResultadoPersistencia resultado = eliminar(id);
            if (resultado.isExito()) {
                refrescarTabla(vista);
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
