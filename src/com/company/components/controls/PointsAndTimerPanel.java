package com.company.components.controls;

import com.company.Constants;
import com.company.components.MonstersAttackGameField;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;

import static com.company.Constants.paragraphFont;

public class PointsAndTimerPanel extends JPanel {
    private final JLabel timerLabel;
    private final JLabel pointsLabel;
    private final Instant startTime;
    private final MonstersAttackGameField gameField;

    public PointsAndTimerPanel(MonstersAttackGameField monstersAttackGameField){
        startTime = Instant.now();
        gameField = monstersAttackGameField;
        setBackground(new Color(0x32FFFFFF, true));
        setPreferredSize(new Dimension(200, 30));
        timerLabel = new JLabel();
        pointsLabel = new JLabel();
        JLabel splitter = new JLabel(" | ");
        timerLabel.setFont(paragraphFont);
        pointsLabel.setFont(paragraphFont);
        splitter.setFont(paragraphFont);

        add(timerLabel);
        add(splitter);
        add(pointsLabel);
    }

    public void refresh(){
        Duration elapsedTime = Duration.between(startTime, Instant.now());
        String time = String.format("%02d:%02d",
                elapsedTime.toMinutes(),
                elapsedTime.toSecondsPart());
        timerLabel.setText(time);
        pointsLabel.setText(gameField.getPoints() + " p");
    }
}
