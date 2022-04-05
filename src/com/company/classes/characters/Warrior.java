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
        this.setLevel(1);
        this.setManaPoints(200);
        this.setMaxManaPoints(200);
        this.setAttackType(AttackType.PHYSICAL);
        this.setName(name);
        this.setX(0);
        this.setY(0);
        this.uploadImage("assets/images/WarriorBaseImage.png", "assets/images/WarriorAttackLeftImage.png", "assets/images/WarriorAttackRightImage.png");
    }

    public void left() {
       int newPositionX = this.getX() > Constants.CHARACTER_WIDTH ?  this.getX() - Constants.CHARACTER_WIDTH : 0;
       tryChangePosition(newPositionX, this.getY());
    }
    public void right() {
        int newPositionX = this.getX() < 320 ?  this.getX() + Constants.CHARACTER_WIDTH : 320;
        tryChangePosition(newPositionX, this.getY());
    }
    public void up() {
        int newPositionY = this.getY() >= Constants.CHARACTER_HEIGHT ?  this.getY() - Constants.CHARACTER_HEIGHT : 0;
        System.out.println(newPositionY);
        tryChangePosition(this.getX(), newPositionY);
    }
    public void down() {
        System.out.println("before button" + getY());
        int newPositionY = this.getY() < 320 ?  this.getY() + Constants.CHARACTER_HEIGHT : 320;

        tryChangePosition(this.getX(), newPositionY);
        System.out.println("after button" + newPositionY);
    }

}
