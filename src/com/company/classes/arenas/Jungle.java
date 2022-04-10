package com.company.classes.arenas;

import com.company.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Jungle extends Arena{

    public Jungle(){
        this.arenaName = "Jungle";
        this.setImages();
        this.backgroundColor = new Color(0x5FAF7B);
        this.playersSpawnPoints = new ArrayList<>();
        this.playersSpawnPoints.add(new int[]{4, 3});
        this.playersSpawnPoints.add(new int[]{15, 3});
        this.walls = new ArrayList<>();
        walls.add(new int[]{2, 1});
        walls.add(new int[]{3, 1});
        walls.add(new int[]{4, 1});
        walls.add(new int[]{2, 2});
        walls.add(new int[]{15, 1});
        walls.add(new int[]{16, 1});
        walls.add(new int[]{17, 1});
        walls.add(new int[]{2, 4});
        walls.add(new int[]{2, 5});
        walls.add(new int[]{3, 5});
        walls.add(new int[]{4, 5});
        walls.add(new int[]{17, 4});
        walls.add(new int[]{17, 5});
        walls.add(new int[]{16, 5});
        walls.add(new int[]{15, 5});
        walls.add(new int[]{10, 3});
    }

    @Override
    public void setArenaEvent() {

    }
}
