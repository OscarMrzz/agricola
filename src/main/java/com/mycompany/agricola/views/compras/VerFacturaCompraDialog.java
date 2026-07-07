package com.mycompany.agricola.views.compras;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class VerFacturaCompraDialog extends JDialog {

    public JLabel etiquetaFechaValor;
    public JLabel etiquetaIsv;
    public JLabel etiquetaMetodoValor;
    public JLabel etiquetaSubtotal;
    public JLabel etiquetaTotal;
    public JTable tablaDetalle;
    public JButton botonCerrar;

    public VerFacturaCompraDialog(Frame owner, String noFactura) {
        super(owner, "Factura " + noFactura, true);
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        UiStyle.estilizarCuerpo(etiquetaMetodoTitulo);
        UiStyle.estilizarCuerpo(etiquetaFechaTitulo);
        UiStyle.estilizarCuerpo(etiquetaMetodoValor);
        UiStyle.estilizarCuerpo(etiquetaFechaValor);
        UiStyle.estilizarTabla(tablaDetalle);
        UiStyle.estilizarScrollTabla(scrollDetalle);
        UiStyle.estilizarCuerpo(etiquetaSubtotalEtiqueta);
        UiStyle.estilizarCuerpo(etiquetaIsvEtiqueta);
        UiStyle.estilizarTotal(etiquetaTotalEtiqueta);
        UiStyle.estilizarCuerpo(etiquetaSubtotal);
        UiStyle.estilizarCuerpo(etiquetaIsv);
        UiStyle.estilizarTotal(etiquetaTotal);
        UiStyle.estilizarBoton(botonCerrar, UiStyle.TipoBoton.SECUNDARIO);
        setSize(720, 520);
        setLocationRelativeTo(getOwner());
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, UiTheme.SPACE_LG));

        JPanel contenido = new JPanel(new BorderLayout(0, UiTheme.SPACE_LG));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(
                UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET));

        JPanel encabezado = new JPanel(new GridLayout(2, 2, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        encabezado.setOpaque(false);

        etiquetaMetodoTitulo = new JLabel("Metodo de pago:");
        etiquetaFechaTitulo = new JLabel("Fecha:");
        etiquetaMetodoValor = new JLabel("-");
        etiquetaFechaValor = new JLabel("-");

        encabezado.add(etiquetaMetodoTitulo);
        encabezado.add(etiquetaMetodoValor);
        encabezado.add(etiquetaFechaTitulo);
        encabezado.add(etiquetaFechaValor);
        contenido.add(encabezado, BorderLayout.NORTH);

        tablaDetalle = new JTable();
        scrollDetalle = new JScrollPane(tablaDetalle);
        contenido.add(scrollDetalle, BorderLayout.CENTER);

        JPanel totales = new JPanel(new GridLayout(3, 2, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        totales.setOpaque(false);
        etiquetaSubtotalEtiqueta = new JLabel("Subtotal:");
        etiquetaIsvEtiqueta = new JLabel("ISV:");
        etiquetaTotalEtiqueta = new JLabel("Total a pagar:");
        etiquetaSubtotal = new JLabel("0.00");
        etiquetaIsv = new JLabel("0.00");
        etiquetaTotal = new JLabel("0.00");

        totales.add(etiquetaSubtotalEtiqueta);
        totales.add(etiquetaSubtotal);
        totales.add(etiquetaIsvEtiqueta);
        totales.add(etiquetaIsv);
        totales.add(etiquetaTotalEtiqueta);
        totales.add(etiquetaTotal);
        contenido.add(totales, BorderLayout.SOUTH);

        add(contenido, BorderLayout.CENTER);

        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.setOpaque(false);
        botonCerrar = new JButton("Cerrar");
        pie.add(botonCerrar);
        add(pie, BorderLayout.SOUTH);
    }

    private JLabel etiquetaMetodoTitulo;
    private JLabel etiquetaFechaTitulo;
    private JLabel etiquetaSubtotalEtiqueta;
    private JLabel etiquetaIsvEtiqueta;
    private JLabel etiquetaTotalEtiqueta;
    private JScrollPane scrollDetalle;
}
