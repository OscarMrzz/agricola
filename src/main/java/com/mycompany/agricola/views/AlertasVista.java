package com.mycompany.agricola.views;

import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.AlertasController;
import com.mycompany.agricola.model.entity.AdvertenciaStockBajoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencidoEntity;
import com.mycompany.agricola.model.entity.AdvertenciaVencimientoEntity;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class AlertasVista extends javax.swing.JPanel {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final AlertasController controller = new AlertasController();
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblVencidos;
    private javax.swing.JScrollPane scrollVencidos;
    private javax.swing.JTable tablaVencidos;

    public AlertasVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        btnRefrescar = UiStyle.crearBotonRefrescar();
        btnVolver = new javax.swing.JButton("Volver");
        lblVencidos = new javax.swing.JLabel("Productos vencidos");
        scrollVencidos = new javax.swing.JScrollPane();
        tablaVencidos = new javax.swing.JTable();
        scrollVencidos.setViewportView(tablaVencidos);
        UiStyle.estilizarBotonNav(btnVolver);
        UiStyle.conIcono(btnVolver, UiIcons.EXIT);
        UiStyle.estilizarTitulo(lblTitulo);
        UiStyle.estilizarSeccion(lblVencimiento);
        UiStyle.estilizarSeccion(lblVencidos);
        UiStyle.estilizarSeccion(lblStock);
        UiStyle.estilizarTabla(tablaVencimiento);
        UiStyle.estilizarTabla(tablaVencidos);
        UiStyle.estilizarTabla(tablaStock);
        UiStyle.estilizarScrollTabla(scrollVencimiento);
        UiStyle.estilizarScrollTabla(scrollVencidos);
        UiStyle.estilizarScrollTabla(scrollStock);
        reorganizarLayout();
    }

    private void reorganizarLayout() {
        removeAll();
        setLayout(new java.awt.BorderLayout(0, UiTheme.SPACE_LG));
        UiStyle.aplicarPagina(this);

        JPanel encabezado = new javax.swing.JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        encabezado.setOpaque(false);
        encabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        JPanel barra = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, UiTheme.SPACE_SM, 0));
        barra.setOpaque(false);
        barra.add(btnRefrescar);
        barra.add(btnVolver);
        encabezado.add(barra, java.awt.BorderLayout.CENTER);
        add(encabezado, java.awt.BorderLayout.NORTH);

        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new javax.swing.BoxLayout(contenido, javax.swing.BoxLayout.Y_AXIS));

        JPanel seccionVencimiento = new JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        seccionVencimiento.setOpaque(false);
        seccionVencimiento.add(lblVencimiento, java.awt.BorderLayout.NORTH);
        seccionVencimiento.add(scrollVencimiento, java.awt.BorderLayout.CENTER);
        scrollVencimiento.setPreferredSize(new java.awt.Dimension(0, 140));
        contenido.add(seccionVencimiento);
        contenido.add(javax.swing.Box.createVerticalStrut(UiTheme.SPACE_LG));

        JPanel seccionVencidos = new JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        seccionVencidos.setOpaque(false);
        seccionVencidos.add(lblVencidos, java.awt.BorderLayout.NORTH);
        seccionVencidos.add(scrollVencidos, java.awt.BorderLayout.CENTER);
        scrollVencidos.setPreferredSize(new java.awt.Dimension(0, 120));
        contenido.add(seccionVencidos);
        contenido.add(javax.swing.Box.createVerticalStrut(UiTheme.SPACE_LG));

        JPanel seccionStock = new JPanel(new java.awt.BorderLayout(0, UiTheme.SPACE_MD));
        seccionStock.setOpaque(false);
        seccionStock.add(lblStock, java.awt.BorderLayout.NORTH);
        seccionStock.add(scrollStock, java.awt.BorderLayout.CENTER);
        scrollStock.setPreferredSize(new java.awt.Dimension(0, 120));
        contenido.add(seccionStock);

        add(contenido, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void inicializarLogica() {
        cargarDatos();
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarDatos() {
        DefaultTableModel modeloVencimiento = new DefaultTableModel(
                new String[]{"No", "Producto", "Cantidad", "Fecha vencimiento", "Dias restantes"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int no = 1;
        for (AdvertenciaVencimientoEntity alerta : controller.listarProductosPorVencer()) {
            String fecha = alerta.getFechaVencimiento() != null
                    ? alerta.getFechaVencimiento().format(FECHA_FORMATO) : "-";
            modeloVencimiento.addRow(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getCantidad(),
                fecha,
                alerta.getDiasRestantes()
            });
        }
        tablaVencimiento.setModel(modeloVencimiento);

        DefaultTableModel modeloVencidos = new DefaultTableModel(
                new String[]{"No", "Producto", "Cantidad vencida"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        no = 1;
        for (AdvertenciaVencidoEntity alerta : controller.listarProductosVencidos()) {
            modeloVencidos.addRow(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getCantidad()
            });
        }
        tablaVencidos.setModel(modeloVencidos);

        DefaultTableModel modeloStock = new DefaultTableModel(
                new String[]{"No", "Producto", "Stock actual"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        no = 1;
        for (AdvertenciaStockBajoEntity alerta : controller.listarStockBajo()) {
            modeloStock.addRow(new Object[]{
                no++,
                alerta.getNombreProducto(),
                alerta.getStockActual()
            });
        }
        tablaStock.setModel(modeloStock);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblVencimiento = new javax.swing.JLabel();
        scrollVencimiento = new javax.swing.JScrollPane();
        tablaVencimiento = new javax.swing.JTable();
        lblStock = new javax.swing.JLabel();
        scrollStock = new javax.swing.JScrollPane();
        tablaStock = new javax.swing.JTable();

        lblTitulo.setText("Alertas");
        lblVencimiento.setText("Productos por vencer");
        tablaVencimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Cantidad", "Fecha vencimiento", "Dias restantes"}
        ));
        scrollVencimiento.setViewportView(tablaVencimiento);
        lblStock.setText("Stock bajo");
        tablaStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Producto", "Stock actual"}
        ));
        scrollStock.setViewportView(tablaStock);

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
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVencimiento;
    private javax.swing.JScrollPane scrollStock;
    private javax.swing.JScrollPane scrollVencimiento;
    private javax.swing.JTable tablaStock;
    private javax.swing.JTable tablaVencimiento;
    // End of variables declaration//GEN-END:variables
}
