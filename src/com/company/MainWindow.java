package com.company;

import com.company.classes.CharacterClass;
import com.company.components.GameField;
import com.company.components.GameSettings;
import com.company.classes.arenas.Arena;
import com.company.components.MonstersAttackGameField;

import javax.swing.*;
import java.awt.*;

import static com.company.utils.ResourceLoader.load;


public class MainWindow extends JFrame {

    private Arena arena;
    private GameSettings gameSettingsPanel;
    private GameField gameField;

    public MainWindow(int width, int height) {
        super();
        setSize(new Dimension(width+15, height));
        setLocationRelativeTo(null);
        setTitle("Warriors");
        setIconImage(new ImageIcon(load("/icons/Warriors-icon.png")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameSettingsPanel = new GameSettings(this);
        add(gameSettingsPanel);
        setVisible(true);
    }

    public void startGame(int gameMode, Team team, Arena arena){
        try {
            remove(gameSettingsPanel);
            remove(gameField);
        }catch(NullPointerException ignored){}
        if(gameField!=null) {
            gameField.closeGameField();
        }else{
            CharacterClass.resetOccupiedCells();
            arena.resetArena();
        }
        for(CharacterClass player : team.getTeamMembers()){
            player.setHealthPoints(player.getMaxHealthPoints());
            player.getWorkers().forEach(worker -> worker.cancel(true));
            player.getWorkers().clear();
            player.getAbilityTimeouts().forEach( (ability, timeout) -> timeout[0] = 0 );
        }
        if(gameMode==2) {
            this.gameField = new GameField(this, team, arena);
        }
        else if(gameMode==1){
            this.gameField = new MonstersAttackGameField(this, team, arena);
        }
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
        gameField.closeGameField();
        CharacterClass.setPlayerCount(0);
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
