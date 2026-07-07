package com.mycompany.agricola.views.ventas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.ventas.VentasController;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;
import com.mycompany.agricola.views.util.UiUtil;

public class VentasVista extends javax.swing.JPanel {

    private final VentasController controller = new VentasController();
    private DefaultTableModel modelo;
    private List<VentasDetalleEntity> ventasCache = new ArrayList<>();

    public VentasVista() {
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        modelo = new DefaultTableModel(
                new String[]{"No", "No.Factura", "Cliente", "Fecha", "Producto", "Cant.",
                    "Metodo", "Precio", "Subtotal", "ISV", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaVentas.setModel(modelo);
        cargarDatos();
        btnAgregar.addActionListener(e -> UiUtil.abrirFrame(new FormularioAgregarVentaVista(), "Nueva Venta", 1100, 620));
        btnEditar.addActionListener(e -> editarSeleccionado());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        ventasCache = controller.listarVentasDetalle();
        int no = 1;
        for (VentasDetalleEntity v : ventasCache) {
            modelo.addRow(new Object[]{
                no++,
                v.getNoFactura(),
                v.getCliente(),
                v.getFechaVenta(),
                v.getNombreProducto(),
                v.getCantidadProducto(),
                v.getMetodoPago(),
                v.getPrecioUnitario(),
                v.getSubtotal(),
                v.getImpuesto(),
                v.getTotalAPagar()
            });
        }
    }

    private void editarSeleccionado() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaVentas);
        if (fila >= 0 && fila < ventasCache.size()) {
            int id = ventasCache.get(fila).getIdVenta();
            UiUtil.abrirFrame(new FormularioEditarVentaVista(id), "Editar Venta");
        }
    }

    private void eliminarSeleccionado() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaVentas);
        if (fila >= 0 && fila < ventasCache.size() && UiUtil.confirmarEliminar(this)) {
            int id = ventasCache.get(fila).getIdVenta();
            var resultado = controller.eliminar(id);
            if (resultado.isExito()) {
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Listado de ventas");

        btnAgregar.setText("Agregar");
        btnEditar.setText("Editar");
        btnEliminar.setText("Eliminar");
        btnVolver.setText("Volver");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "No.Factura", "Cliente", "Fecha", "Producto", "Cant.", "Metodo", "Precio", "Subtotal", "ISV", "Total"}
        ));
        scrollTabla.setViewportView(tablaVentas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(8, 8, 8)
                        .addComponent(btnEditar)
                        .addGap(8, 8, 8)
                        .addComponent(btnEliminar)
                        .addGap(8, 8, 8)
                        .addComponent(btnVolver))
                    .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnVolver))
                .addGap(18, 18, 18)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaVentas;
    // End of variables declaration//GEN-END:variables
}
