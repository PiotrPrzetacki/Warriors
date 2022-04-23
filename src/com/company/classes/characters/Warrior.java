package com.company.classes.characters;

import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Warrior extends CharacterClass {
    public Warrior(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey);
        this.setAttackAmount(65);
        this.setMaxHealthPoints(1000);
        this.setHealthPoints(1000);
        this.className = "Warior";
        this.setAttackDistance(1);
        this.setAbilityCooldown(Abilities.ATTACK, 200);
        this.setAbilityCooldown(Abilities.MOVE, 140);
        this.setAttackType(AttackType.PHYSICAL);
        this.setName(name);
        this.uploadImage("/images/characters/warrior/WarriorBaseImage.png",
                "/images/characters/warrior/WarriorAttackLeftImage.png",
                "/images/characters/warrior/WarriorAttackRightImage.png");
    }

}
