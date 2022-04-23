package com.company.classes.characters;

import com.company.classes.AttackType;
import com.company.classes.CharacterClass;
import com.company.classes.objects.Fireball;
import com.company.components.GameField;

public class Warrior extends CharacterClass {
    public Warrior(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey, int specialAbilityKey) {
        super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, specialAbilityKey);
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
        this.getAbilityTimeouts().put(Abilities.THROW_FIREBALL, new int[]{0, 18_000});
    }

    @Override
    public void useSpecialAbility(GameField gameField) {
        int[] target = getNearestEnemy();
        int[] specialAbility = getAbilityTimeouts().get(Abilities.THROW_FIREBALL);
        if(specialAbility[0] == 0){
            gameField.getFreeObjects().add(new Fireball(getX()+20, getY()+40, target[0]+20, target[1]+40, gameField, this));
            resetAbilityTimeout(Abilities.THROW_FIREBALL);
            reduceAbilityTimeout(Abilities.THROW_FIREBALL);
        }
    }

    private int[] getNearestEnemy(){
        double distanceToNearestPlayer = Double.MAX_VALUE;
        int[] target = new int[] {-1, -1};
        for (int i=0; i<CharacterClass.occupiedCells.length; i++){
            for (int j=0; j<CharacterClass.occupiedCells[0].length; j++){
                int a = (i-getX())*(i-getX());
                int b = (j-getY())*(j-getY());
                double currentDistance = Math.sqrt(a + b);
                if(CharacterClass.occupiedCells[i][j] > 0 && CharacterClass.occupiedCells[i][j] != getNumber()){
                    if(currentDistance<distanceToNearestPlayer){
                        distanceToNearestPlayer = currentDistance;
                        target[0] = i;
                        target[1] = j;
                    }
                }
            }
        }
        if(target[0]==-1){
            target[0] = getX()+1;
            target[1] = getY();
        }
        return target;
    }
}
