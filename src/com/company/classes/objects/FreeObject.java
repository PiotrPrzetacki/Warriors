package com.company.classes.objects;

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
}

