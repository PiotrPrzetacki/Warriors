package com.company.classes.arenas.eventWorkers;

import com.company.classes.CharacterClass;
import com.company.classes.arenas.Hell;
import com.company.utils.PausableSwingWorker;

public class FireSpawnWorker extends PausableSwingWorker {

    private Hell hell;

    public FireSpawnWorker(Hell hell) {
        this.hell = hell;
    }

    @Override
    protected Void doInBackground() throws InterruptedException {

        while (!isCancelled()) {
            sleep(hell.getFireCooldown());
            if (!isPaused()) {
                hell.getRandomSquares(hell.getFireSquaresCount(), hell.getFireSquareNumber());
            }
            sleep(hell.getFireDuration());
            if (!isPaused()){
                for (int i = 0; i < CharacterClass.occupiedCells.length; i++) {
                    for (int j = 0; j < CharacterClass.occupiedCells[0].length; j++) {
                        if (hell.getSpecialSquares()[i][j] == -2) {
                            hell.getSpecialSquares()[i][j] = 0;
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
        }
    }
}