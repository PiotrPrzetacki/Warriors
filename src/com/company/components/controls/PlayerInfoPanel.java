package com.company.components.controls;

import com.company.classes.CharacterClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerInfoPanel extends JPanel {

    private final HealthPanel healthPanel;
    private List<AbilityPanel> abilityPanels;

    public PlayerInfoPanel(CharacterClass player){
        this.healthPanel = new HealthPanel(player);

        abilityPanels = new ArrayList<>();
        abilityPanels.add(new AbilityPanel("move", player));
        abilityPanels.add(new AbilityPanel("attack", player));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(healthPanel);

        for (AbilityPanel abilityPanel : abilityPanels){
            add(Box.createRigidArea(new Dimension(5, 0)));
            add(abilityPanel);
        }
    }

    public HealthPanel getHealthPanel() {
        return healthPanel;
    }

    public List<AbilityPanel> getAbilityPanels() {
        return abilityPanels;
    }
}
