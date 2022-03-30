package com.company.classes.characters;

import com.company.Constants;
import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Archer  extends CharacterClass {
    public Archer(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);
        this.setAttackAmount(50);
        this.setMaxHealthPoints(1000);
        setHealthPoints(500);
        this.className = "Archer";
        /*this.setLevel(1);
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(1000);
        this.setManaPoints(200);
        this.setMaxManaPoints(200);
        this.setAttackType(AttackType.PHYSICAL);
        this.setAttackAmount(5);
        this.setName(name);*/

        /*this.setX(300);
        this.setY(300);*/
        this.uploadImage("5.png", "7.png", "6.png");


    }

    public void left() {
        int newPositionX = this.getX() >= Constants.CHARACTER_WIDTH ?  this.getX() - Constants.CHARACTER_WIDTH : 320;
        tryChangePosition(newPositionX, this.getY());
    }
    public void right() {
        int newPositionX = this.getX() < 320 ?  this.getX() + Constants.CHARACTER_WIDTH : 0;
        tryChangePosition(newPositionX, this.getY());

    }
    public void up() {
        int newPositionY = this.getY() >= Constants.CHARACTER_HEIGHT ?  this.getY() - Constants.CHARACTER_HEIGHT : 320;
        tryChangePosition(this.getX(), newPositionY);
    }
    public void down() {
        int newPositionY = this.getY() < 320 ?  this.getY() + Constants.CHARACTER_HEIGHT : 0;
        tryChangePosition(this.getX(), newPositionY);
    }
}
