package com.company.components.controls;

import com.company.Constants;
import com.company.components.GameField;
import com.company.utils.PausableSwingWorker;

import javax.swing.*;
import java.awt.*;

public class StartGameMenu extends JPanel {

    private PausableSwingWorker<Void, Void> startGameWorker;

    public StartGameMenu(GameField gameField){

        setBackground(new Color(0, 0, 0, 0));
        gameField.setPlayersAbilitiesActivated(false);

        setLayout(new BorderLayout());
        JLabel label = new JLabel(" 3 ");
        label.setFont(new Font(Constants.defaultFontFamily, Font.ITALIC, 40));
        label.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255, 60));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.black);
        add(label, BorderLayout.NORTH);
        StartGameMenu _this = this;

        startGameWorker = new PausableSwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while(!isCancelled()) {
                    sleep(800);
                    label.setText(" 2 ");
                    sleep(800);
                    label.setText(" 1 ");
                    sleep(800);
                    label.setText("Fight!");
                    gameField.setPlayersAbilitiesActivated(true);
                    sleep(500);
                    remove(label);
                    gameField.remove(_this);
                }
                return null;
            }
        };
        startGameWorker.execute();
    }

    public PausableSwingWorker<Void, Void> getStartGameWorker() {
        return startGameWorker;
    }
}
