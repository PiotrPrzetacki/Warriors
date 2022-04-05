package com.company.components.controls;

import com.company.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrimaryButton extends JButton {

    public static final int LEFT_BORDER=2;
    public static final int BOTTOM_BORDER=1;

    public PrimaryButton(String text, int hoverStyle){
        super(text);

        this.setFocusable(false);
        this.setName(text);
        this.setFont(new Font(Constants.defaultFontFamily, Font.PLAIN, 22));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setContentAreaFilled(false);

        JButton _this = this;

        if(hoverStyle==1) {
            this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (isEnabled())
                        _this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (isEnabled())
                        _this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));
                }
            });
        }

        if(hoverStyle==2) {
            this.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(0, 0, 0, 0)),
                    BorderFactory.createEmptyBorder(2, 8, 1, 0)
            ));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (isEnabled())
                        _this.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(0, 4, 0, 0, Color.red),
                                BorderFactory.createEmptyBorder(2, 8, 1, 0)
                        ));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (isEnabled())
                        _this.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(0, 0, 0, 0)),
                                BorderFactory.createEmptyBorder(2, 8, 1, 0)
                        ));
                }
            });
        }
    }
}
