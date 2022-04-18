package com.company.components.controls;

import com.company.Constants;
import com.company.MainWindow;
import com.company.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseMenu extends JPanel {

    private final JLabel titleLabel;
    private final MainWindow mainWindow;

    public PauseMenu(MainWindow mainWindow, String text){
        this.mainWindow = mainWindow;
        setMaximumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(400, 300));
        setMinimumSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.titleLabel = new HeaderLabel(text, 2);
        titleLabel.setFont(new Font(Constants.defaultFontFamily, Font.BOLD, 34));
        add(titleLabel, BorderLayout.NORTH);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        JButton homeButton = new MenuButton("Go to main menu", new ImageIcon(getClass().getResource("/icons/home_icon.png")), this);
        JButton restartButton = new MenuButton("Restart Game", new ImageIcon(getClass().getResource("/icons/restart_icon.png")), this);
        JButton quitButton = new MenuButton("Quit", new ImageIcon(getClass().getResource("/icons/quit_icon.png")), this);
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
        mainWindow.goToMainMenu(mainWindow.getGameField().getArena());
    }
    private void handleRestartGame(ActionEvent e){
        mainWindow.getGameField().getArena().cancelBackgroundWorkers();
        mainWindow.startGame(mainWindow.getGameSettingsPanel().getGameMode(),
                             new Team(mainWindow.getGameSettingsPanel().getPlayers()),
                             mainWindow.getGameSettingsPanel().getArena());
    }
    private void handleQuit(ActionEvent e){
        System.exit(0);
    }

    public void refresh(){
        revalidate();
        repaint();
    }

    public void setTitleText(String text){
        titleLabel.setText(text);
    }
}
