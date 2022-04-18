package com.company.classes.arenas;

import com.company.Constants;
import com.company.classes.CharacterClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Winter extends Arena{

    private int icySquaresCount;
    private int icySquareNumber;

    public Winter() {
        this.arenaName = "Winter";
        this.eventEnabled = true;
        this.icySquaresCount = 8;
        this.icySquareNumber = -3;
        this.setImages();
        this.backgroundColor = new Color(0x468D9D);
        this.playersSpawnPoints = new ArrayList<>();
        this.playersSpawnPoints.add(new int[]{4, 3});
        this.playersSpawnPoints.add(new int[]{15, 3});
        this.walls = new ArrayList<>();
        walls.add(new int[]{0, 0});
        walls.add(new int[]{1, 0});
        walls.add(new int[]{2, 0});
        walls.add(new int[]{3, 0});
        walls.add(new int[]{4, 0});
        walls.add(new int[]{5, 0});
        walls.add(new int[]{6, 0});
        walls.add(new int[]{7, 0});
        walls.add(new int[]{8, 0});
        walls.add(new int[]{11, 0});
        walls.add(new int[]{12, 0});
        walls.add(new int[]{13, 0});
        walls.add(new int[]{14, 0});
        walls.add(new int[]{15, 0});
        walls.add(new int[]{16, 0});
        walls.add(new int[]{17, 0});
        walls.add(new int[]{18, 0});
        walls.add(new int[]{19, 0});

        walls.add(new int[]{0, 6});
        walls.add(new int[]{1, 6});
        walls.add(new int[]{2, 6});
        walls.add(new int[]{3, 6});
        walls.add(new int[]{4, 6});
        walls.add(new int[]{5, 6});
        walls.add(new int[]{6, 6});
        walls.add(new int[]{7, 6});
        walls.add(new int[]{8, 6});
        walls.add(new int[]{11, 6});
        walls.add(new int[]{12, 6});
        walls.add(new int[]{13, 6});
        walls.add(new int[]{14, 6});
        walls.add(new int[]{15, 6});
        walls.add(new int[]{16, 6});
        walls.add(new int[]{17, 6});
        walls.add(new int[]{18, 6});
        walls.add(new int[]{19, 6});

        walls.add(new int[]{0, 1});
        walls.add(new int[]{7, 1});
        walls.add(new int[]{12, 1});
        walls.add(new int[]{19, 1});
        walls.add(new int[]{0, 2});
        walls.add(new int[]{19, 2});
        walls.add(new int[]{0, 4});
        walls.add(new int[]{19, 4});
        walls.add(new int[]{0, 5});
        walls.add(new int[]{7, 5});
        walls.add(new int[]{0, 5});
        walls.add(new int[]{7, 5});
        walls.add(new int[]{12, 5});
        walls.add(new int[]{19, 5});
    }

    @Override
    public void resetArena() {
        super.resetArena();
        getRandomSquares(icySquaresCount, icySquareNumber);
    }
}
