package com.company.components;

import com.company.MainWindow;
import com.company.classes.CharacterClass;
import com.company.classes.arenas.Arena;
import com.company.components.layouts.ArenaSelectionPanel;
import com.company.components.layouts.CharacterSelectionPanel;
import com.company.components.layouts.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameSettings extends JPanel {

    private int gameMode;
    private List<CharacterClass> players;
    private Arena arena;

    private MainMenuPanel mainMenuPanel;
    private CharacterSelectionPanel characterSelectionPanel;
    private ArenaSelectionPanel arenaSelectionPanel;

    private MainWindow mainWindow;

    public GameSettings(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.players = new ArrayList<>();

        setLayout(new GridLayout(0, 1));
        setMainMenu();
    }

    public void setMainMenu(){
        mainMenuPanel = new MainMenuPanel(this);
        removeAll();
        add(mainMenuPanel);
        revalidate();
        repaint();
    }

    public void setCharacterSelection(){
        characterSelectionPanel = new CharacterSelectionPanel(this);
        removeAll();
        add(characterSelectionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void setArenaSelectionPanel(){
        arenaSelectionPanel = new ArenaSelectionPanel(this);
        removeAll();
        add(arenaSelectionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public void addPlayer(CharacterClass player){
        players.add(player);
    }

    public void clearPlayers(){
        players.clear();
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public int getGameMode() {
        return gameMode;
    }

    public List<CharacterClass> getPlayers() {
        return players;
    }
}
