package com.mycompany.agricola.views.admin.clientes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.admin.clientes.ClientesAdminController;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiUtil;

public class ClientesAdminVista extends javax.swing.JPanel {

    private final ClientesAdminController controller = new ClientesAdminController();
    private DefaultTableModel modelo;
    private List<ClienteEntity> clientesCache = new ArrayList<>();
    private javax.swing.JButton btnRefrescar;

    public ClientesAdminVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        btnRefrescar = UiStyle.crearBotonRefrescar();
        UiStyle.estilizarTabla(tablaClientes);
        UiStyle.estilizarBotonNav(btnAgregar);
        UiStyle.estilizarBotonNav(btnEditar);
        UiStyle.estilizarBotonNav(btnEliminar);
        UiStyle.estilizarBotonNav(btnVolver);
        UiStyle.conIcono(btnAgregar, UiIcons.ADD);
        UiStyle.conIcono(btnEditar, UiIcons.EDIT);
        UiStyle.conIcono(btnEliminar, UiIcons.DELETE);
        UiStyle.conIcono(btnVolver, UiIcons.EXIT);
        UiStyle.aplicarLayoutLista(this, lblTitulo, scrollTabla,
                btnAgregar, btnEditar, btnEliminar, btnRefrescar, btnVolver);
    }

    private void inicializarLogica() {
        modelo = new DefaultTableModel(
                new String[]{"No", "Nombre", "Apellido", "Estatus"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClientes.setModel(modelo);
        cargarDatos();
        btnAgregar.addActionListener(e -> UiUtil.abrirFrameFormulario(new FormularioAgregarClienteVista(), "Agregar Cliente"));
        btnEditar.addActionListener(e -> editarSeleccionado());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        clientesCache = controller.listarClientes();
        int no = 1;
        for (ClienteEntity c : clientesCache) {
            modelo.addRow(new Object[]{
                no++,
                c.getNombreCliente(),
                c.getApellidoCliente(),
                c.isEstado() ? "Si" : "No"
            });
        }
    }

    private void editarSeleccionado() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaClientes);
        if (fila >= 0 && fila < clientesCache.size()) {
            int id = clientesCache.get(fila).getIdCliente();
            UiUtil.abrirFrameFormulario(new FormularioEditarClienteVista(id), "Editar Cliente");
        }
    }

    private void eliminarSeleccionado() {
        int fila = UiUtil.obtenerFilaSeleccionada(tablaClientes);
        if (fila >= 0 && fila < clientesCache.size() && UiUtil.confirmarEliminar(this)) {
            int id = clientesCache.get(fila).getIdCliente();
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
        tablaClientes = new javax.swing.JTable();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Listado de clientes");

        btnAgregar.setText("Agregar");
        btnEditar.setText("Editar");
        btnEliminar.setText("Eliminar");
        btnVolver.setText("Volver");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Nombre", "Apellido", "Estatus"}
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
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
