package com.mycompany.agricola.views.util;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public final class UiIcons {

    private static final String BASE = "com/mycompany/agricola/icons/";

    public static final String LOGIN = BASE + "login.svg";
    public static final String ADD = BASE + "add.svg";
    public static final String EDIT = BASE + "edit.svg";
    public static final String DELETE = BASE + "delete.svg";
    public static final String SAVE = BASE + "save.svg";
    public static final String CANCEL = BASE + "cancel.svg";
    public static final String USERS = BASE + "users.svg";
    public static final String PRODUCTS = BASE + "box.svg";
    public static final String INVENTORY = BASE + "inventory.svg";
    public static final String ALERT = BASE + "alert.svg";
    public static final String CART = BASE + "cart.svg";
    public static final String EXIT = BASE + "exit.svg";
    /** @deprecated usar {@link #EXIT} */
    public static final String BACK = EXIT;
    public static final String PDF = BASE + "pdf.svg";
    public static final String SALE = BASE + "sale.svg";
    public static final String PURCHASE = BASE + "purchase.svg";
    public static final String CLIENT = BASE + "client.svg";
    public static final String INIT = BASE + "init.svg";
    public static final String REFRESH = BASE + "refresh.svg";
    public static final String VIEW = BASE + "view.svg";

    private UiIcons() {
    }

    public static FlatSVGIcon icono(String path) {
        return new FlatSVGIcon(path, UiTheme.ICON_SIZE, UiTheme.ICON_SIZE);
    }

    public static FlatSVGIcon iconoGrande(String path) {
        return new FlatSVGIcon(path, UiTheme.ICON_SIZE_LG, UiTheme.ICON_SIZE_LG);
    }

    public static FlatSVGIcon iconoTile(String path) {
        return new FlatSVGIcon(path, UiTheme.ICON_SIZE_TILE, UiTheme.ICON_SIZE_TILE);
    }
}
