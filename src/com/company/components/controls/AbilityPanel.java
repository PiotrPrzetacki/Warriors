package com.company.components.controls;

import com.company.classes.CharacterClass;

import javax.swing.*;

import java.awt.*;

import static com.company.utils.ResourceLoader.load;

public class AbilityPanel extends JPanel {

    private ImageIcon abilityIcon;
    private JLabel abilityIconLabel;
    private JLabel timeLabel;
    private String ability;

    public AbilityPanel(String ability, CharacterClass player){
        this.ability = ability;
        if(ability.equals("move")){
            abilityIcon = getResizedImage("/icons/boot-icon.png");
        }
        else if(ability.equals("attack")){
            abilityIcon = getResizedImage("/icons/sword-icon.png");
        }
        else if(ability.equals("teleport")){
            abilityIcon = getResizedImage("/icons/teleport-icon.png");
        }

        timeLabel = new JLabel(String.valueOf(player.getAbilityTimeouts().get(ability)));
        abilityIconLabel = new JLabel(abilityIcon);
        setMinimumSize(new Dimension(35, 40));
        setPreferredSize(new Dimension(35, 40));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        add(timeLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        add(abilityIconLabel, c);
    }

    private ImageIcon getResizedImage(String path){
        return new ImageIcon(new ImageIcon(load(path)).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public String getAbility() {
        return ability;
    }
}
