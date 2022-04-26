package com.company.classes.monsters;

import com.company.Constants;
import com.company.classes.CharacterClass;
import com.company.components.MonstersAttackGameField;

import java.util.Random;

import static com.company.Constants.*;


public class MonstersController {

    private MonstersAttackGameField gameField;

    public MonstersController(MonstersAttackGameField gameField){
        this.gameField = gameField;
        this.gameField.setWave(1);
        this.gameField.setPoints(0);
        this.gameField.getMonsters().clear();
        startNextWave(gameField.getWave());
    }

    public void startNextWave(int wave){
        System.gc();
        for(int i=1; i<=wave; i++){
            int[] newPos = getMonsterSpawn();
            gameField.getMonsters().add(new Skeleton(newPos[0], newPos[1], wave, gameField));
        }
    }

    public int[] getMonsterSpawn() {
        Random rnd = new Random();
        int x, y;
        int spawnPoint = rnd.ints(0, 5).findFirst().getAsInt();
        switch (spawnPoint){
            default:
            case 1:
                x = 400;
                y = -80*rnd.ints(1, 4).findFirst().getAsInt();
                break;
            case 2:
                x = 800+40*rnd.ints(0, 3).findFirst().getAsInt();
                y = 320;
                break;
            case 3:
                x = 400;
                y = 600+80*rnd.ints(0, 3).findFirst().getAsInt();
                break;
            case 4:
                x = -40*rnd.ints(1, 4).findFirst().getAsInt();
                y = 320;
                break;
        }
        return new int[] {x, y};
    }

    private boolean isOutsideGameField(int x, int y){
        return x < 0 || x >= WINDOW_WIDTH || y < 0 || y >= WINDOW_HEIGHT;
    }
}
