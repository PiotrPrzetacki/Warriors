package com.company.components.controls;

import com.company.classes.CharacterClass;

import javax.swing.*;
import java.awt.*;

public class HealthPanel extends JPanel {

    private HealthBar healthBar;
    private JLabel label;

    public HealthPanel(CharacterClass player) {
        this.healthBar = new HealthBar(player);
        this.label = new JLabel(player.getName());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        add(label, c);

        c.gridx = 0;
        c.gridy = 1;
        add(healthBar, c);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }
}
