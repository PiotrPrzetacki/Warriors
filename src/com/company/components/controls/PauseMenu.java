package com.company.components.controls;

import com.company.Constants;
import com.company.MainWindow;
import com.company.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseMenu extends JPanel {

    private JPanel buttonsPanel;
    private JButton homeButton;
    private JButton restartButton;
    private JButton quitButton;
    private MainWindow mainWindow;

    public PauseMenu(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        setMaximumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        HeaderLabel title = new HeaderLabel("Game Paused", 2);
        title.setFont(new Font(Constants.defaultFontFamily, Font.BOLD, 40));
        add(title, BorderLayout.NORTH);
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        homeButton = new MenuButton("Go to main menu", new ImageIcon("C:\\Users\\Piotrek\\Desktop\\back.png"), this);
        restartButton = new MenuButton("Restart Game", new ImageIcon("C:\\Users\\Piotrek\\Desktop\\restart.png"), this);
        quitButton = new MenuButton("Quit", new ImageIcon("C:\\Users\\Piotrek\\Desktop\\exit.png"), this);
        homeButton.addActionListener(this::handleGoToMainMenu);
        restartButton.addActionListener(this::handleRestartGame);
        quitButton.addActionListener(this::handleQuit);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(homeButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(restartButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(quitButton);
        buttonsPanel.add(Box.createHorizontalGlue());

        add(buttonsPanel, BorderLayout.SOUTH);

    }

    private void handleGoToMainMenu(ActionEvent e){
        mainWindow.goToMainMenu();
    }
    private void handleRestartGame(ActionEvent e){
        mainWindow.startGame(mainWindow.getGameSettingsPanel().getGameMode(),
                             new Team(mainWindow.getGameSettingsPanel().getPlayers()),
                             mainWindow.getGameSettingsPanel().getArena());
    }
    private void handleQuit(ActionEvent e){
        System.exit(0);
    }

    public void refresh(){
        removeAll();
        HeaderLabel title = new HeaderLabel("Game Paused", 2);
        title.setFont(new Font(Constants.defaultFontFamily, Font.BOLD, 40));
        add(title, BorderLayout.NORTH);
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        homeButton = new MenuButton("Go to main menu", new ImageIcon("C:\\Users\\Piotrek\\Desktop\\back.png"), this);
        restartButton = new MenuButton("Restart Game", new ImageIcon("C:\\Users\\Piotrek\\Desktop\\restart.png"), this);
        quitButton = new MenuButton("Quit", new ImageIcon("C:\\Users\\Piotrek\\Desktop\\exit.png"), this);
        homeButton.addActionListener(this::handleGoToMainMenu);
        restartButton.addActionListener(this::handleRestartGame);
        quitButton.addActionListener(this::handleQuit);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(homeButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(restartButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(quitButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
