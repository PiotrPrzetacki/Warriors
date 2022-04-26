package com.company.components.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends JButton {

    private final HeaderLabel textLabel;

    public MenuButton(String text, ImageIcon icon, PauseMenu parent){
        setPreferredSize(new Dimension(80, 80));
        setMaximumSize(new Dimension(80, 80));
        setBackground(Color.red);
        setBorder(BorderFactory.createLineBorder(new Color(0x810303)));
        setIcon(icon);
        setFocusable(false);

        textLabel = new HeaderLabel(text, 3);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(new Color(0x9A0505)));
                parent.getInfoPanel().add(textLabel);
                SwingUtilities.updateComponentTreeUI(parent);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(new Color(0x810303)));
                parent.getInfoPanel().remove(textLabel);
                SwingUtilities.updateComponentTreeUI(parent);
            }


        });
    }
}
