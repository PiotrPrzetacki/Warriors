package com.company.components.controls;

import com.company.Constants;
import com.company.classes.arenas.Arena;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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

    private void setImageBorder(Image img){
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setStroke(new BasicStroke(10));
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, img.getWidth(null), img.getHeight(null));
        try {
            ImageIO.createImageOutputStream(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Arena getSelectedArena(){
        return availableArenas.get(currentIndex);
    }
}
