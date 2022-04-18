package com.company.classes.arenas;

import com.company.classes.CharacterClass;
import com.company.utils.PausableSwingWorker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Hell extends Arena{

    private int fireSquaresCount;
    private int fireDuration;
    private int fireCooldown;
    private int fireSquareNumber;
    private PausableSwingWorker<Void, Void> eventWorker;

    public Hell() {
        this.arenaName = "Hell";
        this.eventEnabled = true;
        this.setImages();
        this.fireSquaresCount = 7;
        this.fireSquareNumber = -2;
        this.fireDuration = 15_000;
        this.fireCooldown = 7_000;
        this.eventEnabled = true;
        this.backgroundColor = new Color(0x693535);
        this.playersSpawnPoints = new ArrayList<>();
        this.playersSpawnPoints.add(new int[]{4, 3});
        this.playersSpawnPoints.add(new int[]{15, 3});
        this.walls = new ArrayList<>();
        walls.add(new int[]{2, 1});
        walls.add(new int[]{3, 1});
        walls.add(new int[]{16, 1});
        walls.add(new int[]{17, 1});
        walls.add(new int[]{9, 2});
        walls.add(new int[]{10, 2});
        walls.add(new int[]{9, 3});
        walls.add(new int[]{10, 3});
        walls.add(new int[]{9, 4});
        walls.add(new int[]{10, 4});
        walls.add(new int[]{3, 5});
        walls.add(new int[]{2, 5});
        walls.add(new int[]{16, 5});
        walls.add(new int[]{17, 5});

        eventWorker = new PausableSwingWorker<>() {
            @Override
            protected Void doInBackground() throws InterruptedException {

                while (!isCancelled()) {
                    sleep(fireCooldown);
                    if (!isPaused()) {
                        getRandomSquares(fireSquaresCount, fireSquareNumber);
                    }
                    sleep(fireDuration);
                    if (!isPaused()){
                        for (int i = 0; i < CharacterClass.occupiedCells.length; i++) {
                            for (int j = 0; j < CharacterClass.occupiedCells[0].length; j++) {
                                if (specialSquares[i][j] == -2) {
                                    specialSquares[i][j] = 0;
                                }
                            }
                        }
                    } else {
                        Thread.sleep(50);
                    }
                }
                return null;
            }

            private void sleep(int milliseconds) throws InterruptedException {
                int interval = 10;
                for(int i=0; i<milliseconds; i+=interval){
                    Thread.sleep(interval);
                    if(isPaused()) i-= interval;
                    System.out.println(i);
                }
            }
        };
        backgroundWorkers.add(eventWorker);
    }

    @Override
    public void setArenaEvent() {
        eventWorker.execute();
    }
}
