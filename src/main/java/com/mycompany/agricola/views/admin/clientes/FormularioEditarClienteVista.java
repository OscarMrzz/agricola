package com.mycompany.agricola.views.admin.clientes;

import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.controllers.admin.clientes.FormularioEditarClienteController;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioEditarClienteVista extends javax.swing.JPanel {

    private final FormularioEditarClienteController controller = new FormularioEditarClienteController();
    private ClienteEntity cliente;

    public FormularioEditarClienteVista(int id) {
        initComponents();
        aplicarEstilos();
        cliente = controller.obtenerPorId(id);
        if (cliente == null) {
            panelFormulario.setVisible(false);
            lblNoEncontrado.setVisible(true);
        } else {
            inicializarLogica();
        }
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(lblTitulo);
        UiStyle.estilizarFormPanel(panelFormulario, "Datos");
        UiStyle.estilizarError(lblNoEncontrado);
        UiStyle.estilizarBoton(btnGuardar, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(btnCancelar, UiStyle.TipoBoton.SECUNDARIO);
        UiStyle.conIcono(btnGuardar, UiIcons.SAVE);
        UiStyle.conIcono(btnCancelar, UiIcons.CANCEL);
        UiStyle.estilizarCuerpo(lblNombre);
        UiStyle.estilizarInput(txtNombre);
        UiStyle.estilizarCuerpo(lblApellido);
        UiStyle.estilizarInput(txtApellido);
        UiStyle.estilizarCuerpo(lblLimiteCredito);
        UiStyle.estilizarInput(txtLimiteCredito);
        UiStyle.estilizarCuerpo(lblEstado);
    }

    private void inicializarLogica() {
        txtNombre.setText(cliente.getNombreCliente());
        txtApellido.setText(cliente.getApellidoCliente());
        txtLimiteCredito.setText(cliente.getLimiteCredito().toPlainString());
        chkActivo.setSelected(cliente.isEstado());
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void guardar() {
        try {
            cliente.setNombreCliente(txtNombre.getText().trim());
            cliente.setApellidoCliente(txtApellido.getText().trim());
            cliente.setLimiteCredito(new BigDecimal(txtLimiteCredito.getText().trim()));
            cliente.setEstado(chkActivo.isSelected());

            var resultado = controller.actualizar(cliente);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
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

        lblNoEncontrado = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
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

        lblNoEncontrado.setText("Cliente no encontrado");
        lblNoEncontrado.setVisible(false);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Editar cliente");

        lblNombre.setText("Nombre:");
        lblApellido.setText("Apellido:");
        lblLimiteCredito.setText("Limite credito:");
        lblEstado.setText("Estado:");
        chkActivo.setText("Activo");
        btnGuardar.setText("Guardar");
        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(lblLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkActivo))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(8, 8, 8)
                        .addComponent(btnCancelar)))
                .addGap(20, 20, 20))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLimiteCredito)
                    .addComponent(txtLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(chkActivo))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNoEncontrado)
            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNoEncontrado)
            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLimiteCredito;
    private javax.swing.JLabel lblNoEncontrado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtLimiteCredito;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
