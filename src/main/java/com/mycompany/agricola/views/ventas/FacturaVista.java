package com.mycompany.agricola.views.ventas;

import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import com.mycompany.agricola.controllers.ventas.FacturaController;

public class FacturaVista extends javax.swing.JPanel {

    private final FacturaController controller = new FacturaController();

    public FacturaVista() {
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        btnReporte.addActionListener(e -> generarReporte());
    }

    private void generarReporte() {
        try {
            byte[] pdf = controller.generarReporteInsumosCriticos();
            String ruta = "reporte_insumos_criticos.pdf";
            try (FileOutputStream fos = new FileOutputStream(ruta)) {
                fos.write(pdf);
            }
            JOptionPane.showMessageDialog(this, "PDF generado: " + ruta);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnReporte = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setText("Facturas");

        btnReporte.setText("Generar Reporte Insumos Criticos (PDF)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(btnReporte))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(btnReporte)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
