package com.company.classes.characters;

import com.company.Constants;
import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Mage  extends CharacterClass {

    public Mage(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);
        this.setAttackAmount(50);
        setHealthPoints(500);
        this.className = "Mage";
        this.setAttackDistance(3);
        /*this.setLevel(1);
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(1000);
        this.setManaPoints(200);
        this.setMaxManaPoints(200);
        this.setAttackType(AttackType.PHYSICAL);
        this.setAttackAmount(5);
        this.setName(name);

        this.setX(300);
        this.setY(0);
        this.uploadImage("WarriorBaseImage.png", "WarriorAttackRightImage.png", "WarriorAttackLeftImage.png");*/
    }
    public void left() {
        int newPositionX = this.getX() > Constants.CHARACTER_WIDTH ?  this.getX() - Constants.CHARACTER_WIDTH : 320;
    }
    public void right() {
        int newPositionX = this.getX() < 320 ?  this.getX() + Constants.CHARACTER_WIDTH : 0;

    }
    public void up() {
        int newPositionY = this.getY() > Constants.CHARACTER_HEIGHT ?  this.getY() - Constants.CHARACTER_HEIGHT : 320;
    }
    public void down() {
        int newPositionY = this.getY() < 320 ?  this.getY() + Constants.CHARACTER_HEIGHT : 0;
    }
}
