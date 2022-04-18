package com.company.components.controls;

import com.company.Constants;
import com.company.components.GameField;

import javax.swing.*;
import java.awt.*;

public class StartGameMenu extends JPanel {

    public StartGameMenu(GameField gameField){

        setBackground(new Color(0, 0, 0, 0));
        gameField.setPlayersCanMove(false);
        gameField.setPlayersCanAttack(false);

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

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000);
                label.setText(" 2 ");
                Thread.sleep(1000);
                label.setText(" 1 ");
                Thread.sleep(1000);
                label.setText("Fight!");
                gameField.setPlayersCanAttack(true);
                gameField.setPlayersCanMove(true);
                Thread.sleep(500);
                remove(label);
                gameField.remove(_this);
                return null;
            }
        }.execute();

    }
}
