package com.company.components.layouts;

import com.company.classes.CharacterClass;
import com.company.components.controls.AbilityPanel;
import com.company.components.controls.PlayerInfoPanel;

import javax.swing.*;
import java.awt.*;

import static com.company.utils.TimeConverter.convertAbilityTime;

public class PlayersStats extends JPanel {

    private final CharacterClass[] players;
    private final PlayerInfoPanel[] playerInfoPanels;

    public PlayersStats(CharacterClass[] players){
        this.players = players;
        this.playerInfoPanels = new PlayerInfoPanel[players.length];
        setPreferredSize(new Dimension(1000, 42));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));

        for(int i=0; i<players.length; i++){
            playerInfoPanels[i] = new PlayerInfoPanel(players[i]);
            if(i==0) add(playerInfoPanels[i], BorderLayout.WEST);
            if(i==1) add(playerInfoPanels[i], BorderLayout.EAST);
        }
    }

    public void refresh(){
        for(int i=0; i<players.length; i++){
            playerInfoPanels[i].getHealthPanel().getHealthBar().setValue(players[i].getHealthPoints());
            playerInfoPanels[i].getHealthPanel().getHealthBar().setString(players[i].getHealthPoints() + "/" + players[i].getMaxHealthPoints() + " HP");
            for(AbilityPanel abilityPanel : playerInfoPanels[i].getAbilityPanels()){
                abilityPanel.getTimeLabel().setText(convertAbilityTime(players[i].getAbilityTimeouts().get(abilityPanel.getAbility())[0]));
            }
        }
    }
}
