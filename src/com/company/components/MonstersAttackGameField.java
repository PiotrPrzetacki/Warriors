package com.company.components;

import com.company.MainWindow;
import com.company.Team;
import com.company.classes.BaseClass;
import com.company.classes.CharacterClass;
import com.company.classes.arenas.Arena;
import com.company.classes.monsters.Monster;
import com.company.classes.monsters.MonstersController;
import com.company.classes.monsters.Skeleton;
import com.company.classes.objects.Blood;
import com.company.components.controls.HeaderLabel;
import com.company.components.controls.WaveInformationPanel;
import com.company.components.layouts.MonstersAttackInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class MonstersAttackGameField extends GameField{

    private int wave;
    private int points;
    private final List<Monster> monsters;
    private final MonstersAttackInfoPanel infoPanel;
    private final MonstersController monstersController;

    public MonstersAttackGameField(MainWindow mainWindow, Team team, Arena arena) {
        super(mainWindow, team, arena);
        this.monsters = new ArrayList<>();
        this.infoPanel = new MonstersAttackInfoPanel(this);
        this.monstersController = new MonstersController(this);
        this.wave = 1;
        this.points = 0;
        add(infoPanel, BorderLayout.NORTH);
    }

    public void paintMonsters(Graphics g){
        for(Monster monster : monsters){
            g.drawImage(monster.getCurrentImage(), monster.getX(), monster.getY(), this);
            g.drawString(String.valueOf(monster.getHealthPoints()), monster.getX(), monster.getY()+15);
        }
    }

    @Override
    public void attack(int attackedCharacterNumber, int attackAmount){
        BaseClass attackedCharacter;
        try {
            attackedCharacter = this.getPlayers()[attackedCharacterNumber - 1];
        } catch (ArrayIndexOutOfBoundsException e){
            attackedCharacter = monsters.stream().filter(monster -> monster.getNumber()==attackedCharacterNumber).findAny().orElse(null);
        }
        attackedCharacter.reduceHealth((int) (attackAmount*new Random().doubles(0.9, 1.1).findFirst().getAsDouble()));
        this.getFreeObjects().add(new Blood(attackedCharacter.getX(), attackedCharacter.getY(), this.getFreeObjects()));
    }

    @Override
    protected void checkGameOver() {
        for(CharacterClass player : getPlayers()){
            if(player.getHealthPoints()>0) return;
        }
        pauseGame(false);
        setPauseState(false);
        getPausePanel().setPauseText("You died");
        getPausePanel().getPauseMenu().addWaveInfo(wave-1, points);
    }

    @Override
    protected void pauseGame(boolean pauseState) {
        super.pauseGame(pauseState);
        infoPanel.setVisible(pauseState);
    }

    @Override
    public void closeGameField(){
        super.closeGameField();
        int mSize = monsters.size();
        for(int i=0; i<mSize; i++){
            monsters.get(0).killMonster();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        infoPanel.refresh();
        if(monsters.isEmpty()){
            wave++;
            monstersController.startNextWave(wave);
            infoPanel.setVisible(false);
            WaveInformationPanel wavePanel = new WaveInformationPanel(wave);
            add(wavePanel, BorderLayout.NORTH);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    remove(wavePanel);
                    infoPanel.setVisible(true);
                }
            }, 2000);
        }
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public int getWave() {
        return wave;
    }

    public int getPoints() {
        return points;
    }
}
