package com.company.classes.monsters;

import com.company.Constants;
import com.company.classes.CharacterClass;
import com.company.components.MonstersAttackGameField;
import com.company.utils.Node;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.company.utils.Pathfinder.getNextSquare;

public class Skeleton extends Monster{

    private final ScheduledExecutorService executorService;
    private MonstersAttackGameField gameField;
    public Skeleton(int x, int y, int level, MonstersAttackGameField gameField){
        super();
        this.gameField = gameField;
        setX(x);
        setY(y);
        uploadImage("/images/characters/monsters/skeleton/skeleton-base.gif",
                "/images/characters/monsters/skeleton/skeleton-attack-left.gif",
                "/images/characters/monsters/skeleton/skeleton-attack-right.gif");
//        uploadImage("images/characters/warrior/WarriorBaseImage.png",
//                "/images/characters/warrior/WarriorBaseImage.png",
//                "images/characters/warrior/WarriorBaseImage.png");
        setAttackAmount((60 + (level*10) ));
        setMovementSpeed(800 + (level*10) );
        setHealthPoints(400 + (level*10));

        int initialDelay = new Random().ints(2800, 3300).findFirst().getAsInt();
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::performAction, initialDelay, getMovementSpeed(), TimeUnit.MILLISECONDS);
    }

    private void performAction(){
        try {
            if (CharacterClass.occupiedCells[getX() + 40][getY()] > 0 && CharacterClass.occupiedCells[getX() + 40][getY()] < 100) {
                setAttackRightImage();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setBaseImage();
                    }
                }, 300);
                gameField.attack(CharacterClass.occupiedCells[getX() + 40][getY()], getAttackAmount());
                return;
            }
        } catch (ArrayIndexOutOfBoundsException ignored){}
        try {
            if (CharacterClass.occupiedCells[getX() - 40][getY()] > 0 && CharacterClass.occupiedCells[getX() - 40][getY()] < 100) {
                setAttackLeftImage();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setBaseImage();
                    }
                }, 150);
                gameField.attack(CharacterClass.occupiedCells[getX() - 40][getY()], getAttackAmount());
                return;
            }
        } catch (ArrayIndexOutOfBoundsException ignored){}

        move();
    }



    @Override
    public void killMonster() {
        if(!isOutsideGameField()) {
            CharacterClass.occupiedCells[getX()][getY()] = 0;
        }
        gameField.getMonsters().remove(this);
        executorService.shutdown();
    }

    private void move(){
        int[] path = getNextSquare(new int[]{getX(), getY()}, getTarget());
        if(!isOutsideGameField()) {
            CharacterClass.occupiedCells[getX()][getY()] = 0;
        }
        this.setX(path[0]);
        this.setY(path[1]);
        if(!isOutsideGameField()){
            CharacterClass.occupiedCells[getX()][getY()] = getNumber();
        }
    }

    private int[] getTarget(){
        double distanceToNearestPlayer = Double.MAX_VALUE;
        int[] target = new int[] {-1, -1};
        for (int i=0; i<CharacterClass.occupiedCells.length; i++){
            for (int j=0; j<CharacterClass.occupiedCells[0].length; j++){
                if(CharacterClass.occupiedCells[i][j] > 0 && CharacterClass.occupiedCells[i][j] < 100){
                    double currentDistance = Math.sqrt( (i-getX())*(i-getX()) + (j-getY())*(j-getY()) );
                    if(currentDistance<distanceToNearestPlayer){
                        distanceToNearestPlayer = currentDistance;
                        target[0] = i;
                        target[1] = j;
                    }
                }
            }
        }
        if(target[0]==-1){
            target[0] = getX();
            target[1] = getY();
        }
        return target;
    }

    private boolean isOutsideGameField(){
        return getX() < 0 || getX() >= Constants.WINDOW_WIDTH || getY() < 0 || getY() >= Constants.WINDOW_HEIGHT;
    }
}
