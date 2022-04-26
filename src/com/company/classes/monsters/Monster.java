package com.company.classes.monsters;

import com.company.classes.BaseClass;
import com.company.classes.CharacterClass;
import com.company.components.GameField;

import javax.swing.*;
import java.awt.*;

import static com.company.utils.ResourceLoader.load;

public abstract class Monster implements BaseClass {

    public static int monstersCount = 0;
    private int x;
    private int y;
    private Image baseImage, attackLeftImage, attackRightImage, currentImage;
    private int attackAmount;
    private int movementSpeed;
    private int healthPoints;
    private final int number;

    public Monster() {
        monstersCount++;
        number = monstersCount+100;

    }

    public void uploadImage(String baseImage, String attackLeftImage, String attackRightImage) {
        this.baseImage = new ImageIcon(load(baseImage)).getImage();
        this.attackLeftImage = new ImageIcon(load(attackLeftImage)).getImage();
        this.attackRightImage = new ImageIcon(load(attackRightImage)).getImage();
        this.currentImage = this.baseImage;
    }

    public void setAttackAmount(int attackAmount) {
        this.attackAmount = attackAmount;
    }

    public int getAttackAmount() {
        return attackAmount;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber() {
        return number;
    }

    public abstract void killMonster();

    @Override
    public void attack(int direction, CharacterClass[] players, GameField gameField) {

    }

    @Override
    public void reduceHealth(int amount) {
        healthPoints -= amount;
        if(healthPoints<0){
            killMonster();
        }
    }

    @Override
    public void info() {
        System.out.println("Monster{" +
                "x=" + x +
                ", y=" + y +
                ", number=" + number +
                '}');
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public void setAttackLeftImage() {
        this.currentImage = attackLeftImage;
    }

    public void setAttackRightImage() {
        this.currentImage = attackRightImage;
    }

    public void setBaseImage() {
        this.currentImage = baseImage;
    }
}
