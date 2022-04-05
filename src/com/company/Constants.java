package com.company;

import com.company.classes.arenas.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final int CHARACTER_WIDTH = 40;
    public static final int CHARACTER_HEIGHT = 80;
    public static final int WINDOW_WIDTH = 375;
    public static final int WINDOW_HEIGHT = 438;
    public static final String defaultFontFamily = "firacode nf";
    public static final Font titleFont = new Font(defaultFontFamily, Font.BOLD, 64);
    public static final Font header1Font = new Font(defaultFontFamily, Font.BOLD, 32);
    public static final Font header2Font = new Font(defaultFontFamily, Font.BOLD, 26);
    public static final Font paragraphFont = new Font(defaultFontFamily, Font.PLAIN, 18);
    public static final List<Arena> availableArenas = new ArrayList<>(Arrays.asList(
            new Jungle(),
            new Desert(),
            new Hell(),
            new Winter()
    ));
}
