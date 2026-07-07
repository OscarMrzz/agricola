package com.mycompany.agricola.views.admin.inventario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class RetiroInventarioDialog extends JDialog {

    public JLabel etiquetaAviso;
    public JLabel etiquetaCantidad;
    public JLabel etiquetaProducto;
    public JLabel etiquetaProductoValor;
    public JLabel etiquetaStock;
    public JLabel etiquetaStockValor;
    public JButton botonAceptar;
    public JButton botonCancelar;
    public JTextField inputCantidad;

    public RetiroInventarioDialog(Frame owner) {
        super(owner, "Retirar producto", true);
        construirUi();
    }

    private void construirUi() {
        setLayout(new BorderLayout(0, UiTheme.SPACE_LG));
        JPanel contenido = new JPanel(new BorderLayout(0, UiTheme.SPACE_LG));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(
                UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET));

        etiquetaAviso = new JLabel(
                "<html>Esta opcion es solo para retirar producto dañado, en mal estado, "
                        + "vencido o demasiado proximo a vencer.</html>");
        UiStyle.estilizarCuerpo(etiquetaAviso);
        contenido.add(etiquetaAviso, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new FlowLayout(FlowLayout.LEFT, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        formulario.setOpaque(false);
        etiquetaProducto = new JLabel("Producto:");
        etiquetaProductoValor = new JLabel("-");
        etiquetaStock = new JLabel("Stock total:");
        etiquetaStockValor = new JLabel("-");
        etiquetaCantidad = new JLabel("Cantidad:");
        inputCantidad = new JTextField(8);
        UiStyle.estilizarInput(inputCantidad);
        UiStyle.estilizarCuerpo(etiquetaProducto);
        UiStyle.estilizarCuerpo(etiquetaProductoValor);
        UiStyle.estilizarCuerpo(etiquetaStock);
        UiStyle.estilizarCuerpo(etiquetaStockValor);
        UiStyle.estilizarCuerpo(etiquetaCantidad);
        formulario.add(etiquetaProducto);
        formulario.add(etiquetaProductoValor);
        formulario.add(etiquetaStock);
        formulario.add(etiquetaStockValor);
        formulario.add(etiquetaCantidad);
        formulario.add(inputCantidad);
        contenido.add(formulario, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, UiTheme.SPACE_SM, 0));
        acciones.setOpaque(false);
        botonAceptar = new JButton("Aceptar");
        botonCancelar = new JButton("Cancelar");
        UiStyle.estilizarBoton(botonAceptar, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(botonCancelar, UiStyle.TipoBoton.SECUNDARIO);
        acciones.add(botonAceptar);
        acciones.add(botonCancelar);
        contenido.add(acciones, BorderLayout.SOUTH);

        add(contenido, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
