package com.company.utils;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ResourceLoader {
    public static URL load(String path){
        return ResourceLoader.class.getResource(path);
    }

    public static void loadFont(String... names){
        for(String name : names){
            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(ResourceLoader.class.getResourceAsStream("/fonts/"+name))));
            } catch (IOException | FontFormatException ignored){}
        }

    }
}
