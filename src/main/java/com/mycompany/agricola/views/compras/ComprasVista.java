package com.mycompany.agricola.views.compras;

import java.awt.Frame;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.compras.ComprasController;
import com.mycompany.agricola.model.entity.FacturaCompraEntity;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiUtil;

public class ComprasVista extends javax.swing.JPanel {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final ComprasController controller = new ComprasController();
    private DefaultTableModel modelo;
    private List<FacturaCompraEntity> facturasCache = new ArrayList<>();
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnVer;

    public ComprasVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        btnRefrescar = UiStyle.crearBotonRefrescar();
        btnVer = new javax.swing.JButton("Ver");
        UiStyle.estilizarTabla(tablaCompras);
        UiStyle.estilizarBotonNav(btnAgregar);
        UiStyle.estilizarBotonNav(btnVer);
        UiStyle.estilizarBotonNav(btnEliminar);
        UiStyle.estilizarBotonNav(btnVolver);
        UiStyle.conIcono(btnAgregar, UiIcons.ADD);
        UiStyle.conIcono(btnVer, UiIcons.VIEW);
        UiStyle.conIcono(btnEliminar, UiIcons.DELETE);
        UiStyle.conIcono(btnVolver, UiIcons.BACK);
        UiStyle.aplicarLayoutLista(this, lblTitulo, scrollTabla,
                btnAgregar, btnVer, btnEliminar, btnRefrescar, btnVolver);
    }

    private void inicializarLogica() {
        modelo = new DefaultTableModel(
                new String[]{"No", "No. Factura", "Fecha", "Metodo", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCompras.setModel(modelo);
        cargarDatos();
        btnAgregar.addActionListener(e -> UiUtil.abrirFrame(new FormularioAgregarCompraVista(), "Nueva Compra", 1120, 680));
        btnVer.addActionListener(e -> verSeleccionada());
        btnEliminar.addActionListener(e -> eliminarSeleccionada());
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        facturasCache = controller.listarFacturas();
        int no = 1;
        for (FacturaCompraEntity factura : facturasCache) {
            String fecha = factura.getFechaCompra() != null
                    ? factura.getFechaCompra().format(FECHA_FORMATO) : "-";
            modelo.addRow(new Object[]{
                no++,
                factura.getNoFactura(),
                fecha,
                factura.getMetodoPago(),
                factura.getTotal()
            });
        }
    }

    private void verSeleccionada() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaCompras);
        if (fila >= 0 && fila < facturasCache.size()) {
            FacturaCompraEntity factura = facturasCache.get(fila);
            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            VerFacturaCompraDialog dialog = new VerFacturaCompraDialog(owner, factura, controller);
            dialog.setVisible(true);
        }
    }

    private void eliminarSeleccionada() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaCompras);
        if (fila >= 0 && fila < facturasCache.size()) {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "Desea eliminar la factura seleccionada y todos sus registros?",
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                String noFactura = facturasCache.get(fila).getNoFactura();
                var resultado = controller.eliminarFactura(noFactura);
                if (resultado.isExito()) {
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaCompras = new javax.swing.JTable();

        lblTitulo.setText("Listado de compras");
        btnAgregar.setText("Agregar");
        btnEliminar.setText("Eliminar");
        btnVolver.setText("Volver");

        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "No. Factura", "Fecha", "Metodo", "Total"}
        ));
        scrollTabla.setViewportView(tablaCompras);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaCompras;
    // End of variables declaration//GEN-END:variables
}
