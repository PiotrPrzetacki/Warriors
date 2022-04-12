package com.company.components.controls;

import com.company.Constants;

import javax.swing.*;
import java.awt.*;

public class HeaderLabel extends JLabel {

    public HeaderLabel(String text, int level){
        super(text);
        switch (level){
            case 1:
                this.setFont(Constants.header1Font);
                this.setHorizontalAlignment(JLabel.LEFT);
                break;

            default:
            case 2:
                this.setFont(Constants.header2Font);
                this.setHorizontalAlignment(JLabel.CENTER);
                this.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }
    public HeaderLabel(String text, Font font){
        super(text);
        setFont(font);
    }
}
