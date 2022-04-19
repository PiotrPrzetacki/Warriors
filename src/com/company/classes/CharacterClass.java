package com.company.classes;

import com.company.Constants;
import com.company.classes.arenas.Arena;
import com.company.components.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int leftKey, rightKey, upKey, downKey, leftAttackKey,rightAttackKey;
    protected String className;
    private Arena arena;
    private boolean canMove;
    private int moveCooldown;
    private boolean canAttack;
    private int attackCooldown;
    private int attackDistance;
    private Timer fireTimer;
    private HashMap<String, Integer> abilityTimeouts;

    public CharacterClass(
            String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey) {
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
        this.canMove = true;
        this.canAttack = true;

        abilityTimeouts = new HashMap<>();
        abilityTimeouts.put("attack", 0);
        abilityTimeouts.put("move", 0);
    }

    public CharacterClass(String name){
        this.name = name;
    }

    public void setCharacterData(int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey, int rightAttackKey){
        this.x = x;
        this.y = y;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftAttackKey = leftAttackKey;
        this.rightAttackKey = rightAttackKey;
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

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
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

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
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


    public void attack(int direction, CharacterClass[] players) {
        System.out.println(attackCooldown);
        abilityTimeouts.replace("attack", attackCooldown);
        resetTimeout("attack", attackCooldown);
        if(direction==0){
            for(int i=0; i<attackDistance; i++){
                if(CharacterClass.occupiedCells[this.getX() + (Constants.CHARACTER_WIDTH*(i+1))][this.getY()] > 0){
                    players[CharacterClass.occupiedCells[this.getX() + (Constants.CHARACTER_WIDTH*(i+1))][this.getY()] - 1].reduceHealth(this.attackAmount);
                    break;
                }
            }
        }
        else if(direction==1){
            for(int i=0; i<attackDistance; i++){
                if(CharacterClass.occupiedCells[this.getX() - (Constants.CHARACTER_WIDTH*(i+1))][this.getY()] > 0){
                    players[CharacterClass.occupiedCells[this.getX() - (Constants.CHARACTER_WIDTH*(i+1))][this.getY()] - 1].reduceHealth(this.attackAmount);
                    break;
                }
            }
        }
    }

    private void resetTimeout(String ability, int cooldown) {
        new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws InterruptedException {
                int interval = 50;
                for(int i=cooldown; i>=0; i-=interval){
                    abilityTimeouts.replace(ability, i);
                    Thread.sleep(interval-2);
                }
                return null;
            }
        }.execute();
    }

    public boolean getCanAttack() {
        return canAttack;
    }

    @Override
    public void restoreHealth(int amount) {
        setHealthPoints(this.getMaxHealthPoints() + amount);
    }

    @Override
    public void loseHealth(int amount) {
        setHealthPoints(this.getMaxHealthPoints() - amount);
    }

    @Override
    public void restoreMana(int amount) {
        setManaPoints(this.getMaxManaPoints() + amount);
    }

    @Override
    public void loseMana(int amount) {
        setManaPoints(this.getMaxManaPoints() - amount);
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() + 1);
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

    public boolean getCanMove() {
        return canMove;
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
        abilityTimeouts.replace("move", moveCooldown);
        resetTimeout("move", moveCooldown);
        if (occupiedCells[newPositionX][newPositionY] == 0) {
            if (arena.getSpecialSquares()[newPositionX][newPositionY] != -3) {
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
                    canMove = false;
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    if (newX >= 0 && newX <= CharacterClass.occupiedCells[0].length) {
                                        tryChangePosition(newX, y);
                                    }
                                    canMove = true;
                                }
                            }, 200
                    );

                } else if (direction[0] == 1) {
                    this.x = newPositionX;
                    this.y = newPositionY;
                    int newY = newPositionY + (Constants.CHARACTER_HEIGHT * direction[1]);
                    canMove = false;
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    if (newY >= 0 && newY <= CharacterClass.occupiedCells.length) {
                                        tryChangePosition(x, newY);
                                    }
                                    canMove = true;
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

    public abstract void left();

    public abstract void right();

    public abstract void up();

    public abstract void down();

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

    public HashMap<String, Integer> getAbilityTimeouts() {
        return abilityTimeouts;
    }

    public void setAbilityTimeouts(HashMap<String, Integer> abilityTimeouts) {
        this.abilityTimeouts = abilityTimeouts;
    }

    public int getMoveCooldown() {
        return moveCooldown;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setMoveCooldown(int milliseconds) {
        this.moveCooldown = milliseconds;
    }

    public void setAttackCooldown(int milliseconds) {
        this.attackCooldown = milliseconds;
    }
}
