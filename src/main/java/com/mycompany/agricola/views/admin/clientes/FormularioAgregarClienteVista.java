package com.mycompany.agricola.views.admin.clientes;

import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.controllers.admin.clientes.FormularioAgregarClienteController;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioAgregarClienteVista extends javax.swing.JPanel {

    private final FormularioAgregarClienteController controller = new FormularioAgregarClienteController();

    public FormularioAgregarClienteVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarVistaFormulario(this, lblTitulo, btnGuardar, btnCancelar,
                lblNombre, txtNombre, lblApellido, txtApellido,
                lblLimiteCredito, txtLimiteCredito, lblEstado);
    }

    private void inicializarLogica() {
        txtLimiteCredito.setText("0");
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void guardar() {
        try {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNombreCliente(txtNombre.getText().trim());
            cliente.setApellidoCliente(txtApellido.getText().trim());
            cliente.setLimiteCredito(new BigDecimal(txtLimiteCredito.getText().trim()));
            cliente.setEstado(chkActivo.isSelected());

            var resultado = controller.crear(cliente);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(this, "Cliente creado correctamente");
                SwingUtilities.getWindowAncestor(this).dispose();
            } else {
                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Limite de credito invalido");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblLimiteCredito = new javax.swing.JLabel();
        txtLimiteCredito = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Agregar cliente");

        lblNombre.setText("Nombre:");
        lblApellido.setText("Apellido:");
        lblLimiteCredito.setText("Limite credito:");
        lblEstado.setText("Estado:");
        chkActivo.setText("Activo");
        chkActivo.setSelected(true);
        btnGuardar.setText("Guardar");
        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkActivo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(8, 8, 8)
                        .addComponent(btnCancelar)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLimiteCredito)
                    .addComponent(txtLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(chkActivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLimiteCredito;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtLimiteCredito;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
