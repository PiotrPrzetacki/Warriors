package com.company;

import static com.company.utils.ResourceLoader.loadFont;

public class Main {

    public static void main(String[] args) {
        loadFont("Metal-Vengeance.ttf", "Fira-Code.ttf", "Fira-Code-Bold.ttf");
        MainWindow mainWindow = new MainWindow(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }
}