package com.company.components;

import com.company.Constants;
import com.company.MainWindow;
import com.company.Team;
import com.company.classes.CharacterClass;
import com.company.classes.arenas.Arena;
import com.company.components.controls.PausePanel;
import com.company.components.controls.StartGameMenu;
import com.company.components.layouts.PlayersStats;
import com.company.utils.PausableSwingWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class GameField extends JPanel {
    private final Arena arena;
    private final CharacterClass[] players;
    private final PlayersStats playersStats;
    private final PausePanel pausePanel;
    private final boolean repaintLoopEnabled;
    private boolean pauseState;
    private StartGameMenu startGameMenu;

    public GameField(MainWindow mainWindow, Team team, Arena arena) {
        this.players = team.getTeamMembers();
        this.arena = arena;
        this.playersStats = new PlayersStats(players);
        this.pausePanel = new PausePanel(mainWindow, "Game paused");
        this.startGameMenu = new StartGameMenu(this);
        this.repaintLoopEnabled = true;
        this.pauseState = true;

        mainWindow.setSize(Constants.WINDOW_WIDTH+15, Constants.WINDOW_HEIGHT+42);
        setLayout(new BorderLayout());
        setPlayersArena();
        arena.resetArena();
        setPlayersPositions();
        setRepaintLoop();
        setWalls(arena.getWalls());
        setBackground(arena.getBackgroundColor());
        add(playersStats, BorderLayout.SOUTH);

        add(startGameMenu, BorderLayout.CENTER);

        setFocusable(true);
        addKeyListener(new FieldKeyListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playersStats.refresh();
        System.out.println(players[0].getAbilityTimeouts().get("attack"));
        if(pauseState) {
            checkGameOver();
            startGameMenu.getStartGameWorker().resume();
        }
        else{
            setPlayersCanAttack(false);
            setPlayersCanMove(false);
            startGameMenu.getStartGameWorker().pause();
        }

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
                if(player.getCanMove() && player.getAbilityTimeouts().get("move")==0) {
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
                if(player.getCanAttack() && player.getAbilityTimeouts().get("attack")==0) {
                    Timer timer = new Timer(150, e1 -> {
                        player.setBaseImage();
                        repaint();
                    });
                    timer.setRepeats(false);

                    if (key == player.getLeftAttackKey()) {
                        player.setAttackLeftImage();

                        player.attack(1, players);
                        timer.start();

                    }
                    if (key == player.getRightAttackKey()) {
                        player.setAttackRightImage();

                        player.attack(0, players);

                        timer.start();
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
        new Timer(20, e -> repaint()).start();
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

    public Arena getArena() {
        return arena;
    }

    private void pauseGame(boolean pauseState){
        pausePanel.refresh();
        if(pauseState) {
            arena.resumeBackgroundWorkers();
            setPlayersCanMove(true);
            setPlayersCanAttack(true);
            remove(pausePanel);

        }
        else {
            arena.pauseBackgroundWorkers();
            setPlayersCanMove(false);
            setPlayersCanAttack(false);
            add(pausePanel, BorderLayout.CENTER);
        }
        pausePanel.refresh();
    }

    private void checkGameOver(){
        for(CharacterClass player : players){
            if(player.getHealthPoints()<=0){
                for(CharacterClass winningPlayer : players){
                    if(winningPlayer.getHealthPoints()>0){
                        pausePanel.setPauseText(winningPlayer.getName() + " wins!");
                        pauseGame(false);
                    }
                }
                break;
            }
        }
    }

    public void closeGameField(){
        arena.closeArena();
        startGameMenu.getStartGameWorker().cancel(true);
    }
}
