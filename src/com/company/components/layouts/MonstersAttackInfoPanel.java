package com.company.components.layouts;

import com.company.components.MonstersAttackGameField;
import com.company.components.controls.PointsAndTimerPanel;
import com.company.components.controls.WaveCounter;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MonstersAttackInfoPanel extends JPanel {
    private final WaveCounter waveCounter;
    private final PointsAndTimerPanel pointsAndTimerPanel;

    public MonstersAttackInfoPanel(MonstersAttackGameField monstersAttackGameField){
        waveCounter = new WaveCounter(monstersAttackGameField);
        pointsAndTimerPanel = new PointsAndTimerPanel(monstersAttackGameField);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                add(waveCounter, BorderLayout.WEST);
                add(pointsAndTimerPanel, BorderLayout.EAST);
                revalidate();
            }
        }, 3_500);
    }

    public void refresh(){
        waveCounter.refresh();
        pointsAndTimerPanel.refresh();
    }
}
