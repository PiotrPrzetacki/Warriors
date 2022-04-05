package com.company;

import com.company.components.GameSettings;
import com.company.classes.arenas.Arena;

import javax.swing.*;


public class MainWindow extends JFrame {

    private Team team;
    private Arena arena;
    private GameSettings gameSettingsPanel;

    public MainWindow(int weight, int height) {
        setSize(weight, height);
        setLocationRelativeTo(null);
        setTitle("Warriors");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameSettingsPanel = new GameSettings(this);

        add(gameSettingsPanel);
        //add(new GameField(team));
        setVisible(true);
    }
}
