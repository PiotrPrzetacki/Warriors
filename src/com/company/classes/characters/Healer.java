package com.company.classes.characters;

import com.company.classes.CharacterClass;

public class Healer  extends CharacterClass {
    public Healer(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey, int specialAbilityKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, specialAbilityKey);
        this.setAttackAmount(75);
        this.className = "Healer";
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(1000);
        this.setAttackDistance(2);
        this.setName(name);
        this.setAbilityCooldown(Abilities.ATTACK, 235);
        this.setAbilityCooldown(Abilities.MOVE, 200);
        this.setAttackDistance(2);
        this.uploadImage("/images/characters/healer/HealerBase.png",
                "/images/characters/healer/HealerAttackLeft.png",
                "/images/characters/healer/HealerAttackRight.png");

        getAbilityTimeouts().put(Abilities.TELEPORT, new int[]{0, 5000});
    }

    @Override
    public void useSpecialAbility() {

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
