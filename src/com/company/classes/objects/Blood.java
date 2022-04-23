package com.company.classes.objects;

import javax.swing.*;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.company.utils.ResourceLoader.load;

public class Blood extends FreeObject{

    public Blood(int x, int y, List<FreeObject> gameFieldFreeObjects){
        this.x = getRandomInt(x-22, 20);
        this.y = getRandomInt(y+12, 20);
        this.imageToDraw = new ImageIcon(load("/images/characters/effects/blood.gif")).getImage();

        Blood _this = this;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                gameFieldFreeObjects.remove(_this);
            }
        }, 200);
    }

    private int getRandomInt(int input, int range){
        return input + (int) (Math.random()*range - 0.5*range);
    }
}
