package com.company.components.controls;

import com.company.classes.CharacterClass;
import com.company.classes.characters.Abilities;

import javax.swing.*;

import java.awt.*;

import static com.company.utils.ResourceLoader.load;

public class AbilityPanel extends JPanel {

    private ImageIcon abilityIcon;
    private final JLabel abilityIconLabel;
    private final JLabel timeLabel;
    private final Abilities ability;

    public AbilityPanel(Abilities ability, CharacterClass player){
        this.ability = ability;
        if(ability == Abilities.MOVE){
            abilityIcon = getResizedImage("/icons/boot-icon.png");
        }
        else if(ability == Abilities.ATTACK){
            abilityIcon = getResizedImage("/icons/sword-icon.png");
        }
        else if(ability == Abilities.TELEPORT){
            abilityIcon = getResizedImage("/icons/teleport-icon.png");
        }
        else if(ability == Abilities.LONG_SHOTS){
            abilityIcon = getResizedImage("/icons/bow-icon.png");
        }

        timeLabel = new JLabel(String.valueOf(player.getAbilityTimeouts().get(ability)[0]));
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

    public Abilities getAbility() {
        return ability;
    }
}
