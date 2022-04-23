package com.company.classes.characters;

import com.company.classes.CharacterClass;
import com.company.classes.objects.Arrow;
import com.company.components.GameField;

import javax.swing.*;

public class Archer  extends CharacterClass {

    private int longShotsDuration = 5_000;
    private int longShotsDistance = 5;
    private final int normalDistance = 2;

    public Archer(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey, int specialAbilityKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, specialAbilityKey);
        this.setAttackAmount(80);
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(1000);
        this.setAbilityCooldown(Abilities.ATTACK, 250);
        this.setAbilityCooldown(Abilities.MOVE, 210);
        this.className = "Archer";
        this.setAttackDistance(normalDistance);
        this.uploadImage("/images/characters/archer/ArcherBaseImage.png",
                "/images/characters/archer/ArcherAttackLeftImage.png",
                "/images/characters/archer/ArcherAttackRightImage.png");

        getAbilityTimeouts().put(Abilities.TELEPORT, new int[]{0, 6000});
        getAbilityTimeouts().put(Abilities.LONG_SHOTS, new int[]{0, 15_000});

    }

    @Override
    public void useSpecialAbility(GameField gameField) {
        int[] specialAbility = getAbilityTimeouts().get(Abilities.LONG_SHOTS);
        if(specialAbility[0] == 0){
            specialAbility[0] = -1;
            setAttackDistance(longShotsDistance);
            Timer timer = new Timer(longShotsDuration, e -> {
                setAttackDistance(normalDistance);
                resetAbilityTimeout(Abilities.LONG_SHOTS);
                reduceAbilityTimeout(Abilities.LONG_SHOTS);
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    @Override
    public void left() {
        teleportLeft();
    }
    @Override
    public void right() {
        teleportRight();
    }
    @Override
    public void up() {
        teleportUp();
    }

    @Override
    public void down() {
        teleportDown();
    }

    @Override
    public void attack(int direction, CharacterClass[] players, GameField gameField){
        //super.attack(direction, players, gameField);
        resetAbilityTimeout(Abilities.ATTACK);
        reduceAbilityTimeout(Abilities.ATTACK);
        gameField.getFreeObjects().add(new Arrow(this, direction, gameField));
    }
}
