package com.mycompany.agricola.views.admin.inventario;

import java.awt.Frame;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.admin.inventario.InventarioAdminController;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.views.AlertasVista;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiUtil;

public class InventarioAdminVista extends javax.swing.JPanel {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final InventarioAdminController controller = new InventarioAdminController();
    private DefaultTableModel modeloInventario;
    private List<InventarioEntity> inventarioCache = new ArrayList<>();
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnRetirar;

    public InventarioAdminVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        btnRefrescar = UiStyle.crearBotonRefrescar();
        btnRetirar = new javax.swing.JButton("Retirar");
        UiStyle.estilizarTitulo(lblTitulo);
        UiStyle.estilizarTabla(tablaInventario);
        UiStyle.estilizarScrollTabla(scrollTablaInventario);
        UiStyle.estilizarBotonNav(btnEditar);
        UiStyle.estilizarBotonNav(btnRetirar);
        UiStyle.estilizarBotonNav(btnAlertar);
        UiStyle.estilizarBotonNav(btnVolver);
        UiStyle.conIcono(btnEditar, UiIcons.EDIT);
        UiStyle.conIcono(btnRetirar, UiIcons.DELETE);
        UiStyle.conIcono(btnAlertar, UiIcons.ALERT);
        UiStyle.conIcono(btnVolver, UiIcons.EXIT);
        reorganizarLayout();
    }

    private void reorganizarLayout() {
        removeAll();
        setLayout(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_LG));
        UiStyle.aplicarPagina(this);

        JPanel encabezado = new javax.swing.JPanel(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD));
        encabezado.setOpaque(false);
        encabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        JPanel barra = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
                com.mycompany.agricola.views.util.UiTheme.SPACE_SM, 0));
        barra.setOpaque(false);
        barra.add(btnEditar);
        barra.add(btnRetirar);
        barra.add(btnAlertar);
        barra.add(btnRefrescar);
        barra.add(btnVolver);
        encabezado.add(barra, java.awt.BorderLayout.CENTER);
        add(encabezado, java.awt.BorderLayout.NORTH);
        add(scrollTablaInventario, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void inicializarLogica() {
        modeloInventario = new DefaultTableModel(
                new String[]{"No", "Producto", "Stock", "Minimo", "Prox. vencimiento", "Por vencer", "Vencido"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaInventario.setModel(modeloInventario);

        cargarDatos();

        btnEditar.addActionListener(e -> editarSeleccionado());
        btnRetirar.addActionListener(e -> retirarSeleccionado());
        btnAlertar.addActionListener(e -> UiUtil.abrirFrame(new AlertasVista(), "Alertas"));
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarDatos() {
        modeloInventario.setRowCount(0);
        inventarioCache = controller.listarInventario();
        int no = 1;
        for (InventarioEntity inv : inventarioCache) {
            String nombre = inv.getNombreProducto() != null
                    ? inv.getNombreProducto() : controller.obtenerNombreProducto(inv.getIdProducto());
            String proximoVencimiento = inv.getProximoVencimiento() != null
                    ? inv.getProximoVencimiento().format(FECHA_FORMATO) : "-";
            modeloInventario.addRow(new Object[]{
                no++,
                nombre,
                inv.getStock(),
                inv.getStockMinimo(),
                proximoVencimiento,
                inv.getCantidadPorVencer(),
                inv.getCantidadVencida()
            });
        }
    }

    private void editarSeleccionado() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaInventario);
        if (fila >= 0 && fila < inventarioCache.size()) {
            int idProducto = inventarioCache.get(fila).getIdProducto();
            UiUtil.abrirFrameFormulario(new FormularioEditarInventarioVista(idProducto), "Editar Stock Minimo");
        }
    }

    private void retirarSeleccionado() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaInventario);
        if (fila < 0 || fila >= inventarioCache.size()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un producto para retirar");
            return;
        }
        InventarioEntity seleccionado = inventarioCache.get(fila);
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
        RetiroInventarioDialog dialog = new RetiroInventarioDialog(owner, seleccionado, this::cargarDatos);
        dialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnAlertar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        scrollTablaInventario = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Inventario");

        btnEditar.setText("Editar");
        btnAlertar.setText("Alertar");
        btnVolver.setText("Volver");

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock", "Minimo", "Prox. vencimiento", "Por vencer", "Vencido"}
        ));
        scrollTablaInventario.setViewportView(tablaInventario);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollTablaInventario;
    private javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables
}
