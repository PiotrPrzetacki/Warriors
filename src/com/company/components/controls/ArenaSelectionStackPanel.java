package com.company.components.controls;

import com.company.Constants;
import com.company.classes.arenas.Arena;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ArenaSelectionStackPanel extends StackPanel{

    private JLabel arenaNameLabel;
    private List<Arena> availableArenas;

    public ArenaSelectionStackPanel(List<Arena> availableArenas) {
        super(new SecondaryButton(">", new Font(Constants.defaultFontFamily, Font.BOLD, 22), new int[]{7, 15, 7, 15}), new SecondaryButton("<", new Font(Constants.defaultFontFamily, Font.BOLD, 22), new int[]{7, 15, 7, 15}), availableArenas);

        this.availableArenas = availableArenas;
        this.arenaNameLabel = new HeaderLabel(availableArenas.get(currentIndex).getArenaName(), new Font(Constants.defaultFontFamily, Font.BOLD, 20));

        Image arenaImg = availableArenas.get(currentIndex).getArenaImage();
        arenaImg = arenaImg.getScaledInstance(365, 264, Image.SCALE_SMOOTH);
        ImageIcon arenaImage = new ImageIcon(arenaImg);
        arenaNameLabel.setIcon(arenaImage);
        arenaNameLabel.setVerticalTextPosition(JLabel.TOP);
        arenaNameLabel.setHorizontalTextPosition(JLabel.CENTER);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(prevBtn);
        add(Box.createHorizontalGlue());
        add(arenaNameLabel);
        add(Box.createHorizontalGlue());
        add(nextBtn);
    }

    @Override
    protected void refresh() {
        Image arenaImg = availableArenas.get(currentIndex).getArenaImage();
        arenaImg = arenaImg.getScaledInstance(365, 264, Image.SCALE_SMOOTH);
        ImageIcon arenaImage = new ImageIcon(arenaImg);
        arenaNameLabel.setIcon(arenaImage);
        arenaNameLabel.setVerticalTextPosition(JLabel.TOP);
        arenaNameLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.arenaNameLabel.setText(availableArenas.get(currentIndex).getArenaName());
        SwingUtilities.updateComponentTreeUI(this);
    }

    public Arena getSelectedArena(){
        return availableArenas.get(currentIndex);
    }
}
