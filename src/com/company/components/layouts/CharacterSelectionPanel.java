package com.company.components.layouts;

import com.company.components.GameSettings;
import com.company.components.controls.HeaderLabel;
import com.company.components.controls.PlayerSelectionStackPanel;
import com.company.components.controls.SecondaryButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CharacterSelectionPanel extends JPanel {

    private GameSettings gameSettingsPanel;

    private SecondaryButton nextBtn;

    private List<String> availableCharacters;
    private List<PlayerSelectionStackPanel> playerSelectionStackPanels;
    private JPanel mainPanel;
    private JLabel title;

    public CharacterSelectionPanel(GameSettings gameSettingsPanel){

        this.availableCharacters = new ArrayList<>();
        this.playerSelectionStackPanels = new ArrayList<>();

        this.gameSettingsPanel = gameSettingsPanel;
        this.nextBtn = new SecondaryButton("NEXT >");
        this.nextBtn.addActionListener(this::handleClickNext);

        this.availableCharacters.add("Warrior");
        this.availableCharacters.add("Archer");

        setLayout(new BorderLayout());

        title = new HeaderLabel("Select character", 1);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 20));

        headerPanel.add(title);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(nextBtn);

        playerSelectionStackPanels.add(new PlayerSelectionStackPanel(availableCharacters, "Player1"));
        if (gameSettingsPanel.getGameMode()==2)
            playerSelectionStackPanels.add(new PlayerSelectionStackPanel(availableCharacters, "Player2"));

        JPanel mainPanelContainer = new JPanel();
        mainPanelContainer.setLayout(new BoxLayout(mainPanelContainer, BoxLayout.PAGE_AXIS));

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        mainPanel.add(Box.createHorizontalGlue());
        for (PlayerSelectionStackPanel playerSelectionStackPanel : playerSelectionStackPanels) {
            mainPanel.add(playerSelectionStackPanel);
            mainPanel.add(Box.createHorizontalGlue());
        }

        mainPanelContainer.add(Box.createVerticalGlue());
        mainPanelContainer.add(mainPanel);
        mainPanelContainer.add(Box.createVerticalGlue());

        add(headerPanel, BorderLayout.NORTH);
        add(mainPanelContainer, BorderLayout.CENTER);
    }

    protected void handleClickNext(ActionEvent e){
        for (PlayerSelectionStackPanel playerSelectionStackPanel : playerSelectionStackPanels)
            if (playerSelectionStackPanel.getPlayerNameTextField().getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Player name is empty", "Validation error", JOptionPane.WARNING_MESSAGE);
                return;
            }

        gameSettingsPanel.clearPlayers();
        for (int i = 0; i< playerSelectionStackPanels.size(); i++){
            gameSettingsPanel.addPlayer(playerSelectionStackPanels.get(i).getSelectedCharacter());
        }
        gameSettingsPanel.setArenaSelectionPanel();
    }
}