package com.company.components.layouts;

import com.company.classes.CharacterClass;
import com.company.components.controls.HealthBar;
import com.company.components.controls.PlayerInfoPanel;

import javax.swing.*;
import java.awt.*;

public class PlayersStats extends JPanel {

    private CharacterClass[] players;
    private PlayerInfoPanel[] playerInfoPanels;

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
            playerInfoPanels[i].getHealthBar().setValue(players[i].getHealthPoints());
            playerInfoPanels[i].getHealthBar().setString(players[i].getHealthPoints() + "/" + players[i].getMaxHealthPoints() + " HP");
        }
    }
}
