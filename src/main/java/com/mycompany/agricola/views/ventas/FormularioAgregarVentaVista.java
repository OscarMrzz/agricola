package com.mycompany.agricola.views.ventas;

import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.ventas.ClientesVentasController;
import com.mycompany.agricola.controllers.ventas.FacturaController;
import com.mycompany.agricola.controllers.ventas.FormularioAgregarVentaController;
import com.mycompany.agricola.model.entity.CarritoVentaEntity;
import com.mycompany.agricola.model.entity.ClienteEntity;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;
import com.mycompany.agricola.model.entity.ProductoEntity;
import com.mycompany.agricola.services.AuthService;
import com.mycompany.agricola.services.CreditoExcedidoException;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class FormularioAgregarVentaVista extends javax.swing.JPanel {

    private final FormularioAgregarVentaController controller = new FormularioAgregarVentaController();
    private final ClientesVentasController clientesController = new ClientesVentasController();
    private final FacturaController facturaController = new FacturaController();
    private DefaultTableModel modeloCarrito;
    private String ultimaFacturaGuardada;
    private javax.swing.JButton btnEliminarFila;

    public FormularioAgregarVentaVista() {
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        UiStyle.aplicarPagina(this);
        UiStyle.estilizarTitulo(lblTituloFormulario);
        UiStyle.estilizarSeccion(lblTituloCarrito);
        UiStyle.estilizarFormPanel(panelFormulario, "Datos");
        UiStyle.estilizarFormPanel(panelCarrito, "Carrito");
        UiStyle.estilizarTabla(tablaCarrito);
        UiStyle.estilizarScrollTabla(scrollCarrito);
        UiStyle.estilizarCuerpo(lblNoFactura);
        UiStyle.estilizarCuerpo(lblNoFacturaValor);
        UiStyle.estilizarCuerpo(lblProducto);
        UiStyle.estilizarInput(cmbProducto);
        UiStyle.estilizarCuerpo(lblCliente);
        UiStyle.estilizarInput(cmbCliente);
        UiStyle.estilizarCuerpo(lblCantidad);
        UiStyle.estilizarInput(txtCantidad);
        UiStyle.estilizarCuerpo(lblPrecioUnitario);
        UiStyle.estilizarInput(txtPrecioUnitario);
        UiStyle.estilizarCuerpo(lblMetodoPago);
        UiStyle.estilizarInput(cmbMetodoPago);
        UiStyle.estilizarCuerpo(lblSubtotalEtiqueta);
        UiStyle.estilizarCuerpo(lblSubtotalLinea);
        UiStyle.estilizarCuerpo(lblIsvEtiqueta);
        UiStyle.estilizarCuerpo(lblIsvLinea);
        UiStyle.estilizarCuerpo(lblTotalEtiqueta);
        UiStyle.estilizarTotal(lblTotalLinea);
        UiStyle.estilizarCuerpo(lblSubtotalFacturaEtiqueta);
        UiStyle.estilizarCuerpo(lblSubtotalFactura);
        UiStyle.estilizarCuerpo(lblIsvFacturaEtiqueta);
        UiStyle.estilizarCuerpo(lblIsvFactura);
        UiStyle.estilizarCuerpo(lblTotalFacturaEtiqueta);
        UiStyle.estilizarTotal(lblTotalFactura);
        UiStyle.estilizarBotonNav(btnAgregarCarrito);
        UiStyle.estilizarBoton(btnGuardarVenta, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBotonNav(btnGenerarPdf);
        UiStyle.estilizarBoton(btnVolver, UiStyle.TipoBoton.SECUNDARIO);
        btnEliminarFila = new javax.swing.JButton("Eliminar fila");
        UiStyle.estilizarBoton(btnEliminarFila, UiStyle.TipoBoton.PELIGRO);
        UiStyle.conIcono(btnAgregarCarrito, UiIcons.CART);
        UiStyle.conIcono(btnGuardarVenta, UiIcons.SAVE);
        UiStyle.conIcono(btnGenerarPdf, UiIcons.PDF);
        UiStyle.conIcono(btnVolver, UiIcons.EXIT);
        UiStyle.conIcono(btnEliminarFila, UiIcons.DELETE);
        reorganizarLayout();
    }

    private void reorganizarLayout() {
        panelFormulario.removeAll();
        panelCarrito.removeAll();
        this.removeAll();

        panelFormulario.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(0, 0, com.mycompany.agricola.views.util.UiTheme.SPACE_LG, 0);
        panelFormulario.add(lblTituloFormulario, gbc);

        agregarFilaFormulario(panelFormulario, gbc, 1, lblNoFactura, lblNoFacturaValor);
        agregarFilaFormulario(panelFormulario, gbc, 2, lblProducto, cmbProducto);
        agregarFilaFormulario(panelFormulario, gbc, 3, lblCliente, cmbCliente);
        agregarFilaFormulario(panelFormulario, gbc, 4, lblCantidad, txtCantidad);
        agregarFilaFormulario(panelFormulario, gbc, 5, lblPrecioUnitario, txtPrecioUnitario);
        agregarFilaFormulario(panelFormulario, gbc, 6, lblMetodoPago, cmbMetodoPago);
        agregarFilaFormulario(panelFormulario, gbc, 7, lblSubtotalEtiqueta, lblSubtotalLinea);
        agregarFilaFormulario(panelFormulario, gbc, 8, lblIsvEtiqueta, lblIsvLinea);
        agregarFilaFormulario(panelFormulario, gbc, 9, lblTotalEtiqueta, lblTotalLinea);

        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(com.mycompany.agricola.views.util.UiTheme.SPACE_LG, 0,
                com.mycompany.agricola.views.util.UiTheme.SPACE_MD, 0);
        panelFormulario.add(btnAgregarCarrito, gbc);
        gbc.gridy = 11;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        panelFormulario.add(btnVolver, gbc);

        panelCarrito.setLayout(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD));
        JPanel contenidoCarrito = new javax.swing.JPanel(new java.awt.BorderLayout(0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD));
        contenidoCarrito.setOpaque(false);
        contenidoCarrito.add(lblTituloCarrito, java.awt.BorderLayout.NORTH);
        contenidoCarrito.add(scrollCarrito, java.awt.BorderLayout.CENTER);

        JPanel totales = new javax.swing.JPanel(new java.awt.GridLayout(3, 2, com.mycompany.agricola.views.util.UiTheme.SPACE_MD,
                com.mycompany.agricola.views.util.UiTheme.SPACE_SM));
        totales.setOpaque(false);
        totales.add(lblSubtotalFacturaEtiqueta);
        totales.add(lblSubtotalFactura);
        totales.add(lblIsvFacturaEtiqueta);
        totales.add(lblIsvFactura);
        totales.add(lblTotalFacturaEtiqueta);
        totales.add(lblTotalFactura);
        contenidoCarrito.add(totales, java.awt.BorderLayout.SOUTH);
        panelCarrito.add(contenidoCarrito, java.awt.BorderLayout.CENTER);

        JPanel barraCarrito = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
                com.mycompany.agricola.views.util.UiTheme.SPACE_SM, 0));
        barraCarrito.setOpaque(false);
        barraCarrito.add(btnEliminarFila);
        barraCarrito.add(btnGuardarVenta);
        barraCarrito.add(btnGenerarPdf);
        panelCarrito.add(barraCarrito, java.awt.BorderLayout.SOUTH);

        setLayout(new java.awt.GridLayout(1, 2, com.mycompany.agricola.views.util.UiTheme.SPACE_LG, 0));
        add(panelFormulario);
        add(panelCarrito);
        revalidate();
        repaint();
    }

    private void agregarFilaFormulario(javax.swing.JPanel panel, java.awt.GridBagConstraints gbc, int fila,
            javax.swing.JLabel etiqueta, java.awt.Component campo) {
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        gbc.insets = new java.awt.Insets(0, 0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD,
                com.mycompany.agricola.views.util.UiTheme.SPACE_MD);
        panel.add(etiqueta, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 0, com.mycompany.agricola.views.util.UiTheme.SPACE_MD, 0);
        panel.add(campo, gbc);
        gbc.gridx = 0;
    }

    private void inicializarLogica() {
        lblNoFacturaValor.setText(controller.getNoFactura());
        modeloCarrito = new DefaultTableModel(
                new String[]{"No", "Producto", "Cantidad", "Precio", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCarrito.setModel(modeloCarrito);
        cargarCombos();
        cmbProducto.addActionListener(e -> actualizarCalculosLinea());
        txtCantidad.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { actualizarCalculosLinea(); }
            @Override
            public void removeUpdate(DocumentEvent e) { actualizarCalculosLinea(); }
            @Override
            public void changedUpdate(DocumentEvent e) { actualizarCalculosLinea(); }
        });
        btnAgregarCarrito.addActionListener(e -> agregarLinea());
        btnEliminarFila.addActionListener(e -> eliminarFilaSeleccionada());
        btnGuardarVenta.addActionListener(e -> guardarVenta());
        btnGenerarPdf.addActionListener(e -> generarPdf());
        btnVolver.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
        actualizarCalculosLinea();
    }

    private void cargarCombos() {
        cmbProducto.removeAllItems();
        for (ProductoEntity p : controller.listarProductosAptos()) {
            cmbProducto.addItem(p);
        }
        cmbCliente.removeAllItems();
        for (CreditosClientesDetalleEntity c : clientesController.listarCreditos()) {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setIdCliente(c.getIdCliente());
            cliente.setNombreCliente(c.getNombreCliente());
            cliente.setApellidoCliente(c.getApellidoCliente());
            cliente.setLimiteCredito(c.getCreditoMaximo());
            cmbCliente.addItem(cliente);
        }
    }

    private int leerCantidad() {
        try {
            return Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void actualizarCalculosLinea() {
        ProductoEntity producto = (ProductoEntity) cmbProducto.getSelectedItem();
        var totales = controller.calcularTotalesLinea(producto, leerCantidad());
        txtPrecioUnitario.setText(totales.getPrecioUnitario().toPlainString());
        lblSubtotalLinea.setText(totales.getSubtotal().toPlainString());
        lblIsvLinea.setText(totales.getIsv().toPlainString());
        lblTotalLinea.setText(totales.getTotal().toPlainString());
    }

    private void agregarLinea() {
        try {
            ProductoEntity producto = (ProductoEntity) cmbProducto.getSelectedItem();
            ClienteEntity cliente = (ClienteEntity) cmbCliente.getSelectedItem();
            int cantidad = leerCantidad();
            String metodo = (String) cmbMetodoPago.getSelectedItem();
            controller.agregarLinea(producto, cantidad, cliente, metodo);
            actualizarTablaCarrito();
            txtCantidad.setText("");
            actualizarCalculosLinea();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFilaSeleccionada() {
        int fila = tablaCarrito.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila del carrito", "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        controller.eliminarLinea(fila);
        actualizarTablaCarrito();
    }

    private void guardarVenta() {
        try {
            ClienteEntity cliente = (ClienteEntity) cmbCliente.getSelectedItem();
            String metodo = (String) cmbMetodoPago.getSelectedItem();
            var usuario = AuthService.getUsuarioActual();
            int idVendedor = usuario != null ? usuario.getIdUser() : 1;
            ultimaFacturaGuardada = controller.getNoFactura();
            var resultado = controller.guardarVenta(cliente, idVendedor, metodo);
            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(this, "Venta guardada: " + ultimaFacturaGuardada);
                lblNoFacturaValor.setText(controller.getNoFactura());
                actualizarTablaCarrito();
            } else {
                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (CreditoExcedidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Credito excedido", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void generarPdf() {
        if (ultimaFacturaGuardada == null) {
            JOptionPane.showMessageDialog(this, "Guarde una venta primero");
            return;
        }
        try {
            byte[] pdf = facturaController.generarFacturaVenta(ultimaFacturaGuardada);
            String ruta = "factura_" + ultimaFacturaGuardada + ".pdf";
            try (FileOutputStream fos = new FileOutputStream(ruta)) {
                fos.write(pdf);
            }
            JOptionPane.showMessageDialog(this, "PDF generado: " + ruta);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error reporte", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTablaCarrito() {
        modeloCarrito.setRowCount(0);
        int no = 1;
        for (CarritoVentaEntity linea : controller.getCarrito()) {
            modeloCarrito.addRow(new Object[]{
                no++,
                linea.getNombreProducto(),
                linea.getCantidadProducto(),
                linea.getPrecioUnitario(),
                linea.getTotalAPagar()
            });
        }
        lblSubtotalFactura.setText(controller.calcularSubtotalFactura().toPlainString());
        lblIsvFactura.setText(controller.calcularIsvFactura().toPlainString());
        lblTotalFactura.setText(controller.calcularTotalFactura().toPlainString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panelFormulario = new javax.swing.JPanel();
        lblTituloFormulario = new javax.swing.JLabel();
        lblNoFactura = new javax.swing.JLabel();
        lblNoFacturaValor = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblPrecioUnitario = new javax.swing.JLabel();
        txtPrecioUnitario = new javax.swing.JTextField();
        lblMetodoPago = new javax.swing.JLabel();
        cmbMetodoPago = new javax.swing.JComboBox<>(new String[]{"contado", "credito"});
        lblSubtotalEtiqueta = new javax.swing.JLabel();
        lblSubtotalLinea = new javax.swing.JLabel();
        lblIsvEtiqueta = new javax.swing.JLabel();
        lblIsvLinea = new javax.swing.JLabel();
        lblTotalEtiqueta = new javax.swing.JLabel();
        lblTotalLinea = new javax.swing.JLabel();
        btnAgregarCarrito = new javax.swing.JButton();
        btnGuardarVenta = new javax.swing.JButton();
        btnGenerarPdf = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        panelCarrito = new javax.swing.JPanel();
        lblTituloCarrito = new javax.swing.JLabel();
        scrollCarrito = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        lblSubtotalFacturaEtiqueta = new javax.swing.JLabel();
        lblSubtotalFactura = new javax.swing.JLabel();
        lblIsvFacturaEtiqueta = new javax.swing.JLabel();
        lblIsvFactura = new javax.swing.JLabel();
        lblTotalFacturaEtiqueta = new javax.swing.JLabel();
        lblTotalFactura = new javax.swing.JLabel();

        lblTituloFormulario.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTituloFormulario.setText("Nueva venta");
        lblNoFactura.setText("No. factura:");
        lblNoFacturaValor.setText("-");
        lblProducto.setText("Producto:");
        lblCliente.setText("Cliente:");
        lblCantidad.setText("Cantidad:");
        lblPrecioUnitario.setText("Precio unitario:");
        txtPrecioUnitario.setEditable(false);
        lblMetodoPago.setText("Metodo pago:");
        lblSubtotalEtiqueta.setText("Subtotal linea:");
        lblSubtotalLinea.setText("0.00");
        lblIsvEtiqueta.setText("ISV 15%:");
        lblIsvLinea.setText("0.00");
        lblTotalEtiqueta.setFont(new java.awt.Font("Arial Black", 1, 12));
        lblTotalEtiqueta.setText("Total linea:");
        lblTotalLinea.setFont(new java.awt.Font("Arial Black", 1, 12));
        lblTotalLinea.setText("0.00");
        btnAgregarCarrito.setText("Agregar al carrito");
        btnGuardarVenta.setText("Guardar venta");
        btnGenerarPdf.setText("Generar PDF");
        btnVolver.setText("Volver");
        lblTituloCarrito.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTituloCarrito.setText("Carrito de productos");
        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{"No", "Producto", "Cantidad", "Precio", "Total"}));
        scrollCarrito.setViewportView(tablaCarrito);
        lblSubtotalFacturaEtiqueta.setText("Subtotal:");
        lblSubtotalFactura.setText("0.00");
        lblIsvFacturaEtiqueta.setText("ISV:");
        lblIsvFactura.setText("0.00");
        lblTotalFacturaEtiqueta.setFont(new java.awt.Font("Arial Black", 1, 12));
        lblTotalFacturaEtiqueta.setText("Total:");
        lblTotalFactura.setFont(new java.awt.Font("Arial Black", 1, 12));
        lblTotalFactura.setText("0.00");

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup().addGap(20, 20, 20)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloFormulario)
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblNoFactura, 110, 110, 110).addGap(18).addComponent(lblNoFacturaValor))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblProducto, 110, 110, 110).addGap(18).addComponent(cmbProducto, 0, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblCliente, 110, 110, 110).addGap(18).addComponent(cmbCliente, 0, 260, Short.MAX_VALUE))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblCantidad, 110, 110, 110).addGap(18).addComponent(txtCantidad, 120, 120, 120))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblPrecioUnitario, 110, 110, 110).addGap(18).addComponent(txtPrecioUnitario, 120, 120, 120))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblMetodoPago, 110, 110, 110).addGap(18).addComponent(cmbMetodoPago, 160, 160, 160))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblSubtotalEtiqueta, 110, 110, 110).addGap(18).addComponent(lblSubtotalLinea))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblIsvEtiqueta, 110, 110, 110).addGap(18).addComponent(lblIsvLinea))
                    .addGroup(panelFormularioLayout.createSequentialGroup().addComponent(lblTotalEtiqueta, 110, 110, 110).addGap(18).addComponent(lblTotalLinea))
                    .addComponent(btnAgregarCarrito).addComponent(btnGuardarVenta).addComponent(btnGenerarPdf).addComponent(btnVolver))
                .addGap(20, 20, 20)));
        panelFormularioLayout.setVerticalGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(lblTituloFormulario).addGap(18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblNoFactura).addComponent(lblNoFacturaValor)).addGap(12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblProducto).addComponent(cmbProducto)).addGap(12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblCliente).addComponent(cmbCliente)).addGap(12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblCantidad).addComponent(txtCantidad)).addGap(12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblPrecioUnitario).addComponent(txtPrecioUnitario)).addGap(12)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblMetodoPago).addComponent(cmbMetodoPago)).addGap(18)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblSubtotalEtiqueta).addComponent(lblSubtotalLinea))
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblIsvEtiqueta).addComponent(lblIsvLinea))
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblTotalEtiqueta).addComponent(lblTotalLinea)).addGap(18)
                .addComponent(btnAgregarCarrito).addComponent(btnGuardarVenta).addComponent(btnGenerarPdf).addComponent(btnVolver).addContainerGap(20, Short.MAX_VALUE)));

        javax.swing.GroupLayout panelCarritoLayout = new javax.swing.GroupLayout(panelCarrito);
        panelCarrito.setLayout(panelCarritoLayout);
        panelCarritoLayout.setHorizontalGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarritoLayout.createSequentialGroup().addGap(20, 20, 20)
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloCarrito).addComponent(scrollCarrito, 520, 520, Short.MAX_VALUE)
                    .addGroup(panelCarritoLayout.createSequentialGroup().addComponent(lblSubtotalFacturaEtiqueta, 80, 80, 80).addGap(18).addComponent(lblSubtotalFactura))
                    .addGroup(panelCarritoLayout.createSequentialGroup().addComponent(lblIsvFacturaEtiqueta, 80, 80, 80).addGap(18).addComponent(lblIsvFactura))
                    .addGroup(panelCarritoLayout.createSequentialGroup().addComponent(lblTotalFacturaEtiqueta, 80, 80, 80).addGap(18).addComponent(lblTotalFactura)))
                .addGap(20, 20, 20)));
        panelCarritoLayout.setVerticalGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarritoLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(lblTituloCarrito).addGap(18)
                .addComponent(scrollCarrito, 420, 420, Short.MAX_VALUE).addGap(18)
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblSubtotalFacturaEtiqueta).addComponent(lblSubtotalFactura))
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblIsvFacturaEtiqueta).addComponent(lblIsvFactura))
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblTotalFacturaEtiqueta).addComponent(lblTotalFactura)).addGap(20, 20, 20)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(panelFormulario).addComponent(panelCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCarrito;
    private javax.swing.JButton btnGenerarPdf;
    private javax.swing.JButton btnGuardarVenta;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<ClienteEntity> cmbCliente;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JComboBox<ProductoEntity> cmbProducto;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblIsvEtiqueta;
    private javax.swing.JLabel lblIsvFactura;
    private javax.swing.JLabel lblIsvFacturaEtiqueta;
    private javax.swing.JLabel lblIsvLinea;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblNoFactura;
    private javax.swing.JLabel lblNoFacturaValor;
    private javax.swing.JLabel lblPrecioUnitario;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblSubtotalEtiqueta;
    private javax.swing.JLabel lblSubtotalFactura;
    private javax.swing.JLabel lblSubtotalFacturaEtiqueta;
    private javax.swing.JLabel lblSubtotalLinea;
    private javax.swing.JLabel lblTituloCarrito;
    private javax.swing.JLabel lblTituloFormulario;
    private javax.swing.JLabel lblTotalEtiqueta;
    private javax.swing.JLabel lblTotalFactura;
    private javax.swing.JLabel lblTotalFacturaEtiqueta;
    private javax.swing.JLabel lblTotalLinea;
    private javax.swing.JPanel panelCarrito;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JScrollPane scrollCarrito;
    private javax.swing.JTable tablaCarrito;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecioUnitario;
    // End of variables declaration//GEN-END:variables
}
