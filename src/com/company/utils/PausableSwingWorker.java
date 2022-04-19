package com.company.utils;

import javax.swing.*;

public abstract class PausableSwingWorker<K, V> extends SwingWorker<K, V> {

    private volatile boolean isPaused;

    public final void pause() {
        if (!isPaused() && !isDone()) {
            isPaused = true;
            firePropertyChange("paused", false, true);
        }
    }

    public final void resume() {
        if (isPaused() && !isDone()) {
            isPaused = false;
            firePropertyChange("paused", true, false);
        }
    }

    public final boolean isPaused() {
        return isPaused;
    }

    protected void sleep(int milliseconds) throws InterruptedException {
        int interval = 50;
        for(int i=0; i<milliseconds; i+=interval){
            Thread.sleep(interval);
            if(isPaused()) i-= interval;
        }
    }
}