package com.mycompany.agricola.controllers.util;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public final class TablaCrudHelper {

    private TablaCrudHelper() {
    }

    public static DefaultTableModel crearModeloNoEditable(String[] columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public static void limpiarYLLenar(DefaultTableModel modelo, List<Object[]> filas) {
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
    }
}
