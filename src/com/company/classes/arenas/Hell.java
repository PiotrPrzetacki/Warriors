package com.company.classes.arenas;

import com.company.classes.CharacterClass;
import com.company.classes.arenas.eventWorkers.FireSpawnWorker;
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

        eventWorker = new FireSpawnWorker(this);
        backgroundWorkers.add(eventWorker);
    }

    @Override
    public void resetArena() {
        super.resetArena();
        eventWorker.cancel(true);
        eventWorker = new FireSpawnWorker(this);
        backgroundWorkers.add(eventWorker);
        eventWorker.execute();
    }

    @Override
    public void closeArena(){
        eventWorker.cancel(true);
    }

    public int getFireSquaresCount() {
        return fireSquaresCount;
    }

    public int getFireDuration() {
        return fireDuration;
    }

    public int getFireCooldown() {
        return fireCooldown;
    }

    public int getFireSquareNumber() {
        return fireSquareNumber;
    }
}
