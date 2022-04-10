package com.company;

import com.company.classes.characters.Archer;
import com.company.classes.characters.Warrior;
import com.company.components.GameField;
import com.company.components.GameSettings;
import com.company.classes.arenas.Arena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class MainWindow extends JFrame {

    private Team team;
    private Arena arena;
    private GameSettings gameSettingsPanel;
    private GameField gameField;

    public MainWindow(int width, int height) {
        super();
        setSize(new Dimension(width+15, height));
        setLocationRelativeTo(null);
        setTitle("Warriors");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameSettingsPanel = new GameSettings(this);
        add(gameSettingsPanel);
        setVisible(true);
    }

    public void startGame(int gameMode, Team team, Arena arena){
        remove(gameSettingsPanel);
        this.gameField = new GameField(this, team, arena);
        add(gameField);
        gameField.requestFocusInWindow();
        revalidate();
        repaint();

    }
}
