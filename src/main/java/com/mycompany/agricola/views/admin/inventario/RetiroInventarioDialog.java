package com.mycompany.agricola.views.admin.inventario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mycompany.agricola.controllers.admin.inventario.InventarioRetiroController;
import com.mycompany.agricola.model.entity.InventarioEntity;
import com.mycompany.agricola.services.AuthService;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class RetiroInventarioDialog extends JDialog {

    private final InventarioRetiroController controller = new InventarioRetiroController();
    private final InventarioEntity inventario;
    private final Runnable alRetirar;
    private JTextField txtCantidad;

    public RetiroInventarioDialog(Frame owner, InventarioEntity inventario, Runnable alRetirar) {
        super(owner, "Retirar producto", true);
        this.inventario = inventario;
        this.alRetirar = alRetirar;
        construirUi();
    }

    private void construirUi() {
        setLayout(new BorderLayout(0, UiTheme.SPACE_LG));
        JPanel contenido = new JPanel(new BorderLayout(0, UiTheme.SPACE_LG));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(
                UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET));

        JLabel lblAviso = new JLabel(
                "<html>Esta opcion es solo para retirar producto dañado, en mal estado, "
                        + "vencido o demasiado proximo a vencer.</html>");
        UiStyle.estilizarCuerpo(lblAviso);
        contenido.add(lblAviso, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new FlowLayout(FlowLayout.LEFT, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        formulario.setOpaque(false);
        JLabel lblProducto = new JLabel("Producto:");
        JLabel lblProductoValor = new JLabel(inventario.getNombreProducto());
        JLabel lblStock = new JLabel("Stock total:");
        JLabel lblStockValor = new JLabel(String.valueOf(inventario.getStock()));
        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField(8);
        UiStyle.estilizarInput(txtCantidad);
        UiStyle.estilizarCuerpo(lblProducto);
        UiStyle.estilizarCuerpo(lblProductoValor);
        UiStyle.estilizarCuerpo(lblStock);
        UiStyle.estilizarCuerpo(lblStockValor);
        UiStyle.estilizarCuerpo(lblCantidad);
        formulario.add(lblProducto);
        formulario.add(lblProductoValor);
        formulario.add(lblStock);
        formulario.add(lblStockValor);
        formulario.add(lblCantidad);
        formulario.add(txtCantidad);
        contenido.add(formulario, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, UiTheme.SPACE_SM, 0));
        acciones.setOpaque(false);
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        UiStyle.estilizarBoton(btnAceptar, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(btnCancelar, UiStyle.TipoBoton.SECUNDARIO);
        btnAceptar.addActionListener(e -> aceptar());
        btnCancelar.addActionListener(e -> dispose());
        acciones.add(btnAceptar);
        acciones.add(btnCancelar);
        contenido.add(acciones, BorderLayout.SOUTH);

        add(contenido, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void aceptar() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            var usuario = AuthService.getUsuarioActual();
            int idUsuario = usuario != null ? usuario.getIdUser() : 1;
            var resultado = controller.retirar(inventario, cantidad, idUsuario);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(this, "Retiro registrado correctamente");
                if (alRetirar != null) {
                    alRetirar.run();
                }
                dispose();
            } else {
                String mensaje = resultado.getError() != null
                        ? resultado.getError().getMessage() : resultado.getMensaje();
                JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad invalida");
        }
    }
}
