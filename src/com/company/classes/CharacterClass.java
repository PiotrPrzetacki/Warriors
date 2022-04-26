package com.company.classes;

import com.company.Constants;
import com.company.classes.arenas.Arena;
import com.company.classes.characters.Abilities;
import com.company.classes.objects.Blood;
import com.company.components.GameField;
import com.company.utils.PausableSwingWorker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static com.company.utils.ResourceLoader.load;

public abstract class CharacterClass implements BaseClass {
    public static int[][] occupiedCells = new int[Constants.WINDOW_WIDTH][Constants.WINDOW_HEIGHT];
    public static int playerCount = 0;
    private int number;
    private int healthPoints = 200;
    private int manaPoints;
    private int level;
    private AttackType attackType;
    protected int attackAmount;
    private String name;
    private int maxHealthPoints;
    private int maxManaPoints;
    private int leftKey, rightKey, upKey, downKey, leftAttackKey,rightAttackKey, specialAbilityKey;
    protected String className;
    private Arena arena;
    private int attackDistance;
    private Timer fireTimer;
    private HashMap<Abilities, int[]> abilityTimeouts;
    private List<SwingWorker> workers;

    public CharacterClass(
            String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey, int specialAbilityKey) {
        this.number = ++playerCount;
        occupiedCells[x][y] = this.number;
        this.name = name;
        this.x = x;
        this.y = y;
        this.leftKey =leftKey;
        this.rightKey= rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftAttackKey = leftAttackKey;
        this.rightAttackKey = rightAttackKey;
        this.specialAbilityKey = specialAbilityKey;

        workers = new ArrayList<>();
        abilityTimeouts = new HashMap<>();
        abilityTimeouts.put(Abilities.ATTACK, new int[]{0, 200});
        abilityTimeouts.put(Abilities.MOVE, new int[]{0, 200});
    }

    public CharacterClass(String name){
        this.name = name;
    }

    public abstract void useSpecialAbility(GameField gameField);

    public void setCharacterData(int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey, int specialAbilityKey){
        this.x = x;
        this.y = y;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftAttackKey = leftAttackKey;
        this.rightAttackKey = rightAttackKey;
        this.specialAbilityKey = specialAbilityKey;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            this.healthPoints = 0;
        } else if (healthPoints > this.maxHealthPoints) {
            this.healthPoints = this.maxHealthPoints;
        }
        else {
            this.healthPoints = healthPoints;
        }
    }

    public void setManaPoints(int manaPoints) {
        if (manaPoints < 0) {
            this.manaPoints = 0;
        } else if (manaPoints > this.maxManaPoints) {
            this.manaPoints = this.maxManaPoints;
        }
        else {
            this.manaPoints = manaPoints;
        }
    }

    public void setLevel(int level) {
        if (level < 1) {
            System.out.println("We can't lose level");
        } else {
            this.level = level;
        }
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public void setAttackAmount(int attackAmount) {
        this.attackAmount = attackAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public void setMaxManaPoints(int maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getLevel() {
        return level;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public int getAttackAmount() {
        return attackAmount;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public int getMaxManaPoints() {
        return maxManaPoints;
    }


    public void attack(int direction, CharacterClass[] players, GameField gameField) {
        resetAbilityTimeout(Abilities.ATTACK);
        reduceAbilityTimeout(Abilities.ATTACK);
        if(direction==1){
            for(int i=0; i<attackDistance; i++){
                try {
                    if(CharacterClass.occupiedCells[this.getX() + (Constants.CHARACTER_WIDTH*(i+1))][this.getY()] > 0){

                        gameField.attack(CharacterClass.occupiedCells[this.getX() + (Constants.CHARACTER_WIDTH * (i + 1))][this.getY()], getAttackAmount());
                    }
                    break;
                }catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
        else if(direction==-1){
            for(int i=0; i<attackDistance; i++){
                try {
                    if (CharacterClass.occupiedCells[this.getX() - (Constants.CHARACTER_WIDTH * (i + 1))][this.getY()] > 0) {
                        gameField.attack(CharacterClass.occupiedCells[this.getX() - (Constants.CHARACTER_WIDTH * (i + 1))][this.getY()], getAttackAmount());
                        break;
                    }
                }catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
    }

    protected void reduceAbilityTimeout(Abilities ability) {
        SwingWorker<Void, Void> worker = new PausableSwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws InterruptedException {

                int interval = 20;
                for(int i=abilityTimeouts.get(ability)[1]; i>=-100; i-=interval) {
                    abilityTimeouts.get(ability)[0] = Math.max(i, 0);
                    Thread.sleep(interval - 1);
                }
                return null;
            }
        };
        workers.add(worker);
        worker.execute();
    }
    protected void resetAbilityTimeout(Abilities ability){
        if(abilityTimeouts.containsKey(ability)) {
            abilityTimeouts.get(ability)[0] = abilityTimeouts.get(ability)[1];
        }
    }

    @Override
    public void info() {
        System.out.println("Name: " + this.name + "\nCurrentHP: " + this.healthPoints + "\nCurrentmana: " + this.maxManaPoints + "\nLevel: " + this.level);
    }

    private Image image, baseImage, attackLeftImage, attackRightImage;
    private int x, y;

    public void setImage(Image image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void uploadImage(String baseImage, String attackLeftImage, String attackRightImage) {
        this.baseImage = new ImageIcon(load(baseImage)).getImage();
        this.attackLeftImage = new ImageIcon(load(attackLeftImage)).getImage();
        this.attackRightImage = new ImageIcon(load(attackRightImage)).getImage();
        setBaseImage();
    }

    public void setBaseImage() {
        this.image = this.baseImage;
    }

    public void setAttackLeftImage() {
        this.image = this.attackLeftImage;
    }

    public void setAttackRightImage() {
        this.image = this.attackRightImage;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int playerCount) {
        CharacterClass.playerCount = playerCount;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public int getUpKey() {
        return upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public int getLeftAttackKey() {
        return leftAttackKey;
    }

    public int getRightAttackKey() {
        return rightAttackKey;
    }

    public void tryChangePosition(int newPositionX, int newPositionY) {
        tryChangePosition(newPositionX, newPositionY, true);
    }

    public void tryChangePosition(int newPositionX, int newPositionY, boolean takeDamage) {

        if (occupiedCells[newPositionX][newPositionY] == 0) {
            if (arena.getSpecialSquares()[newPositionX][newPositionY] != -3) {
                resetAbilityTimeout(Abilities.MOVE);
                reduceAbilityTimeout(Abilities.MOVE);
                occupiedCells[this.x][this.y] = 0;
                occupiedCells[newPositionX][newPositionY] = this.number;
                this.x = newPositionX;
                this.y = newPositionY;
            } else {
                occupiedCells[this.x][this.y] = 0;
                occupiedCells[newPositionX][newPositionY] = this.number;
                int[] direction = getPlayerDirection(x, y, newPositionX, newPositionY);
                if (direction[0] == 0) {
                    this.x = newPositionX;
                    this.y = newPositionY;
                    int newX = newPositionX + (Constants.CHARACTER_WIDTH * direction[1]);
                    abilityTimeouts.get(Abilities.MOVE)[0] = 100;
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    if (newX >= 0 && newX <= CharacterClass.occupiedCells.length) {
                                        tryChangePosition(newX, y);
                                    }
                                    abilityTimeouts.get(Abilities.MOVE)[0] = abilityTimeouts.get(Abilities.MOVE)[1];
                                }
                            }, 200
                    );

                } else if (direction[0] == 1) {
                    this.x = newPositionX;
                    this.y = newPositionY;
                    int newY = newPositionY + (Constants.CHARACTER_HEIGHT * direction[1]);
                    abilityTimeouts.get(Abilities.MOVE)[0] = 100;
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    if (newY >= 0 && newY <= CharacterClass.occupiedCells[0].length) {
                                        tryChangePosition(x, newY);
                                    }
                                    abilityTimeouts.get(Abilities.MOVE)[0] = abilityTimeouts.get(Abilities.MOVE)[1];
                                }
                            }, 200
                    );

                }
            }
            if (arena.getSpecialSquares()[newPositionX][newPositionY] == -2) {
                if(fireTimer==null) {
                    fireTimer = new Timer(500, null);
                    fireTimer.addActionListener(e -> {
                        if (arena.getSpecialSquares()[newPositionX][newPositionY] == -2 &&
                                CharacterClass.occupiedCells[newPositionX][newPositionY] == this.number) {
                            reduceHealth(50);
                        } else {
                            fireTimer.setRepeats(false);
                            fireTimer.stop();
                            fireTimer = null;
                        }
                    });
                    fireTimer.setInitialDelay(0);
                    fireTimer.setRepeats(true);
                    fireTimer.start();
                }
            }
        } else if(takeDamage){
            reduceHealth(50);
        }

    }

    public void reduceHealth(int amount) {
        setHealthPoints(this.getHealthPoints() - amount);
    }

    public void left() {
        int newPositionX = this.getX() > Constants.CHARACTER_WIDTH ?  this.getX() - Constants.CHARACTER_WIDTH : 0;
        tryChangePosition(newPositionX, this.getY());
    }
    public void right() {
        int newPositionX = this.getX() < (Constants.WINDOW_WIDTH-Constants.CHARACTER_WIDTH) ?  this.getX() + Constants.CHARACTER_WIDTH : (Constants.WINDOW_WIDTH-Constants.CHARACTER_WIDTH);
        tryChangePosition(newPositionX, this.getY());
    }
    public void up() {
        int newPositionY = this.getY() >= Constants.CHARACTER_HEIGHT ?  this.getY() - Constants.CHARACTER_HEIGHT : 0;
        tryChangePosition(this.getX(), newPositionY);
    }
    public void down() {
        int newPositionY = this.getY()+Constants.CHARACTER_HEIGHT+40 < (Constants.WINDOW_HEIGHT) ?  this.getY() + Constants.CHARACTER_HEIGHT : (Constants.WINDOW_HEIGHT-Constants.CHARACTER_HEIGHT-40);
        tryChangePosition(this.getX(), newPositionY);
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public void setAttackDistance(int attackDistance) {
        this.attackDistance = attackDistance;
    }

    public static void resetOccupiedCells(){
        for(int i=0; i<CharacterClass.occupiedCells.length; i++){
            for(int j=0; j<CharacterClass.occupiedCells[0].length; j++){
                CharacterClass.occupiedCells[i][j] = 0;
            }
        }
    }

    @Override
    public String toString() {
        return "CharacterClass{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    private int[] getPlayerDirection(int oldX, int oldY, int newX, int newY){
        if(oldY-newY < 0) return new int[]{1, 1};
        else if(oldY-newY > 0) return new int[]{1, -1};
        else {
            if(oldX-newX < 0) return new int[]{0, 1};
            else if(oldX-newX > 0) return new int[]{0, -1};
            else return new int[]{0, 0};
        }
    }

    public HashMap<Abilities, int[]> getAbilityTimeouts() {
        return abilityTimeouts;
    }

    public void teleportLeft() {
        int newPositionX;
        if(this.getX() >= Constants.CHARACTER_WIDTH){
            newPositionX = this.getX() - Constants.CHARACTER_WIDTH;
            tryChangePosition(newPositionX, this.getY());
        } else {
            if(getAbilityTimeouts().get(Abilities.TELEPORT)[0] == 0) {
                newPositionX = Constants.WINDOW_WIDTH - Constants.CHARACTER_WIDTH;
                resetAbilityTimeout(Abilities.TELEPORT);
                reduceAbilityTimeout(Abilities.TELEPORT);
                tryChangePosition(newPositionX, this.getY());
            }
            else{
                newPositionX = this.getX() > Constants.CHARACTER_WIDTH ?  this.getX() - Constants.CHARACTER_WIDTH : 0;
                tryChangePosition(newPositionX, this.getY());
            }
        }
    }
    public void teleportRight() {

        int newPositionX;
        if(this.getX() < (Constants.WINDOW_WIDTH - Constants.CHARACTER_WIDTH)){
            newPositionX = this.getX() + Constants.CHARACTER_WIDTH;
            tryChangePosition(newPositionX, this.getY());
        } else {
            if(getAbilityTimeouts().get(Abilities.TELEPORT)[0] == 0) {
                newPositionX = 0;
                resetAbilityTimeout(Abilities.TELEPORT);
                reduceAbilityTimeout(Abilities.TELEPORT);
                tryChangePosition(newPositionX, this.getY());
            }
            else{
                newPositionX = this.getX() < (Constants.WINDOW_WIDTH-Constants.CHARACTER_WIDTH) ?  this.getX() + Constants.CHARACTER_WIDTH : (Constants.WINDOW_WIDTH-Constants.CHARACTER_WIDTH);
                tryChangePosition(newPositionX, this.getY());
            }
        }
    }
    public void teleportUp() {

        int newPositionY;
        if(this.getY() >= Constants.CHARACTER_HEIGHT){
            newPositionY = this.getY() - Constants.CHARACTER_HEIGHT;
            tryChangePosition(this.getX(), newPositionY);
        } else {
            if(getAbilityTimeouts().get(Abilities.TELEPORT)[0] == 0) {
                newPositionY = (Constants.WINDOW_HEIGHT - Constants.CHARACTER_HEIGHT - 40);
                resetAbilityTimeout(Abilities.TELEPORT);
                reduceAbilityTimeout(Abilities.TELEPORT);
                tryChangePosition(this.getX(), newPositionY);
            }
            else{
                newPositionY = this.getY() >= Constants.CHARACTER_HEIGHT ?  this.getY() - Constants.CHARACTER_HEIGHT : 0;
                tryChangePosition(this.getX(), newPositionY);
            }
        }
    }
    public void teleportDown() {

        int newPositionY;
        if(this.getY() < (Constants.WINDOW_HEIGHT - Constants.CHARACTER_HEIGHT - 40)){
            newPositionY = this.getY() + Constants.CHARACTER_HEIGHT;
            tryChangePosition(this.getX(), newPositionY);
        } else {
            if(getAbilityTimeouts().get(Abilities.TELEPORT)[0] == 0) {
                newPositionY = 0;
                resetAbilityTimeout(Abilities.TELEPORT);
                reduceAbilityTimeout(Abilities.TELEPORT);
                tryChangePosition(this.getX(), newPositionY);
            }
            else{
                newPositionY = this.getY()+Constants.CHARACTER_HEIGHT+40 < (Constants.WINDOW_HEIGHT) ?  this.getY() + Constants.CHARACTER_HEIGHT : (Constants.WINDOW_HEIGHT-Constants.CHARACTER_HEIGHT-40);
                tryChangePosition(this.getX(), newPositionY);
            }
        }
    }

    public void setAbilityCooldown(Abilities ability, int cooldown){
        if(abilityTimeouts.containsKey(ability)) {
            abilityTimeouts.get(ability)[1] = cooldown;
        }
    }

    public int getSpecialAbilityKey() {
        return specialAbilityKey;
    }

    public int getNumber() {
        return number;
    }

    public List<SwingWorker> getWorkers() {
        return workers;
    }
}
