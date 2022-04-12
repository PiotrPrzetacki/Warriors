package com.company.components.layouts;

import com.company.Constants;
import com.company.Team;
import com.company.classes.arenas.*;
import com.company.classes.characters.Archer;
import com.company.classes.characters.Warrior;
import com.company.components.GameSettings;
import com.company.components.controls.ArenaSelectionStackPanel;
import com.company.components.controls.HeaderLabel;
import com.company.components.controls.SecondaryButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArenaSelectionPanel extends JPanel {

    private JLabel title;
    private JButton playBtn;
    private ArenaSelectionStackPanel arenaSelectionStackPanel;

    private GameSettings gameSettingsPanel;
    private List<Arena> availableArenas;

    public ArenaSelectionPanel(GameSettings gameSettingsPanel){

        this.gameSettingsPanel = gameSettingsPanel;

        availableArenas = new ArrayList<>(Arrays.asList(
                new Jungle(),
                new Desert(),
                new Hell(),
                new Winter()
        ));

        title = new HeaderLabel("Select arena", 1);
        title.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

        arenaSelectionStackPanel = new ArenaSelectionStackPanel(availableArenas);
        arenaSelectionStackPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        playBtn = new SecondaryButton("Play!", new Font(Constants.defaultFontFamily, Font.BOLD, 22), new int[]{7, 15, 7, 15});
        playBtn.setMaximumSize(new Dimension(100, 50));
        playBtn.addActionListener(this::handlePlay);

        JPanel playBtnPanel = new JPanel();
        playBtnPanel.setLayout(new BoxLayout(playBtnPanel, BoxLayout.X_AXIS));
        playBtnPanel.add(Box.createHorizontalGlue());
        playBtnPanel.add(playBtn);
        playBtnPanel.add(Box.createHorizontalGlue());
        playBtnPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        setLayout(new BorderLayout());

        add(title, BorderLayout.NORTH);
        add(arenaSelectionStackPanel, BorderLayout.CENTER);

        add(playBtnPanel, BorderLayout.SOUTH);
    }

    private void handlePlay(ActionEvent e){
        gameSettingsPanel.setPlayers(gameSettingsPanel.getPlayers());
        Team team = new Team(gameSettingsPanel.getPlayers());
        gameSettingsPanel.setArena(arenaSelectionStackPanel.getSelectedArena());
        gameSettingsPanel.getMainWindow().startGame(
                gameSettingsPanel.getGameMode(),
                team,
                gameSettingsPanel.getArena()
        );
    }
}
