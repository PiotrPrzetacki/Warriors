package com.company.classes.characters;

import com.company.Constants;
import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Mage  extends CharacterClass {

    public Mage(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);
        this.setAttackAmount(50);
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(500);
        this.setAttackCooldown(5000);
        this.setMoveCooldown(200);
        this.className = "Mage";
        this.setAttackDistance(3);
        this.uploadImage("/images/characters/MageBase.png",
                "/images/characters/MageAttackLeft.png",
                "/images/characters/MageAttackRight.png");
    }
    public void left() {
        int newPositionX = this.getX() > Constants.CHARACTER_WIDTH ?  this.getX() - Constants.CHARACTER_WIDTH : 0;
        tryChangePosition(newPositionX, this.getY());
    }
    public void right() {
        int newPositionX = this.getX() < (Constants.WINDOW_WIDTH-Constants.CHARACTER_WIDTH) ?  this.getX() + Constants.CHARACTER_WIDTH : (Constants.WINDOW_WIDTH-Constants.CHARACTER_WIDTH);
        tryChangePosition(newPositionX, this.getY());
    }
    public void up() {
        int newPositionY = this.getY() >= Constants.CHARACTER_HEIGHT ?  this.getY() - Constants.CHARACTER_HEIGHT : 0;
        tryChangePosition(this.getX(), newPositionY);
    }
    public void down() {
        int newPositionY = this.getY()+Constants.CHARACTER_HEIGHT+40 < (Constants.WINDOW_HEIGHT) ?  this.getY() + Constants.CHARACTER_HEIGHT : (Constants.WINDOW_HEIGHT-Constants.CHARACTER_HEIGHT-40);

        tryChangePosition(this.getX(), newPositionY);
    }
}
