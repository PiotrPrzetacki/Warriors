package com.company.components;

import com.company.MainWindow;
import com.company.classes.CharacterClass;
import com.company.classes.arenas.Arena;
import com.company.components.layouts.ArenaSelectionPanel;
import com.company.components.layouts.CharacterSelectionPanel;
import com.company.components.layouts.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameSettings extends JPanel {

    private int gameMode;
    private final List<CharacterClass> players;
    private Arena arena;

    private final MainWindow mainWindow;

    public GameSettings(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.players = new ArrayList<>();

        setLayout(new GridLayout(0, 1));
        setMainMenu();
    }

    public void setMainMenu(){
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        removeAll();
        add(mainMenuPanel);
        revalidate();
        repaint();
    }

    public void setCharacterSelection(){
        CharacterSelectionPanel characterSelectionPanel = new CharacterSelectionPanel(this);
        removeAll();
        add(characterSelectionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void setArenaSelectionPanel(){
        ArenaSelectionPanel arenaSelectionPanel = new ArenaSelectionPanel(this);
        removeAll();
        add(arenaSelectionPanel);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void setPlayers(List<CharacterClass> players){
        if(players.size()==0) return;
        if(players.size()==1 || players.size()==2){
            players.get(0).setCharacterData(0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E, KeyEvent.VK_Z);
        }
        if(players.size()==2){
            players.get(1).setCharacterData(320, 0, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_O, KeyEvent.VK_P, KeyEvent.VK_I);
        }
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

    public Arena getArena() {
        return arena;
    }

    public int getGameMode() {
        return gameMode;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public List<CharacterClass> getPlayers() {
        return players;
    }
}
