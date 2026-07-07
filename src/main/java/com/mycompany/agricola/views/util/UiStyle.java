package com.mycompany.agricola.views.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public final class UiStyle {

    public enum TipoBoton {
        PRIMARIO, SECUNDARIO, PELIGRO
    }

    private UiStyle() {
    }

    public static void aplicarPagina(JPanel panel) {
        panel.setBorder(new EmptyBorder(
                UiTheme.PAGE_INSET, UiTheme.PAGE_INSET,
                UiTheme.PAGE_INSET, UiTheme.PAGE_INSET));
    }

    public static void aplicarDialogo(JPanel panel) {
        panel.setBorder(new EmptyBorder(
                UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET,
                UiTheme.DIALOG_INSET, UiTheme.DIALOG_INSET));
    }

    public static void estilizarTitulo(JLabel label) {
        label.setFont(UiTheme.fontTitle());
    }

    public static void estilizarSeccion(JLabel label) {
        label.setFont(UiTheme.fontSection());
    }

    public static void estilizarCuerpo(JLabel label) {
        label.setFont(UiTheme.fontBody());
    }

    public static void estilizarTotal(JLabel label) {
        label.setFont(UiTheme.fontTotal());
    }

    public static void estilizarError(JLabel label) {
        label.setFont(UiTheme.fontBody());
        label.setForeground(UiTheme.COLOR_ERROR);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public static void estilizarInput(JTextField field) {
        field.setFont(UiTheme.fontBody());
        field.setPreferredSize(new Dimension(UiTheme.INPUT_WIDTH, UiTheme.INPUT_HEIGHT));
        field.setMinimumSize(new Dimension(UiTheme.INPUT_WIDTH, UiTheme.INPUT_HEIGHT));
    }

    public static void estilizarInput(JPasswordField field) {
        field.setFont(UiTheme.fontBody());
        field.setPreferredSize(new Dimension(UiTheme.INPUT_WIDTH, UiTheme.INPUT_HEIGHT));
        field.setMinimumSize(new Dimension(UiTheme.INPUT_WIDTH, UiTheme.INPUT_HEIGHT));
    }

    @SuppressWarnings("rawtypes")
    public static void estilizarInput(JComboBox combo) {
        combo.setFont(UiTheme.fontBody());
        combo.setPreferredSize(new Dimension(UiTheme.INPUT_WIDTH, UiTheme.INPUT_HEIGHT));
        combo.setMinimumSize(new Dimension(UiTheme.INPUT_WIDTH, UiTheme.INPUT_HEIGHT));
    }

    public static void estilizarInputAncho(JTextField field, int ancho) {
        field.setFont(UiTheme.fontBody());
        field.setPreferredSize(new Dimension(ancho, UiTheme.INPUT_HEIGHT));
        field.setMinimumSize(new Dimension(ancho, UiTheme.INPUT_HEIGHT));
    }

    public static void estilizarBoton(JButton boton, TipoBoton tipo) {
        boton.setFont(UiTheme.fontBody());
        boton.putClientProperty("JButton.buttonType", "square");
        boton.setMargin(new Insets(UiTheme.SPACE_SM, UiTheme.SPACE_LG,
                UiTheme.SPACE_SM, UiTheme.SPACE_LG));

        Dimension tamano = switch (tipo) {
            case PRIMARIO -> UiTheme.BTN_LG;
            case PELIGRO -> UiTheme.BTN_MD;
            case SECUNDARIO -> UiTheme.BTN_MD;
        };
        boton.setPreferredSize(tamano);
        boton.setMinimumSize(tamano);
    }

    public static void estilizarBotonNav(JButton boton) {
        boton.setFont(UiTheme.fontBody());
        boton.putClientProperty("JButton.buttonType", "square");
        boton.putClientProperty("JComponent.minimumWidth", UiTheme.BTN_MD.width);
        boton.setMargin(new Insets(UiTheme.SPACE_SM, UiTheme.SPACE_MD,
                UiTheme.SPACE_SM, UiTheme.SPACE_MD));
        boton.setPreferredSize(UiTheme.BTN_MD);
    }

    public static JButton crearBotonRefrescar() {
        JButton boton = new JButton("Refrescar");
        estilizarBotonNav(boton);
        conIcono(boton, UiIcons.REFRESH);
        return boton;
    }

    public static void aplicarLayoutLista(JPanel root, JLabel titulo, JScrollPane scroll,
            JButton... botones) {
        root.removeAll();
        root.setLayout(new BorderLayout(0, UiTheme.SPACE_LG));
        aplicarPagina(root);
        estilizarTitulo(titulo);

        JPanel encabezado = new JPanel(new BorderLayout(0, UiTheme.SPACE_MD));
        encabezado.setOpaque(false);
        encabezado.add(titulo, BorderLayout.NORTH);

        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, UiTheme.SPACE_SM, 0));
        barra.setOpaque(false);
        for (JButton boton : botones) {
            if (boton != null) {
                barra.add(boton);
            }
        }
        encabezado.add(barra, BorderLayout.CENTER);
        root.add(encabezado, BorderLayout.NORTH);

        if (scroll != null) {
            estilizarScrollTabla(scroll);
            root.add(scroll, BorderLayout.CENTER);
        }
        root.revalidate();
        root.repaint();
    }

    public static void conIcono(JButton boton, String svgPath) {
        boton.setIcon(UiIcons.icono(svgPath));
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.setIconTextGap(UiTheme.SPACE_SM);
    }

    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(UiTheme.fontBody());
        tabla.setRowHeight(UiTheme.TABLE_ROW_HEIGHT);
        tabla.getTableHeader().setFont(UiTheme.fontSection());
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));
    }

    public static void estilizarScrollTabla(JScrollPane scroll) {
        scroll.setBorder(new EmptyBorder(UiTheme.SPACE_SM, 0, UiTheme.SPACE_SM, 0));
    }

    public static void estilizarFormPanel(JPanel panel, String titulo) {
        int inset = UiTheme.FORM_INSET;
        panel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createTitledBorder(titulo),
                new EmptyBorder(inset, inset, inset, inset)));
    }

    public static void aplicarVistaLista(JPanel root, JLabel titulo, JTable tabla,
            JScrollPane scroll, JButton... botones) {
        aplicarPagina(root);
        estilizarTitulo(titulo);
        estilizarTabla(tabla);
        if (scroll != null) {
            estilizarScrollTabla(scroll);
        }
        for (JButton boton : botones) {
            if (boton != null) {
                estilizarBotonNav(boton);
            }
        }
    }

    public static void aplicarVistaFormulario(JPanel root, JLabel titulo,
            JButton guardar, JButton cancelar, Component... campos) {
        aplicarPagina(root);
        if (titulo != null) {
            estilizarTitulo(titulo);
        }
        estilizarBoton(guardar, TipoBoton.PRIMARIO);
        estilizarBoton(cancelar, TipoBoton.SECUNDARIO);
        conIcono(guardar, UiIcons.SAVE);
        conIcono(cancelar, UiIcons.CANCEL);
        for (Component campo : campos) {
            estilizarComponente(campo);
        }
    }

    public static void aplicarVistaHome(JPanel root, JLabel titulo,
            JLabel[] secciones, JButton... botones) {
        aplicarPagina(root);
        estilizarTitulo(titulo);
        if (secciones != null) {
            for (JLabel seccion : secciones) {
                if (seccion != null) {
                    estilizarSeccion(seccion);
                }
            }
        }
        for (JButton boton : botones) {
            if (boton != null) {
                estilizarBotonNav(boton);
            }
        }
    }

    public static void aplicarVistaLogin(JPanel root, JLabel titulo, JLabel lblUsuario,
            JLabel lblPassword, JTextField txtUsuario, JPasswordField txtPassword, JButton btnLogin) {
        aplicarDialogo(root);
        estilizarTitulo(titulo);
        estilizarCuerpo(lblUsuario);
        estilizarCuerpo(lblPassword);
        estilizarInput(txtUsuario);
        estilizarInput(txtPassword);
        estilizarBoton(btnLogin, TipoBoton.PRIMARIO);
        conIcono(btnLogin, UiIcons.LOGIN);
        btnLogin.setPreferredSize(new Dimension(UiTheme.INPUT_WIDTH + UiTheme.LABEL_WIDTH + UiTheme.SPACE_LG, 36));
    }

    private static void estilizarComponente(Component campo) {
        if (campo instanceof JTextField textField) {
            estilizarInput(textField);
        } else if (campo instanceof JPasswordField passwordField) {
            estilizarInput(passwordField);
        } else if (campo instanceof JComboBox<?> comboBox) {
            estilizarInput(comboBox);
        } else if (campo instanceof JLabel label) {
            estilizarCuerpo(label);
        }
    }
}
