package com.mycompany.agricola.views.ventas;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.ventas.ClientesVentasController;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;

public class ClientesVentasVista extends javax.swing.JPanel {

    private final ClientesVentasController controller = new ClientesVentasController();
    private DefaultTableModel modelo;

    public ClientesVentasVista() {
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        modelo = new DefaultTableModel(
                new String[]{"No", "Nombre", "Apellido", "Credito max", "Credito actual", "Diferencia"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClientes.setModel(modelo);
        cargarDatos();
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        int no = 1;
        for (CreditosClientesDetalleEntity c : controller.listarCreditos()) {
            modelo.addRow(new Object[]{
                no++,
                c.getNombreCliente(),
                c.getApellidoCliente(),
                c.getCreditoMaximo(),
                c.getCreditoActual(),
                c.getDiferencia()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Clientes Creditos");

        btnVolver.setText("Volver");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Nombre", "Apellido", "Credito max", "Credito actual", "Diferencia"}
        ));
        scrollTabla.setViewportView(tablaClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(btnVolver)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addGap(18, 18, 18)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
