package com.company.classes.objects;

import com.company.Constants;
import com.company.classes.CharacterClass;
import com.company.components.GameField;

import javax.swing.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.company.utils.ResourceLoader.load;

public class Fireball extends FreeObject{

    private final int attackAmount = 180;
    private final int speed = 8;

    public Fireball(int startX, int startY, int endX, int endY, GameField gameField, CharacterClass owner){
        this.x = startX;
        this.y = startY;
        double rad = Math.atan2(endY-startY, endX-startX);
        double deg = Math.toDegrees(rad);
        this.imageToDraw = new ImageIcon(load(getPathToImage(deg))).getImage();

        double[] v = new double[]{Math.cos(rad)*speed, Math.sin(rad)*speed};

        Fireball _this = this;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                x += (int) v[0];
                y += (int) v[1];

                if(isOutsideGamefield()){
                    killFireball(gameField, timer);
                }
                else {

                    int[] square = getSquare(x, y);
                    if (CharacterClass.occupiedCells[square[0]][square[1]] == -1) {
                        killFireball(gameField, timer);
                    } else if (CharacterClass.occupiedCells[square[0]][square[1]] > 0 && CharacterClass.occupiedCells[square[0]][square[1]] != owner.getNumber()) {
                        CharacterClass attackedPlayer = gameField.getPlayers()[CharacterClass.occupiedCells[square[0]][square[1]] - 1];
                        attackedPlayer.reduceHealth(attackAmount);
                        gameField.getFreeObjects().add(new Blood(attackedPlayer.getX(), attackedPlayer.getY(), gameField.getFreeObjects()));
                        killFireball(gameField, timer);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 20);
    }

    private void killFireball(GameField gameField, Timer timer){
        gameField.getFreeObjects().remove(this);
        timer.cancel();
    }

    private String getPathToImage(double deg){
        String pathToImage = "/images/characters/effects/fireball/";
        if(deg >= -22.5 && deg < 22.5) {
            pathToImage += "fireball-right.gif";
        } else if(deg >= 22.5 && deg< 67.5){
            pathToImage += "fireball-downright.gif";
        } else if(deg >= 67.5 && deg< 112.5){
            pathToImage += "fireball-down.gif";
        } else if(deg >= 112.5 && deg< 157.5){
            pathToImage += "fireball-downleft.gif";
        } else if((deg >= 157.5 && deg<=180) || (deg< -157.5 && deg>-180)){
            pathToImage += "fireball-left.gif";
        } else if(deg <= -112.5 && deg> -157.5){
            pathToImage += "fireball-upleft.gif";
        } else if(deg <= -67.5 && deg> -112.5){
            pathToImage += "fireball-up.gif";
        } else{
            pathToImage += "fireball-upright.gif";
        }
        return pathToImage;
    }

    private int[] getSquare(int x, int y){
        int[] res = new int[2];
        res[0] = x - (x % Constants.CHARACTER_WIDTH);
        res[1] = y - (y % Constants.CHARACTER_HEIGHT);
        return res;
    }

    private boolean isOutsideGamefield(){
        if(x<=0 || x>=Constants.WINDOW_WIDTH || y<=0 || y>=Constants.WINDOW_HEIGHT){
            return true;
        }
        else return false;
    }
}
