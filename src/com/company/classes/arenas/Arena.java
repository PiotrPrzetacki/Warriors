package com.company.classes.arenas;

import com.company.Constants;
import com.company.Team;
import com.company.classes.CharacterClass;

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
    protected boolean eventEnabled;
    protected int[][] specialSquares;

    private Image fireImage;
    private Image icySquareImage;

    private boolean isOpened = false;

    public Arena(){
        this.fireImage = new ImageIcon("assets/images/arenas/hell_fire.gif").getImage();
        this.icySquareImage = new ImageIcon("assets/images/arenas/winter_icy_square.png").getImage();
        this.specialSquares = new int[CharacterClass.occupiedCells.length][CharacterClass.occupiedCells[0].length];

        for (int i = 0; i < specialSquares.length; i++) {
            for (int j = 0; j < specialSquares[0].length; j++) {
                specialSquares[i][j] = 0;
            }
        }
    }

    public abstract void setArenaEvent();

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

    public Image getFireImage() {
        return fireImage;
    }

    public void setEventEnabled(boolean eventEnabled) {
        this.eventEnabled = eventEnabled;
    }

    protected void getRandomSquares(int squaresAmount, int type) {
        for (int[] wall : walls) {
            specialSquares[(wall[0] * Constants.CHARACTER_WIDTH)][(wall[1] * Constants.CHARACTER_HEIGHT)] = -1;
        }
        for (int i = 0; i < squaresAmount; i++) {
            int x, y;
            do {
                x = (((int) (Math.random() * Constants.WINDOW_WIDTH)) / Constants.CHARACTER_WIDTH) * Constants.CHARACTER_WIDTH;
                y = (((int) (Math.random() * Constants.WINDOW_HEIGHT)) / Constants.CHARACTER_HEIGHT) * Constants.CHARACTER_HEIGHT;
            } while (specialSquares[x][y] != 0 && CharacterClass.occupiedCells[x][y] != 0);
            specialSquares[x][y] = type;
        }
    }

    public int[][] getSpecialSquares() {
        return specialSquares;
    }

    public Image getIcySquareImage() {
        return icySquareImage;
    }
}
