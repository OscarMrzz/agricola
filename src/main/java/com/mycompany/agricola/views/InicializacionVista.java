package com.mycompany.agricola.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import com.mycompany.agricola.controllers.InicializacionController;
import com.mycompany.agricola.views.util.FormPanel;
import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;
import com.mycompany.agricola.views.util.UiTheme;
import com.mycompany.agricola.views.util.UiUtil;

public class InicializacionVista extends javax.swing.JPanel {

    private final InicializacionController controller = new InicializacionController();
    private final JFrame frame;

    public InicializacionVista(JFrame frame) {
        this.frame = frame;
        initComponents();
        aplicarEstilos();
        inicializarLogica();
    }

    private void aplicarEstilos() {
        removeAll();
        setLayout(new BorderLayout());
        UiStyle.aplicarDialogo(this);

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        UiStyle.estilizarTitulo(lblTitulo);
        UiStyle.estilizarCuerpo(lblMensaje);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        UiStyle.estilizarBoton(btnBlanco, UiStyle.TipoBoton.PRIMARIO);
        UiStyle.estilizarBoton(btnPrueba, UiStyle.TipoBoton.SECUNDARIO);
        UiStyle.conIcono(btnBlanco, UiIcons.INIT);
        UiStyle.conIcono(btnPrueba, UiIcons.INIT);

        FormPanel panelFormulario = new FormPanel("Configuracion inicial");
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_LG, 0);

        gbc.gridy = 0;
        panelFormulario.add(lblTitulo, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_XXL, 0);
        panelFormulario.add(lblMensaje, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, UiTheme.SPACE_MD, 0);
        panelFormulario.add(btnBlanco, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        panelFormulario.add(btnPrueba, gbc);

        add(panelFormulario, BorderLayout.CENTER);
        revalidate();
        repaint();
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
                            new Dimension(480, 300));
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

        lblTitulo.setText("Distribuidora Agricola");
        lblMensaje.setText("Bienvenido. Configure la base de datos para comenzar.");
        btnBlanco.setText("Iniciar con datos en blanco");
        btnPrueba.setText("Iniciar con datos de prueba");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlanco;
    private javax.swing.JButton btnPrueba;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
