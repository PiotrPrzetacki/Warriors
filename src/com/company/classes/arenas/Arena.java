package com.company.classes.arenas;

import com.company.Team;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public abstract class Arena implements BaseArena{

    protected String arenaName;
    protected Image arenaImage;
    protected Image wallImage;
    protected Color backgroundColor;
    protected List<int[]> walls;
    protected List<int[]> playersSpawnPoints;

    private boolean isOpened = false;

    public boolean open(Team team) {
        if (team.getTeamMembers().length < 1) {
            System.out.println("Not enough party members!");
            isOpened = false;
        } else {
            System.out.println("Welcome, heroes");
            isOpened = true;
        }
        return isOpened;
    }

    public String getArenaName() {
        return arenaName;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public List<int[]> getWalls() {
        return walls;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public List<int[]> getPlayersSpawnPoints() {
        return playersSpawnPoints;
    }

    public Image getArenaImage() {
        return arenaImage;
    }

    public void setImages(){
        this.wallImage = new ImageIcon("assets/images/arenas/"+arenaName.toLowerCase()+"_wall.png").getImage();
        this.arenaImage = new ImageIcon("assets/images/arenas/"+arenaName.toLowerCase()+"_view.png").getImage();
    }
}
