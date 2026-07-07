package com.mycompany.agricola.views.admin.usuarios;

import com.mycompany.agricola.views.util.UiIcons;
import com.mycompany.agricola.views.util.UiStyle;

public class UsuariosVista extends javax.swing.JPanel {

    public javax.swing.JButton botonAgregar;
    public javax.swing.JButton botonEditar;
    public javax.swing.JButton botonEliminar;
    public javax.swing.JButton botonVolver;
    public javax.swing.JButton botonRefrescar;
    public javax.swing.JLabel etiquetaTitulo;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tablaUsuarios;

    public UsuariosVista() {
        initComponents();
        aplicarEstilos();
    }

    private void aplicarEstilos() {
        botonRefrescar = UiStyle.crearBotonRefrescar();
        UiStyle.estilizarTabla(tablaUsuarios);
        UiStyle.estilizarBotonNav(botonAgregar);
        UiStyle.estilizarBotonNav(botonEditar);
        UiStyle.estilizarBotonNav(botonEliminar);
        UiStyle.estilizarBotonNav(botonVolver);
        UiStyle.conIcono(botonAgregar, UiIcons.ADD);
        UiStyle.conIcono(botonEditar, UiIcons.EDIT);
        UiStyle.conIcono(botonEliminar, UiIcons.DELETE);
        UiStyle.conIcono(botonVolver, UiIcons.EXIT);
        UiStyle.aplicarLayoutLista(this, etiquetaTitulo, scrollTabla,
                botonAgregar, botonEditar, botonEliminar, botonRefrescar, botonVolver);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        etiquetaTitulo = new javax.swing.JLabel();
        botonAgregar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();

        etiquetaTitulo.setFont(new java.awt.Font("Arial Black", 1, 16));
        etiquetaTitulo.setText("Listado de usuarios");

        botonAgregar.setText("Agregar");
        botonEditar.setText("Editar");
        botonEliminar.setText("Eliminar");
        botonVolver.setText("Volver");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"No", "Nombre", "Rol", "Estatus"}
        ));
        scrollTabla.setViewportView(tablaUsuarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonAgregar)
                        .addGap(8, 8, 8)
                        .addComponent(botonEditar)
                        .addGap(8, 8, 8)
                        .addComponent(botonEliminar)
                        .addGap(8, 8, 8)
                        .addComponent(botonVolver))
                    .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etiquetaTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregar)
                    .addComponent(botonEditar)
                    .addComponent(botonEliminar)
                    .addComponent(botonVolver))
                .addGap(18, 18, 18)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }
}
