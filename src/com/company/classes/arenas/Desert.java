package com.company.classes.arenas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Desert extends Arena{

    public Desert() {
        this.arenaName = "Desert";
        this.setImages();
        this.backgroundColor = new Color(0x837D4B);
        this.playersSpawnPoints = new ArrayList<>();
        this.playersSpawnPoints.add(new int[]{4, 3});
        this.playersSpawnPoints.add(new int[]{15, 3});
        this.walls = new ArrayList<>();
        walls.add(new int[]{0, 0});
        walls.add(new int[]{1, 0});
        walls.add(new int[]{0, 1});
        walls.add(new int[]{18, 0});
        walls.add(new int[]{19, 0});
        walls.add(new int[]{19, 1});
        walls.add(new int[]{8, 2});
        walls.add(new int[]{8, 4});
        walls.add(new int[]{11, 4});
        walls.add(new int[]{11, 2});
        walls.add(new int[]{0, 5});
        walls.add(new int[]{19, 5});
        walls.add(new int[]{0, 6});
        walls.add(new int[]{1, 6});
        walls.add(new int[]{18, 6});
        walls.add(new int[]{19, 6});
    }

    @Override
    public void setArenaEvent() {

    }
}
