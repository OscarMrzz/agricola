package com.mycompany.agricola.views.ventas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class VerFacturaDialog extends JDialog {

    public VerFacturaDialog(Frame owner, String noFactura, String cliente, String fecha,
            List<Object[]> filasDetalle, String subtotal, String isv, String total) {
        super(owner, "Factura " + noFactura, true);
        construirUi(cliente, fecha, filasDetalle, subtotal, isv, total);
    }

    private void construirUi(String cliente, String fecha, List<Object[]> filasDetalle,
            String subtotal, String isv, String total) {
        setLayout(new BorderLayout(0, UiTheme.SPACE_LG));
        JPanel contenido = new JPanel(new BorderLayout(0, UiTheme.SPACE_LG));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(
                UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET));

        JPanel encabezado = new JPanel(new GridLayout(2, 2, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        encabezado.setOpaque(false);

        JLabel etiquetaClienteTitulo = new JLabel("Cliente:");
        JLabel etiquetaFechaTitulo = new JLabel("Fecha:");
        JLabel etiquetaClienteValor = new JLabel(cliente);
        JLabel etiquetaFechaValor = new JLabel(fecha);

        UiStyle.estilizarCuerpo(etiquetaClienteTitulo);
        UiStyle.estilizarCuerpo(etiquetaFechaTitulo);
        UiStyle.estilizarCuerpo(etiquetaClienteValor);
        UiStyle.estilizarCuerpo(etiquetaFechaValor);

        encabezado.add(etiquetaClienteTitulo);
        encabezado.add(etiquetaClienteValor);
        encabezado.add(etiquetaFechaTitulo);
        encabezado.add(etiquetaFechaValor);
        contenido.add(encabezado, BorderLayout.NORTH);

        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"No", "Producto", "Cant.", "Precio", "Subtotal", "ISV", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Object[] fila : filasDetalle) {
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        UiStyle.estilizarTabla(tabla);
        JScrollPane scroll = new JScrollPane(tabla);
        UiStyle.estilizarScrollTabla(scroll);
        contenido.add(scroll, BorderLayout.CENTER);

        JPanel totales = new JPanel(new GridLayout(3, 2, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        totales.setOpaque(false);
        JLabel etiquetaSubtotal = new JLabel("Subtotal:");
        JLabel etiquetaIsv = new JLabel("ISV:");
        JLabel etiquetaTotal = new JLabel("Total a pagar:");
        JLabel etiquetaSubtotalValor = new JLabel(subtotal);
        JLabel etiquetaIsvValor = new JLabel(isv);
        JLabel etiquetaTotalValor = new JLabel(total);

        UiStyle.estilizarCuerpo(etiquetaSubtotal);
        UiStyle.estilizarCuerpo(etiquetaIsv);
        UiStyle.estilizarTotal(etiquetaTotal);
        UiStyle.estilizarCuerpo(etiquetaSubtotalValor);
        UiStyle.estilizarCuerpo(etiquetaIsvValor);
        UiStyle.estilizarTotal(etiquetaTotalValor);

        totales.add(etiquetaSubtotal);
        totales.add(etiquetaSubtotalValor);
        totales.add(etiquetaIsv);
        totales.add(etiquetaIsvValor);
        totales.add(etiquetaTotal);
        totales.add(etiquetaTotalValor);
        contenido.add(totales, BorderLayout.SOUTH);

        add(contenido, BorderLayout.CENTER);

        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.setOpaque(false);
        JButton botonCerrar = new JButton("Cerrar");
        UiStyle.estilizarBoton(botonCerrar, UiStyle.TipoBoton.SECUNDARIO);
        botonCerrar.addActionListener(e -> dispose());
        pie.add(botonCerrar);
        add(pie, BorderLayout.SOUTH);

        setSize(720, 520);
        setLocationRelativeTo(getOwner());
    }
}
