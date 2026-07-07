package com.mycompany.agricola.views.admin.inventario;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.controllers.admin.inventario.FormularioEditarInventarioController;
import com.mycompany.agricola.model.entity.InventarioEntity;

public class FormularioEditarInventarioVista extends javax.swing.JPanel {

    private final FormularioEditarInventarioController controller = new FormularioEditarInventarioController();
    private InventarioEntity inventario;

    public FormularioEditarInventarioVista(int id) {
        initComponents();
        inventario = controller.obtenerPorId(id);
        if (inventario == null) {
            panelFormulario.setVisible(false);
            lblNoEncontrado.setVisible(true);
        } else {
            inicializarLogica();
        }
    }

    private void inicializarLogica() {
        lblIdProductoValor.setText(String.valueOf(inventario.getIdProducto()));
        lblStockActualValor.setText(String.valueOf(inventario.getStock()));
        txtStockMinimo.setText(String.valueOf(inventario.getStockMinimo()));
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
    }

    private void guardar() {
        try {
            int stockMinimo = Integer.parseInt(txtStockMinimo.getText().trim());
            var resultado = controller.actualizarStockMinimo(inventario.getIdInventario(), stockMinimo);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(this, "Stock minimo actualizado correctamente");
                SwingUtilities.getWindowAncestor(this).dispose();
            } else {
                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Stock minimo invalido");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNoEncontrado = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblIdProducto = new javax.swing.JLabel();
        lblIdProductoValor = new javax.swing.JLabel();
        lblStockActual = new javax.swing.JLabel();
        lblStockActualValor = new javax.swing.JLabel();
        lblStockMinimo = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblNoEncontrado.setText("Inventario no encontrado");
        lblNoEncontrado.setVisible(false);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Editar stock minimo");

        lblIdProducto.setText("ID Producto:");
        lblIdProductoValor.setText("-");
        lblStockActual.setText("Stock actual:");
        lblStockActualValor.setText("-");
        lblStockMinimo.setText("Stock minimo:");
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
                        .addComponent(lblIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblIdProductoValor))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(lblStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStockActualValor))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(lblStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblIdProducto)
                    .addComponent(lblIdProductoValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStockActual)
                    .addComponent(lblStockActualValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStockMinimo)
                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblIdProductoValor;
    private javax.swing.JLabel lblNoEncontrado;
    private javax.swing.JLabel lblStockActual;
    private javax.swing.JLabel lblStockActualValor;
    private javax.swing.JLabel lblStockMinimo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JTextField txtStockMinimo;
    // End of variables declaration//GEN-END:variables
}
