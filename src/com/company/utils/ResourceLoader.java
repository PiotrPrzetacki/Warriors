package com.company.utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class ResourceLoader {
    public static URL load(String path){
        return ResourceLoader.class.getResource(path);
    }
}
