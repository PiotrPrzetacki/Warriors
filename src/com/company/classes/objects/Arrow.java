package com.company.classes.objects;

import com.company.Constants;
import com.company.classes.CharacterClass;
import com.company.components.GameField;

import javax.swing.*;
import java.awt.*;

import static com.company.utils.ImageManipulator.flipHorizontally;
import static com.company.utils.ResourceLoader.load;

public class Arrow extends FreeObject{

    private final int distance;
    private int distanceTravelled;
    private final int attackAmount;

    public Arrow(CharacterClass player, int direction, GameField gameField){
        int yOffset = 50;
        this.x = player.getX();
        this.y = player.getY()+yOffset;
        this.distance = player.getAttackDistance();
        this.attackAmount = player.getAttackAmount();
        this.distanceTravelled = 0;
        Image arrowImage = new ImageIcon(load("/images/characters/effects/arrow.png")).getImage();
        this.imageToDraw = direction==1 ? arrowImage : flipHorizontally(arrowImage);

        Timer timer = new Timer(70, null);
        timer.setInitialDelay(0);
        timer.addActionListener(e -> {
            this.distanceTravelled++;
            this.x += Constants.CHARACTER_WIDTH*direction;
            if(!isOutsideGameField()) {
                if (distanceTravelled > distance || CharacterClass.occupiedCells[x][y - yOffset] == -1) {
                    gameField.getFreeObjects().remove(this);
                    timer.stop();
                } else if (CharacterClass.occupiedCells[x][y - yOffset] > 0) {
                    gameField.attack(CharacterClass.occupiedCells[x][y - yOffset], attackAmount);
                }
            } else {
                gameField.getFreeObjects().remove(this);
                timer.stop();
            }
        });
        timer.start();
    }
}
