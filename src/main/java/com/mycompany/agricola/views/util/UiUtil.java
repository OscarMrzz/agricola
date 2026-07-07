package com.mycompany.agricola.views.util;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public final class UiUtil {

    private UiUtil() {
    }

    public static void abrirFrame(JPanel panel, String titulo) {
        abrirFrame(panel, titulo, 960, 640);
    }

    public static void abrirFrame(JPanel panel, String titulo, int ancho, int alto) {
        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setSize(ancho, alto);
        frame.setMinimumSize(new Dimension(ancho, alto));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void abrirFrameFormulario(JPanel panel, String titulo) {
        abrirFrame(panel, titulo, 520, 420);
    }

    public static void mostrarVistaEnFrame(JFrame frame, JPanel panel, String titulo, Dimension tamano) {
        frame.getContentPane().removeAll();
        frame.setTitle(titulo);
        frame.add(panel);
        frame.setSize(tamano);
        frame.setMinimumSize(tamano);
        frame.revalidate();
        frame.repaint();
        frame.setLocationRelativeTo(null);
    }

    public static int obtenerFilaSeleccionada(JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(tabla, "Seleccione un registro", "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        return fila;
    }

    public static boolean confirmarEliminar(Component parent) {
        int respuesta = JOptionPane.showConfirmDialog(parent,
                "Desea eliminar el registro seleccionado?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    public static int obtenerIdSeleccionado(JTable tabla, int columnIndex) {
        int fila = obtenerFilaSeleccionada(tabla);
        if (fila < 0) {
            return -1;
        }
        Object valor = tabla.getModel().getValueAt(fila, columnIndex);
        if (valor instanceof Number) {
            return ((Number) valor).intValue();
        }
        try {
            return Integer.parseInt(valor.toString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
