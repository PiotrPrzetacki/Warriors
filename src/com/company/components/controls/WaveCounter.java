package com.company.components.controls;

import com.company.Constants;
import com.company.components.MonstersAttackGameField;

import javax.swing.*;
import java.awt.*;

public class WaveCounter extends JPanel {
    private final JLabel label;
    private final MonstersAttackGameField gameField;

    public WaveCounter(MonstersAttackGameField monstersAttackGameField){
        gameField = monstersAttackGameField;
        setPreferredSize(new Dimension(100, 30));
        setBackground(new Color(0x32FFFFFF, true));
        label = new JLabel();
        label.setText(String.valueOf(monstersAttackGameField.getWave()));
        label.setFont(Constants.paragraphFont);
        add(label);
    }

    public void refresh(){
        label.setText("Wave: "+gameField.getWave());
    }
}
