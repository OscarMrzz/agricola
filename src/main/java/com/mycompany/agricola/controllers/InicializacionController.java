package com.mycompany.agricola.controllers;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mycompany.agricola.services.InicializacionService;
import com.mycompany.agricola.views.InicializacionVista;
import com.mycompany.agricola.views.util.UiUtil;

public class InicializacionController {

    private final InicializacionService service = new InicializacionService();
    private final LoginController loginController = new LoginController();

    public InicializacionVista crearVista(JFrame frame) {
        InicializacionVista vista = new InicializacionVista();
        cargarFuncionalidades(vista, frame);
        return vista;
    }

    private void cargarFuncionalidades(InicializacionVista vista, JFrame frame) {
        vista.botonDatosBlanco.addActionListener(e -> inicializarConDatosEnBlanco(vista, frame));
        vista.botonDatosPrueba.addActionListener(e -> inicializarConDatosDePrueba(vista, frame));
    }

    public boolean baseDeDatosInicializada() {
        return service.baseDeDatosInicializada();
    }

    public void iniciarConDatosEnBlanco() throws Exception {
        service.iniciarConDatosEnBlanco();
    }

    public void iniciarConDatosDePrueba() throws Exception {
        service.iniciarConDatosDePrueba();
    }

    private void inicializarConDatosEnBlanco(InicializacionVista vista, JFrame frame) {
        ejecutarInicializacion(vista, frame, true);
    }

    private void inicializarConDatosDePrueba(InicializacionVista vista, JFrame frame) {
        ejecutarInicializacion(vista, frame, false);
    }

    private void ejecutarInicializacion(InicializacionVista vista, JFrame frame, boolean datosEnBlanco) {
        setBotonesHabilitados(vista, false);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (datosEnBlanco) {
                    iniciarConDatosEnBlanco();
                } else {
                    iniciarConDatosDePrueba();
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    UiUtil.mostrarVistaEnFrame(frame, loginController.crearVista(),
                            "Distribuidora Agricola - Login", new Dimension(480, 300));
                } catch (Exception ex) {
                    setBotonesHabilitados(vista, true);
                    JOptionPane.showMessageDialog(vista,
                            "Error al inicializar la base de datos:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void setBotonesHabilitados(InicializacionVista vista, boolean habilitado) {
        vista.botonDatosBlanco.setEnabled(habilitado);
        vista.botonDatosPrueba.setEnabled(habilitado);
    }
}
