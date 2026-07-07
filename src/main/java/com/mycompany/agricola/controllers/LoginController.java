package com.mycompany.agricola.controllers;

import com.mycompany.agricola.model.entity.UsuarioEntity;
import com.mycompany.agricola.services.AuthService;

public class LoginController {

    private final AuthService authService = new AuthService();

    public UsuarioEntity iniciarSesion(String usuario, String password) {
        if (usuario == null || usuario.isBlank() || password == null || password.isBlank()) {
            return null;
        }
        return authService.login(usuario.trim(), password);
    }

    public void cerrarSesion() {
        authService.logout();
    }
}
