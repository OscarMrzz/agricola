package com.mycompany.agricola.model.dao.resultados;

public class ResultadoPersistencia {

    private final boolean exito;
    private final String mensaje;
    private final Exception error;

    private ResultadoPersistencia(boolean exito, String mensaje, Exception error) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.error = error;
    }

    public static ResultadoPersistencia exito() {
        return new ResultadoPersistencia(true, "Operacion exitosa", null);
    }

    public static ResultadoPersistencia error(Exception e, String accion) {
        return new ResultadoPersistencia(false, "Error al " + accion, e);
    }

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Exception getError() {
        return error;
    }
}
