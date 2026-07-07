package com.mycompany.agricola.controllers;

import com.mycompany.agricola.services.InicializacionService;

public class InicializacionController {

    private final InicializacionService service = new InicializacionService();

    public boolean baseDeDatosInicializada() {
        return service.baseDeDatosInicializada();
    }

    public void iniciarConDatosEnBlanco() throws Exception {
        service.iniciarConDatosEnBlanco();
    }

    public void iniciarConDatosDePrueba() throws Exception {
        service.iniciarConDatosDePrueba();
    }
}
