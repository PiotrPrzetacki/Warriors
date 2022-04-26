package com.company.components;

import com.company.Constants;
import com.company.MainWindow;
import com.company.Team;
import com.company.classes.BaseClass;
import com.company.classes.CharacterClass;
import com.company.classes.arenas.Arena;
import com.company.classes.characters.Abilities;
import com.company.classes.objects.Blood;
import com.company.classes.objects.Fireball;
import com.company.classes.objects.FreeObject;
import com.company.components.controls.PausePanel;
import com.company.components.controls.StartGameMenu;
import com.company.components.layouts.PlayersStats;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameField extends JPanel {
    private boolean pauseState;
    private boolean playersAbilitiesActivated;
    private ScheduledExecutorService repaintExecutor;
    private final Arena arena;
    private final CharacterClass[] players;
    private final PlayersStats playersStats;
    private final PausePanel pausePanel;
    private final StartGameMenu startGameMenu;
    private final List<FreeObject> freeObjects;

    public GameField(MainWindow mainWindow, Team team, Arena arena) {
        this.players = team.getTeamMembers();
        this.arena = arena;
        this.playersStats = new PlayersStats(players);
        this.pausePanel = new PausePanel(mainWindow, "Game NOT paused");
        this.startGameMenu = new StartGameMenu(this);
        this.pauseState = true;
        this.freeObjects = new ArrayList<>();
        this.repaintExecutor = Executors.newSingleThreadScheduledExecutor();

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
        addKeyListener(new FieldKeyListener(this));
    }

    private void paintCharacters(Graphics g){
        for (CharacterClass player : players) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
            g.drawString(player.getName(), player.getX(), player.getY()+12);
        }

        if(this instanceof MonstersAttackGameField){
            ((MonstersAttackGameField) this).paintMonsters(g);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playersStats.refresh();
        if(pauseState) {
            checkGameOver();
        }

        paintCharacters(g);

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
                    paintCharacters(g);
                }
            }
        }
        try {
            for (FreeObject obj : freeObjects) {
                if (obj instanceof Fireball) {
                    g.drawImage(obj.getImageToDraw(), obj.getX() - obj.getImageToDraw().getWidth(null) / 2,
                            obj.getY() - obj.getImageToDraw().getHeight(null) / 2, this);
                } else {
                    g.drawImage(obj.getImageToDraw(), obj.getX(), obj.getY(), this);
                }
            }
        }catch (ConcurrentModificationException ignored){}
    }

    public void attack(int attackedCharacterNumber, int attackAmount){
        CharacterClass attackedPlayer = this.getPlayers()[attackedCharacterNumber - 1];
        attackedPlayer.reduceHealth(attackAmount);
        this.getFreeObjects().add(new Blood(attackedPlayer.getX(), attackedPlayer.getY(), this.getFreeObjects()));
    }

    public class FieldKeyListener extends KeyAdapter {
        private final GameField gameField;
        public FieldKeyListener(GameField gameField){
            this.gameField = gameField;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(playersAbilitiesActivated) {
                for (CharacterClass player : players) {
                    if (player.getAbilityTimeouts().get(Abilities.MOVE)[0] == 0) {
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
                    if (player.getAbilityTimeouts().get(Abilities.ATTACK)[0] == 0) {
                        Timer timer = new Timer(150, e1 -> {
                            player.setBaseImage();
                            repaint();
                        });
                        timer.setRepeats(false);

                        if (key == player.getLeftAttackKey()) {
                            player.setAttackLeftImage();

                            player.attack(-1, players, gameField);
                            timer.start();

                        }
                        if (key == player.getRightAttackKey()) {
                            player.setAttackRightImage();

                            player.attack(1, players, gameField);

                            timer.start();
                        }
                    }
                    if(key == player.getSpecialAbilityKey()){
                        player.useSpecialAbility(gameField);
                    }
                }
            }
            if(key == KeyEvent.VK_ESCAPE){
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
        repaintExecutor.scheduleAtFixedRate(this::repaint, 0, 30, TimeUnit.MILLISECONDS);
    }

    public Arena getArena() {
        return arena;
    }

    protected void pauseGame(boolean pauseState){
        pausePanel.refresh();
        if(pauseState) {
            remove(pausePanel);
        }
        else {
            add(pausePanel, BorderLayout.CENTER);
        }
        pausePanel.refresh();
    }

    protected void checkGameOver(){
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
        CharacterClass.resetOccupiedCells();
        arena.closeArena();
        repaintExecutor.shutdown();
        startGameMenu.getStartGameWorker().cancel(true);
    }

    public void setPlayersAbilitiesActivated(boolean activateState){
        playersAbilitiesActivated = activateState;
    }

    public List<FreeObject> getFreeObjects() {
        return freeObjects;
    }

    public CharacterClass[] getPlayers() {
        return players;
    }

    public PausePanel getPausePanel() {
        return pausePanel;
    }

    public void setPauseState(boolean pauseState) {
        this.pauseState = pauseState;
    }
}
