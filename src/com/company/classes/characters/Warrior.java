package com.company.classes.characters;

import com.company.Constants;
import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Warrior extends CharacterClass {
    public Warrior(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);
        this.setAttackAmount(100);
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(1000);
        this.className = "Warior";
        this.setAttackDistance(1);
        this.setAttackType(AttackType.PHYSICAL);
        this.setName(name);
        this.uploadImage("/images/characters/WarriorBaseImage.png",
                "/images/characters/WarriorAttackLeftImage.png",
                "/images/characters/WarriorAttackRightImage.png");
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
