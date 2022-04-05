package com.company.components.layouts;

import com.company.components.GameSettings;
import com.company.components.controls.PrimaryButton;
import com.company.components.controls.TitleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {

    private JButton multiplayerBtn;
    private JButton singleplayerBtn;
    private JButton quitBtn;
    private GameSettings gameSettingsPanel;

    public MainMenuPanel(GameSettings gameSettingsPanel){
        this.gameSettingsPanel = gameSettingsPanel;

        JLabel titleLabel = new TitleLabel("Warriors");

        this.setLayout(new BorderLayout());

        multiplayerBtn = new PrimaryButton("Multiplayer", 2);
        singleplayerBtn = new PrimaryButton("Singleplayer", 2);
        singleplayerBtn.setEnabled(false);
        quitBtn = new PrimaryButton("Quit", 2);

        multiplayerBtn.addActionListener(this::handleClick);
        singleplayerBtn.addActionListener(this::handleClick);
        quitBtn.addActionListener(this::handleClick);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));


        buttonsPanel.add(Box.createVerticalGlue());
        buttonsPanel.add(multiplayerBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonsPanel.add(singleplayerBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonsPanel.add(quitBtn);
        buttonsPanel.add(Box.createVerticalGlue());

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);

    }

    private void handleClick(ActionEvent e){
        if(e.getSource()== multiplayerBtn){
            this.gameSettingsPanel.setGameMode(2);
            this.gameSettingsPanel.setCharacterSelection();
        }
        else if(e.getSource()==singleplayerBtn){
            this.gameSettingsPanel.setGameMode(1);
            this.gameSettingsPanel.setCharacterSelection();
        }
        else if(e.getSource()==quitBtn){
            System.exit(0);
        }
    }
}
