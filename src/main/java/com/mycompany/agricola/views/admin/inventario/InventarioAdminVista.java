package com.mycompany.agricola.views.admin.inventario;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.admin.inventario.InventarioAdminController;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.views.AlertasVista;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiUtil;

public class InventarioAdminVista extends javax.swing.JPanel {

    private final InventarioAdminController controller = new InventarioAdminController();
    private DefaultTableModel modeloInventario;
    private DefaultTableModel modeloAlertas;
    private List<InventarioEntity> inventarioCache = new ArrayList<>();
    private javax.swing.JButton btnRefrescar;

    public InventarioAdminVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        btnRefrescar = UiStyle.crearBotonRefrescar();
        UiStyle.estilizarTitulo(lblTitulo);
        UiStyle.estilizarSeccion(lblTituloAlertas);
        UiStyle.estilizarTabla(tablaInventario);
        UiStyle.estilizarTabla(tablaAlertas);
        UiStyle.estilizarScrollTabla(scrollTablaInventario);
        UiStyle.estilizarScrollTabla(scrollTablaAlertas);
        UiStyle.estilizarBotonNav(btnEditar);
        UiStyle.estilizarBotonNav(btnAlertar);
        UiStyle.estilizarBotonNav(btnVolver);
        UiStyle.conIcono(btnEditar, UiIcons.EDIT);
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
        barra.add(btnAlertar);
        barra.add(btnRefrescar);
        barra.add(btnVolver);
        encabezado.add(barra, java.awt.BorderLayout.CENTER);
        add(encabezado, java.awt.BorderLayout.NORTH);

        JPanel contenido = new javax.swing.JPanel(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_XXL));
        contenido.setOpaque(false);
        contenido.add(scrollTablaInventario, java.awt.BorderLayout.CENTER);

        JPanel seccionAlertas = new javax.swing.JPanel(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD));
        seccionAlertas.setOpaque(false);
        seccionAlertas.add(lblTituloAlertas, java.awt.BorderLayout.NORTH);
        seccionAlertas.add(scrollTablaAlertas, java.awt.BorderLayout.CENTER);
        contenido.add(seccionAlertas, java.awt.BorderLayout.SOUTH);
        scrollTablaAlertas.setPreferredSize(new java.awt.Dimension(0, 180));

        add(contenido, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void inicializarLogica() {
        modeloInventario = new DefaultTableModel(
                new String[]{"No", "Producto", "Stock", "Minimo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaInventario.setModel(modeloInventario);

        modeloAlertas = new DefaultTableModel(
                new String[]{"No", "Producto", "Stock"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAlertas.setModel(modeloAlertas);

        cargarDatos();
        cargarAlertas();

        btnEditar.addActionListener(e -> editarSeleccionado());
        btnAlertar.addActionListener(e -> UiUtil.abrirFrame(new AlertasVista(), "Alertas"));
        btnRefrescar.addActionListener(e -> refrescarDatos());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void refrescarDatos() {
        cargarDatos();
        cargarAlertas();
    }

    private void cargarDatos() {
        modeloInventario.setRowCount(0);
        inventarioCache = controller.listarInventario();
        int no = 1;
        for (InventarioEntity inv : inventarioCache) {
            modeloInventario.addRow(new Object[]{
                no++,
                controller.obtenerNombreProducto(inv.getIdProducto()),
                inv.getStock(),
                inv.getStockMinimo()
            });
        }
    }

    private void cargarAlertas() {
        modeloAlertas.setRowCount(0);
        int no = 1;
        for (AdvertenciaStockBajoEntity a : controller.listarAlertasStockBajo()) {
            modeloAlertas.addRow(new Object[]{
                no++,
                a.getNombreProducto(),
                a.getStockActual()
            });
        }
    }

    private void editarSeleccionado() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaInventario);
        if (fila >= 0 && fila < inventarioCache.size()) {
            int id = inventarioCache.get(fila).getIdInventario();
            UiUtil.abrirFrameFormulario(new FormularioEditarInventarioVista(id), "Editar Stock Minimo");
        }
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
        lblTituloAlertas = new javax.swing.JLabel();
        scrollTablaAlertas = new javax.swing.JScrollPane();
        tablaAlertas = new javax.swing.JTable();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Inventario");

        btnEditar.setText("Editar");
        btnAlertar.setText("Alertar");
        btnVolver.setText("Volver");

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock", "Minimo"}
        ));
        scrollTablaInventario.setViewportView(tablaInventario);

        lblTituloAlertas.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTituloAlertas.setText("Alertas stock bajo");

        tablaAlertas.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock"}
        ));
        scrollTablaAlertas.setViewportView(tablaAlertas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(8, 8, 8)
                        .addComponent(btnAlertar)
                        .addGap(8, 8, 8)
                        .addComponent(btnVolver))
                    .addComponent(scrollTablaInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addComponent(lblTituloAlertas)
                    .addComponent(scrollTablaAlertas, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnAlertar)
                    .addComponent(btnVolver))
                .addGap(18, 18, 18)
                .addComponent(scrollTablaInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblTituloAlertas)
                .addGap(18, 18, 18)
                .addComponent(scrollTablaAlertas, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlertar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloAlertas;
    private javax.swing.JScrollPane scrollTablaAlertas;
    private javax.swing.JScrollPane scrollTablaInventario;
    private javax.swing.JTable tablaAlertas;
    private javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables
}
