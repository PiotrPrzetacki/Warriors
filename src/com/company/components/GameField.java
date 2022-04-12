package com.company.components;

import com.company.Constants;
import com.company.MainWindow;
import com.company.Team;
import com.company.classes.CharacterClass;
import com.company.classes.arenas.Arena;
import com.company.components.controls.HealthBar;
import com.company.components.controls.PausePanel;
import com.company.components.controls.StartGameMenu;
import com.company.components.layouts.PlayersStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class GameField extends JPanel {
    private Team team;
    private Arena arena;
    private CharacterClass[] players;
    private PlayersStats playersStats;
    private PausePanel pausePanel;
    private boolean repaintLoopEnabled;
    private boolean pauseState;
    private MainWindow mainWindow;

    public GameField(MainWindow mainWindow, Team team, Arena arena) {
        this.team = team;
        this.players = team.getTeamMembers();
        this.arena = arena;
        this.playersStats = new PlayersStats(players);
        this.pausePanel = new PausePanel(mainWindow);
        this.repaintLoopEnabled = true;
        this.pauseState = true;
        this.mainWindow = mainWindow;

        mainWindow.setSize(Constants.WINDOW_WIDTH+15, Constants.WINDOW_HEIGHT+42);
        setLayout(new BorderLayout());
        setPlayersArena();
        setPlayersPositions();
        setRepaintLoop();
        setWalls(arena.getWalls());
        arena.setArenaEvent();
        setBackground(arena.getBackgroundColor());
        add(playersStats, BorderLayout.SOUTH);

        add(new StartGameMenu(this), BorderLayout.CENTER);

        setFocusable(true);
        addKeyListener(new FieldKeyListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        playersStats.refresh();

        for (CharacterClass player : players) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
            g.drawString(player.getName(), player.getX(), player.getY()+12);
        }
        for (int i=0; i<CharacterClass.occupiedCells.length; i++){
            for (int j=0; j<CharacterClass.occupiedCells[0].length; j++){
                if(CharacterClass.occupiedCells[i][j] == -1){
                    g.drawImage(arena.getWallImage(), i, j, this);
                }
                if(arena.getSpecialSquares()[i][j] == -2){
                    g.drawImage(arena.getFireImage(), i, j, this);
                }
                else if(arena.getSpecialSquares()[i][j] == -3){
                    g.drawImage(arena.getIcySquareImage(), i, j, this);
                    for (CharacterClass player : players) {
                        g.drawImage(player.getImage(), player.getX(), player.getY(), this);
                        g.drawString(player.getName(), player.getX(), player.getY()+12);
                    }
                }
            }
        }
    }

    public class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            for (CharacterClass player : players) {
                if(player.getCanMove()) {
                    if (key == player.getLeftKey()) {
                        player.left();
                    }
                    if (key == player.getRightKey()) {
                        player.right();
                    }
                    if (key == player.getUpKey()) {
                        player.up();
                    }
                    if (key == player.getDownKey()) {
                        player.down();
                    }
                }
                if(player.getCanAttack()) {
                    if (key == player.getLeftAttackKey()) {
                        player.setAttackLeftImage();

                        if (CharacterClass.occupiedCells[player.getX() - Constants.CHARACTER_WIDTH][player.getY()] > 0) {
                            player.attack(players[CharacterClass.occupiedCells[player.getX() - Constants.CHARACTER_WIDTH][player.getY()] - 1]);
                        }

                        //timer
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        player.setBaseImage();
                                        repaint();
                                    }
                                }, 200
                        );
                    }
                    if (key == player.getRightAttackKey()) {
                        player.setAttackRightImage();

                        if (CharacterClass.occupiedCells[player.getX() + Constants.CHARACTER_WIDTH][player.getY()] > 0) {
                            player.attack(players[CharacterClass.occupiedCells[player.getX() + Constants.CHARACTER_WIDTH][player.getY()] - 1]);
                        }

                        //timer
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        player.setBaseImage();
                                        repaint();
                                    }
                                }, 200
                        );
                    }
                }
            }
            if(key == KeyEvent.VK_Y){
                arena.setEventEnabled(false);
                System.out.println((players[0].getX()/40) + " " + (players[0].getY())/80);
            }
            else if(key == KeyEvent.VK_ESCAPE){
                pauseState = !pauseState;
                pauseGame(pauseState);
            }
            repaint();
        }
    }

    private void setWalls(List<int[]> walls){
        for (int[] wall : walls) {
            CharacterClass.occupiedCells[(wall[0] * Constants.CHARACTER_WIDTH)][(wall[1] * Constants.CHARACTER_HEIGHT)] = -1;
        }
    }
    private void setPlayersPositions(){
        for(int i=0; i<players.length; i++){
            players[i].tryChangePosition(arena.getPlayersSpawnPoints().get(i)[0]*Constants.CHARACTER_WIDTH,
                                         arena.getPlayersSpawnPoints().get(i)[1]*Constants.CHARACTER_HEIGHT,
                                         false);
        }
    }

    private void setPlayersArena(){
        for(CharacterClass player : players){
            player.setArena(arena);
        }
    }

    private void setRepaintLoop(){
        if(repaintLoopEnabled) {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            repaint();
                            setRepaintLoop();
                        }
                    }, 30
            );
        }
    }

    public void setPlayersCanMove(boolean canMove){
        for(CharacterClass player : players){
            player.setCanMove(canMove);
        }
    }

    public void setPlayersCanAttack(boolean canAttack){
        for(CharacterClass player : players){
            player.setCanAttack(canAttack);
        }
    }

    private void pauseGame(boolean pauseState){
        pausePanel.refresh();
        if(pauseState) {
            setPlayersCanMove(true);
            setPlayersCanAttack(true);
            remove(pausePanel);
        }
        else {
            setPlayersCanMove(false);
            setPlayersCanAttack(false);
            add(pausePanel, BorderLayout.CENTER);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }
}
