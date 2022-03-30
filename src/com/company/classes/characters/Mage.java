package com.company.classes.characters;

import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Mage  extends CharacterClass {

    public Mage(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);
        this.setAttackAmount(50);
        setHealthPoints(500);
        this.className = "Mage";
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
        this.uploadImage("1.png", "2.png", "3.png");*/
    }
    public void left() {
        int newPositionX = this.getX() > 40 ?  this.getX() - 40 : 320;
    }
    public void right() {
        int newPositionX = this.getX() < 320 ?  this.getX() + 40 : 0;

    }
    public void up() {
        int newPositionY = this.getY() > 80 ?  this.getY() - 80 : 320;
    }
    public void down() {
        int newPositionY = this.getY() < 320 ?  this.getY() + 80 : 0;
    }
}
