package com.mycompany.agricola.views.compras;

import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.agricola.controllers.compras.FormularioEditarCompraController;
import com.mycompany.agricola.model.entity.CompraEntity;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioEditarCompraVista extends javax.swing.JPanel {

    private final FormularioEditarCompraController controller = new FormularioEditarCompraController();
    private CompraEntity compra;

    public FormularioEditarCompraVista(int id) {
        initComponents();
        aplicarEstilos();
        inicializarLogica(id);
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(lblTitulo);
        UiStyle.estilizarFormPanel(panelFormulario, "Datos");
        UiStyle.estilizarError(lblError);
        UiStyle.estilizarBoton(btnGuardar, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(btnCancelar, UiStyle.TipoBoton.SECUNDARIO);
        UiStyle.conIcono(btnGuardar, UiIcons.SAVE);
        UiStyle.conIcono(btnCancelar, UiIcons.CANCEL);
        UiStyle.estilizarCuerpo(lblFactura);
        UiStyle.estilizarCuerpo(lblFacturaValor);
        UiStyle.estilizarCuerpo(lblProducto);
        UiStyle.estilizarCuerpo(lblProductoValor);
        UiStyle.estilizarCuerpo(lblCantidad);
        UiStyle.estilizarInput(txtCantidad);
        UiStyle.estilizarCuerpo(lblPrecio);
        UiStyle.estilizarInput(txtPrecio);
        UiStyle.estilizarCuerpo(lblMetodoPago);
        UiStyle.estilizarInput(cmbMetodoPago);
    }

    private void inicializarLogica(int id) {
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());

        compra = controller.obtenerPorId(id);
        if (compra == null) {
            panelFormulario.setVisible(false);
            lblError.setText("Compra no encontrada");
            lblError.setVisible(true);
            btnGuardar.setEnabled(false);
            return;
        }

        var detalle = controller.obtenerDetallePorId(id);
        lblFacturaValor.setText(compra.getNoFactura() != null ? compra.getNoFactura() : "");
        lblProductoValor.setText(detalle != null ? detalle.getNombreProducto()
                : String.valueOf(compra.getIdForaneaProducto()));
        txtCantidad.setText(String.valueOf(compra.getCantidadCompra()));
        txtPrecio.setText(compra.getPrecioCompra().toPlainString());
        cmbMetodoPago.setSelectedItem(compra.getMetodoPago());
    }

    private void guardar() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            BigDecimal precio = new BigDecimal(txtPrecio.getText().trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero");
                return;
            }
            compra.setCantidadCompra(cantidad);
            compra.setPrecioCompra(precio);
            compra.setMetodoPago((String) cmbMetodoPago.getSelectedItem());
            var resultado = controller.actualizar(compra);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(this, "Compra actualizada correctamente");
                SwingUtilities.getWindowAncestor(this).dispose();
            } else {
                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad o precio invalido");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        panelFormulario = new javax.swing.JPanel();
        lblFactura = new javax.swing.JLabel();
        lblFacturaValor = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblProductoValor = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblMetodoPago = new javax.swing.JLabel();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        panelBotones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Editar Compra");

        lblError.setForeground(new java.awt.Color(204, 0, 0));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblError.setText(" ");
        lblError.setVisible(false);

        lblFactura.setText("Factura:");

        lblProducto.setText("Producto:");

        lblCantidad.setText("Cantidad:");

        lblPrecio.setText("Precio compra:");

        lblMetodoPago.setText("Metodo de pago:");

        cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "contado", "credito" }));

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(lblProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMetodoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFacturaValor, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(lblProductoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFactura)
                    .addComponent(lblFacturaValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(lblProductoValor))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMetodoPago)
                    .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(8, 8, 8)
                .addComponent(btnCancelar))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnGuardar)
                .addComponent(btnCancelar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(lblError)
                .addGap(18, 18, 18)
                .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JLabel lblFacturaValor;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProductoValor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
