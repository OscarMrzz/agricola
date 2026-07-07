package com.mycompany.agricola.views.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public final class UiTheme {

    public static final int SPACE_XS = 4;
    public static final int SPACE_SM = 8;
    public static final int SPACE_MD = 12;
    public static final int SPACE_LG = 16;
    public static final int SPACE_XL = 20;
    public static final int SPACE_XXL = 24;
    public static final int SPACE_XXXL = 32;

    public static final int PAGE_INSET = 20;
    public static final int FORM_INSET = 16;
    public static final int DIALOG_INSET = 24;

    public static final int INPUT_HEIGHT = 28;
    public static final int INPUT_WIDTH = 260;
    public static final int LABEL_WIDTH = 120;

    public static final Dimension BTN_SM = new Dimension(90, 28);
    public static final Dimension BTN_MD = new Dimension(110, 32);
    public static final Dimension BTN_LG = new Dimension(130, 36);
    public static final Dimension BTN_TILE = new Dimension(160, 140);

    public static final int ICON_SIZE = 16;
    public static final int ICON_SIZE_LG = 20;
    public static final int ICON_SIZE_TILE = 32;
    public static final int TABLE_ROW_HEIGHT = 28;

    public static final int FONT_TITLE_SIZE = 18;
    public static final int FONT_SECTION_SIZE = 13;
    public static final int FONT_BODY_SIZE = 12;
    public static final int FONT_SMALL_SIZE = 11;
    public static final int FONT_TOTAL_SIZE = 13;

    public static final Color COLOR_ERROR = new Color(255, 107, 107);
    public static final Color COLOR_SUCCESS = new Color(107, 203, 119);

    private static final String FONT_FAMILY = "Segoe UI";

    private UiTheme() {
    }

    public static Font fontTitle() {
        return new Font(FONT_FAMILY, Font.BOLD, FONT_TITLE_SIZE);
    }

    public static Font fontSection() {
        return new Font(FONT_FAMILY, Font.BOLD, FONT_SECTION_SIZE);
    }

    public static Font fontBody() {
        return new Font(FONT_FAMILY, Font.PLAIN, FONT_BODY_SIZE);
    }

    public static Font fontSmall() {
        return new Font(FONT_FAMILY, Font.PLAIN, FONT_SMALL_SIZE);
    }

    public static Font fontTotal() {
        return new Font(FONT_FAMILY, Font.BOLD, FONT_TOTAL_SIZE);
    }
}
