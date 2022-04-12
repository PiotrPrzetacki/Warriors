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
        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255, 60));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.black);
        add(label, BorderLayout.NORTH);
        StartGameMenu _this = this;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        label.setText(" 2 ");
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        label.setText(" 1 ");

                                        new java.util.Timer().schedule(
                                                new java.util.TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        label.setText("Fight!");
                                                        gameField.setPlayersCanAttack(true);
                                                        gameField.setPlayersCanMove(true);

                                                        new java.util.Timer().schedule(
                                                                new java.util.TimerTask() {
                                                                    @Override
                                                                    public void run() {
                                                                        remove(label);
                                                                        gameField.remove(_this);
                                                                    }
                                                                }, 500
                                                        );
                                                    }
                                                }, 1000
                                        );

                                    }
                                }, 1000
                        );
                    }
                }, 1000
        );

    }
}
