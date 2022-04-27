package com.company.classes.monsters;

import com.company.Constants;
import com.company.classes.CharacterClass;
import com.company.components.MonstersAttackGameField;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        int[] path = getNextSquare(getTarget());
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

    private int[] getNextSquare(int[] target){
        int[] src = new int[] {getX()/Constants.CHARACTER_WIDTH, getY()/Constants.CHARACTER_HEIGHT};
        int[] dest = new int[] {target[0]/Constants.CHARACTER_WIDTH, target[1]/Constants.CHARACTER_HEIGHT};
        int[] res = aStarSearch(src, dest);
        if(res != null) {
            return new int[]{res[0] * Constants.CHARACTER_WIDTH, res[1] * Constants.CHARACTER_HEIGHT};
        }
        else{
            return new int[]{getX(), getY()};
        }
    }

    private boolean isOutsideGameField(){
        return getX() < 0 || getX() >= Constants.WINDOW_WIDTH || getY() < 0 || getY() >= Constants.WINDOW_HEIGHT;
    }

    private List<Node> traceBack(Node lastNode){
        List<Node> res = new ArrayList<>();
        res.add(lastNode);
        while (lastNode.parent != null){
            lastNode = lastNode.parent;
            res.add(lastNode);
        }
        return res;
    }

    private int[] aStarSearch(int[] src, int[] dest)
    {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        openList.add(new Node(src[0], src[1], 0, null));

        if(src[0]==dest[0] && src[1]==dest[1]){
            return null;
        }

        while (!openList.isEmpty()) {
            Node q = openList.get(0);
            for (Node node : openList) {
                if (node.f < q.f) {
                    q = node;
                }
            }
            openList.remove(q);
            List<Node> successors = new ArrayList<>();
            successors.add(new Node(q.x, q.y - 1, 0, q));
            successors.add(new Node(q.x, q.y + 1, 0, q));
            successors.add(new Node(q.x - 1, q.y, 0, q));
            successors.add(new Node(q.x + 1, q.y, 0, q));
            Collections.shuffle(successors);
            s:
            for (Node successor : successors) {
                if (successor.x == dest[0] && successor.y == dest[1]) {
                    List<Node> path = traceBack(successor);
                    if(path.size()>2) {
                        Node nextNode = path.get(path.size() - 2);
                        return new int[]{nextNode.x, nextNode.y};
                    }else{
                        return null;
                    }
                } else {
                    successor.g = q.g + 1;
                    successor.h = Math.abs(successor.x - dest[0]) + Math.abs(successor.y - dest[1]);
                    successor.f = successor.g + successor.h;
                }
                for (Node node : openList) {
                    if (node.x == successor.x && node.y == successor.y && node.f < successor.f) {
                        continue s;
                    }
                }
                for (Node node : closedList) {
                    if (node.x == successor.x && node.y == successor.y && node.f < successor.f) {
                        continue s;
                    }
                }
                try {
                    if (CharacterClass.occupiedCells[successor.x * Constants.CHARACTER_WIDTH][successor.y * Constants.CHARACTER_HEIGHT] == -1 ||
                            CharacterClass.occupiedCells[successor.x * Constants.CHARACTER_WIDTH][successor.y * Constants.CHARACTER_HEIGHT] > 0) {
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
                openList.add(successor);
            }
            closedList.add(q);
        }
        return null;
    }

    private static class Node{
        public int x, y;
        public double f, g, h;
        public Node parent;

        public Node(int x, int y, double f, Node parent) {
            this.x = x;
            this.y = y;
            this.f = f;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
