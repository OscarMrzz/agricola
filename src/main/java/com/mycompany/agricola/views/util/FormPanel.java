package com.mycompany.agricola.views.util;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FormPanel extends JPanel {

    public FormPanel() {
        this(null);
    }

    public FormPanel(String titulo) {
        super(new BorderLayout());
        int inset = UiTheme.FORM_INSET;
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(titulo != null ? titulo : " "),
                new EmptyBorder(inset, inset, inset, inset)));
    }
}
