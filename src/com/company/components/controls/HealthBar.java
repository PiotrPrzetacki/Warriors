package com.company.components.controls;

import com.company.Constants;
import com.company.classes.CharacterClass;

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JProgressBar {

    public HealthBar(CharacterClass player){
        super(0, player.getMaxHealthPoints());
        setValue(player.getHealthPoints());
        setForeground(new Color(0xBE1111));
        setFont(Constants.paragraphFont);
        setPreferredSize(new Dimension(250, 22));
        setMaximumSize(new Dimension(250, 22));
        setStringPainted(true);
        setString(player.getHealthPoints() + "/" + player.getMaxHealthPoints() + " HP");
    }
}
