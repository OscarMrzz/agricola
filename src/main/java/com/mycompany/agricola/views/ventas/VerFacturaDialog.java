package com.mycompany.agricola.views.ventas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.ventas.VentasController;
import com.mycompany.agricola.model.entity.FacturaVentaEntity;
import com.mycompany.agricola.model.entity.VentasDetalleEntity;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;

public class VerFacturaDialog extends JDialog {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public VerFacturaDialog(Frame owner, FacturaVentaEntity factura, VentasController controller) {
        super(owner, "Factura " + factura.getNoFactura(), true);
        construirUi(factura, controller.listarDetalleFactura(factura.getNoFactura()));
    }

    private void construirUi(FacturaVentaEntity factura, List<VentasDetalleEntity> lineas) {
        setLayout(new BorderLayout(0, UiTheme.SPACE_LG));
        JPanel contenido = new JPanel(new BorderLayout(0, UiTheme.SPACE_LG));
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(
                UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET));

        JPanel encabezado = new JPanel(new GridLayout(2, 2, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        encabezado.setOpaque(false);

        JLabel lblClienteTitulo = new JLabel("Cliente:");
        JLabel lblFechaTitulo = new JLabel("Fecha:");
        JLabel lblClienteValor = new JLabel(factura.getCliente() != null ? factura.getCliente() : "-");
        String fechaTexto = factura.getFechaVenta() != null
                ? factura.getFechaVenta().format(FECHA_FORMATO) : "-";
        JLabel lblFechaValor = new JLabel(fechaTexto);

        UiStyle.estilizarCuerpo(lblClienteTitulo);
        UiStyle.estilizarCuerpo(lblFechaTitulo);
        UiStyle.estilizarCuerpo(lblClienteValor);
        UiStyle.estilizarCuerpo(lblFechaValor);

        encabezado.add(lblClienteTitulo);
        encabezado.add(lblClienteValor);
        encabezado.add(lblFechaTitulo);
        encabezado.add(lblFechaValor);
        contenido.add(encabezado, BorderLayout.NORTH);

        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"No", "Producto", "Cant.", "Precio", "Subtotal", "ISV", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        int no = 1;
        for (VentasDetalleEntity linea : lineas) {
            modelo.addRow(new Object[]{
                no++,
                linea.getNombreProducto(),
                linea.getCantidadProducto(),
                linea.getPrecioUnitario(),
                linea.getSubtotal(),
                linea.getImpuesto(),
                linea.getTotalAPagar()
            });
        }

        JTable tabla = new JTable(modelo);
        UiStyle.estilizarTabla(tabla);
        JScrollPane scroll = new JScrollPane(tabla);
        UiStyle.estilizarScrollTabla(scroll);
        contenido.add(scroll, BorderLayout.CENTER);

        JPanel totales = new JPanel(new GridLayout(3, 2, UiTheme.SPACE_MD, UiTheme.SPACE_SM));
        totales.setOpaque(false);
        JLabel lblSubtotalEtiqueta = new JLabel("Subtotal:");
        JLabel lblIsvEtiqueta = new JLabel("ISV:");
        JLabel lblTotalEtiqueta = new JLabel("Total a pagar:");
        JLabel lblSubtotal = new JLabel(formatearMonto(factura.getSubtotal()));
        JLabel lblIsv = new JLabel(formatearMonto(factura.getImpuesto()));
        JLabel lblTotal = new JLabel(formatearMonto(factura.getTotal()));

        UiStyle.estilizarCuerpo(lblSubtotalEtiqueta);
        UiStyle.estilizarCuerpo(lblIsvEtiqueta);
        UiStyle.estilizarTotal(lblTotalEtiqueta);
        UiStyle.estilizarCuerpo(lblSubtotal);
        UiStyle.estilizarCuerpo(lblIsv);
        UiStyle.estilizarTotal(lblTotal);

        totales.add(lblSubtotalEtiqueta);
        totales.add(lblSubtotal);
        totales.add(lblIsvEtiqueta);
        totales.add(lblIsv);
        totales.add(lblTotalEtiqueta);
        totales.add(lblTotal);
        contenido.add(totales, BorderLayout.SOUTH);

        add(contenido, BorderLayout.CENTER);

        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pie.setOpaque(false);
        JButton btnCerrar = new JButton("Cerrar");
        UiStyle.estilizarBoton(btnCerrar, UiStyle.TipoBoton.SECUNDARIO);
        btnCerrar.addActionListener(e -> dispose());
        pie.add(btnCerrar);
        add(pie, BorderLayout.SOUTH);

        setSize(720, 520);
        setLocationRelativeTo(getOwner());
    }

    private String formatearMonto(BigDecimal monto) {
        return monto != null ? monto.toPlainString() : "0.00";
    }
}
