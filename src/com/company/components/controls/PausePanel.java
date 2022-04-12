package com.company.components.controls;

import com.company.MainWindow;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {

    private PauseMenu pauseMenu;

    public PausePanel(MainWindow mainWindow, String text){
        this.pauseMenu = new PauseMenu(mainWindow, text);
        setBackground(new Color(0, 0, 0, 190));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalGlue());
        add(pauseMenu);
        add(Box.createHorizontalGlue());
    }

    public void refresh(){
        pauseMenu.refresh();
    }

    public void setPauseText(String text){
        pauseMenu.setTitleText(text);
    }
}
