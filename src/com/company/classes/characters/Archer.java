package com.company.classes.characters;

import com.company.classes.CharacterClass;

public class Archer  extends CharacterClass {
    public Archer(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);
        this.setAttackAmount(80);
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(1000);
        this.setAbilityCooldown(Abilities.ATTACK, 250);
        this.setAbilityCooldown(Abilities.MOVE, 210);
        this.className = "Archer";
        this.setAttackDistance(2);
        this.uploadImage("/images/characters/archer/ArcherBaseImage.png",
                "/images/characters/archer/ArcherAttackLeftImage.png",
                "/images/characters/archer/ArcherAttackRightImage.png");

        getAbilityTimeouts().put(Abilities.TELEPORT, new int[]{0, 6000});

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
}
