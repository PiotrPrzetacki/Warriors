package com.company;

import com.company.classes.CharacterClass;
import com.company.components.GameField;
import com.company.components.GameSettings;
import com.company.classes.arenas.Arena;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

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
        try {
            remove(gameSettingsPanel);
            remove(gameField);
        }catch(NullPointerException e){

        }
        CharacterClass.resetOccupiedCells();
        arena.resetArena();
        for(CharacterClass player : team.getTeamMembers()){
            player.setHealthPoints(player.getMaxHealthPoints());
        }
        this.gameField = new GameField(this, team, arena);
        add(gameField);
        gameField.requestFocusInWindow();
        revalidate();
        repaint();

    }

    public void goToMainMenu(Arena arena){

        try {
            remove(gameField);
        }catch(NullPointerException e){

        }
        arena.closeArena();
        CharacterClass.resetOccupiedCells();
        CharacterClass.setPlayerCount(CharacterClass.getPlayerCount()-2);
        gameSettingsPanel = new GameSettings(this);
        add(gameSettingsPanel);
        revalidate();
        repaint();
        System.gc();
    }

    public GameSettings getGameSettingsPanel() {
        return gameSettingsPanel;
    }

    public GameField getGameField() {
        return gameField;
    }
}
