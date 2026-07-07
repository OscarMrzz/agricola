package com.mycompany.agricola.views.admin.usuarios;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.controllers.admin.usuarios.FormularioEditarUsuarioController;
import com.mycompany.agricola.model.entity.RolEntity;
import com.mycompany.agricola.model.entity.UsuarioEntity;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioEditarUsuarioVista extends javax.swing.JPanel {

    private final FormularioEditarUsuarioController controller = new FormularioEditarUsuarioController();
    private UsuarioEntity usuario;

    public FormularioEditarUsuarioVista(int id) {
        initComponents();
        aplicarEstilos();
        usuario = controller.obtenerPorId(id);
        if (usuario == null) {
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
        UiStyle.estilizarCuerpo(lblPassword);
        UiStyle.estilizarInput(txtPassword);
        UiStyle.estilizarCuerpo(lblRol);
        UiStyle.estilizarInput(cmbRol);
        UiStyle.estilizarCuerpo(lblEstado);
    }

    private void inicializarLogica() {
        cargarRoles();
        txtNombre.setText(usuario.getNombreUser());
        txtPassword.setText(usuario.getPasswordUser());
        chkActivo.setSelected(usuario.isEstado());
        seleccionarRol(usuario.getIdForaneaRol());
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void cargarRoles() {
        cmbRol.removeAllItems();
        for (RolEntity rol : controller.listarRoles()) {
            cmbRol.addItem(rol);
        }
    }

    private void seleccionarRol(int idRol) {
        for (int i = 0; i < cmbRol.getItemCount(); i++) {
            RolEntity rol = cmbRol.getItemAt(i);
            if (rol != null && rol.getIdRol() == idRol) {
                cmbRol.setSelectedIndex(i);
                return;
            }
        }
    }

    private void guardar() {
        RolEntity rol = (RolEntity) cmbRol.getSelectedItem();
        usuario.setNombreUser(txtNombre.getText().trim());
        usuario.setPasswordUser(new String(txtPassword.getPassword()));
        usuario.setIdForaneaRol(rol != null ? rol.getIdRol() : usuario.getIdForaneaRol());
        usuario.setEstado(chkActivo.isSelected());

        var resultado = controller.actualizar(usuario);
        if (resultado.isExito()) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
            SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
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
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblRol = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblNoEncontrado.setText("Usuario no encontrado");
        lblNoEncontrado.setVisible(false);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Editar usuario");

        lblNombre.setText("Usuario:");
        lblPassword.setText("Contrasena:");
        lblRol.setText("Rol:");
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
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbRol, 0, 260, Short.MAX_VALUE))
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
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRol)
                    .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JComboBox<RolEntity> cmbRol;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNoEncontrado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
