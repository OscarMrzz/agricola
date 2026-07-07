package com.mycompany.agricola.controllers.ventas;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mycompany.agricola.controllers.util.TablaCrudHelper;
import com.mycompany.agricola.model.entity.CreditosClientesDetalleEntity;
import com.mycompany.agricola.services.CreditosClientesService;
import com.mycompany.agricola.services.NavegacionService;
import com.mycompany.agricola.views.ventas.ClientesVentasVista;

public class ClientesVentasController {

    private static final String[] COLUMNAS = {
        "No", "Nombre", "Apellido", "Credito max", "Credito actual", "Diferencia"
    };

    private final CreditosClientesService creditosService = new CreditosClientesService();
    private final NavegacionService navegacion = new NavegacionService();

    public void abrir(Component parent) {
        ClientesVentasVista vista = new ClientesVentasVista();
        inicializarVista(vista);
        cargarFuncionalidades(vista);
        navegacion.abrirVistaSiPermitida("ClientesVentasVista", vista, parent);
    }

    public List<CreditosClientesDetalleEntity> listarCreditos() {
        return creditosService.obtenerTodos();
    }

    private void inicializarVista(ClientesVentasVista vista) {
        vista.tablaClientes.setModel(TablaCrudHelper.crearModeloNoEditable(COLUMNAS));
        refrescarTabla(vista);
    }

    private void cargarFuncionalidades(ClientesVentasVista vista) {
        vista.botonRefrescar.addActionListener(e -> refrescarTabla(vista));
        vista.botonVolver.addActionListener(e -> volver(vista));
    }

    private void volver(ClientesVentasVista vista) {
        SwingUtilities.getWindowAncestor(vista).dispose();
    }

    private void refrescarTabla(ClientesVentasVista vista) {
        List<Object[]> filas = new ArrayList<>();
        int no = 1;
        for (CreditosClientesDetalleEntity credito : listarCreditos()) {
            filas.add(new Object[]{
                no++,
                credito.getNombreCliente(),
                credito.getApellidoCliente(),
                credito.getCreditoMaximo(),
                credito.getCreditoActual(),
                credito.getDiferencia()
            });
        }
        TablaCrudHelper.limpiarYLLenar((DefaultTableModel) vista.tablaClientes.getModel(), filas);
    }
}
