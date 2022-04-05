package com.company.components.controls;

import com.company.Constants;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {

    public TitleLabel(String text){
        super("Warriors");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 0));
        this.setFont(Constants.titleFont);
        this.setForeground(Color.black);
    }
}
