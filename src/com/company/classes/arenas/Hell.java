package com.company.classes.arenas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Hell extends Arena{

    public Hell() {
        this.arenaName = "Hell";
        this.setImages();
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
    }
}
