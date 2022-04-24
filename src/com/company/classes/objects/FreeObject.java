package com.company.classes.objects;

import com.company.Constants;

import java.awt.*;

public abstract class FreeObject {
    protected int x;
    protected int y;
    protected Image imageToDraw;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImageToDraw() {
        return imageToDraw;
    }

    protected boolean isOutsideGameField(){
        return x < 0 || x >= Constants.WINDOW_WIDTH || y < 0 || y >= Constants.WINDOW_HEIGHT;
    }
}

