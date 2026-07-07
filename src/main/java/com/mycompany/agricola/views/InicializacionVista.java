package com.mycompany.agricola.views;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mycompany.agricola.controllers.InicializacionController;
import com.mycompany.agricola.views.util.UiUtil;

public class InicializacionVista extends javax.swing.JPanel {

    private final InicializacionController controller = new InicializacionController();
    private final JFrame frame;

    public InicializacionVista(JFrame frame) {
        this.frame = frame;
        initComponents();
        inicializarLogica();
    }

    private void inicializarLogica() {
        btnBlanco.addActionListener(e -> ejecutarInicializacion(true));
        btnPrueba.addActionListener(e -> ejecutarInicializacion(false));
    }

    private void ejecutarInicializacion(boolean datosEnBlanco) {
        setBotonesHabilitados(false);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (datosEnBlanco) {
                    controller.iniciarConDatosEnBlanco();
                } else {
                    controller.iniciarConDatosDePrueba();
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    UiUtil.mostrarVistaEnFrame(frame, new LoginVista(), "Distribuidora Agricola - Login",
                            new Dimension(450, 200));
                } catch (Exception ex) {
                    setBotonesHabilitados(true);
                    JOptionPane.showMessageDialog(InicializacionVista.this,
                            "Error al inicializar la base de datos:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void setBotonesHabilitados(boolean habilitado) {
        btnBlanco.setEnabled(habilitado);
        btnPrueba.setEnabled(habilitado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        btnBlanco = new javax.swing.JButton();
        btnPrueba = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Distribuidora Agricola");

        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("Bienvenido. Configure la base de datos para comenzar.");

        btnBlanco.setText("Iniciar con datos en blanco");

        btnPrueba.setText("Iniciar con datos de prueba");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBlanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(lblMensaje)
                .addGap(18, 18, 18)
                .addComponent(btnBlanco)
                .addGap(12, 12, 12)
                .addComponent(btnPrueba)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlanco;
    private javax.swing.JButton btnPrueba;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
