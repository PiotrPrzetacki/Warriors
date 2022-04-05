package com.company.components.controls;

import com.company.Constants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class SecondaryButton extends JButton {

    private Border defaultBorder;
    private Border hoverBorder;
    private Border buttonPadding;
    private Font font;

    public SecondaryButton(String text){
        super(text);

        this.font = new Font(Constants.defaultFontFamily, Font.BOLD, 16);
        this.buttonPadding = BorderFactory.createEmptyBorder(7, 12, 7, 12);
        this.defaultBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(153, 217, 234), 5),
                buttonPadding);
        this.hoverBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(102, 199, 223), 5),
                buttonPadding);
        this.setFocusable(false);
        this.setName(text);
        this.setFont(font);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setContentAreaFilled(false);
        this.setBorder(defaultBorder);

        JButton _this = this;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Font font = _this.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
                _this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                _this.setFont(font.deriveFont(attributes));
                _this.setBorder(hoverBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                _this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                _this.setFont(font);
                _this.setBorder(defaultBorder);
            }
        });
    }

    public SecondaryButton(String text, Font font, int[] padding){
        super(text);

        this.buttonPadding = BorderFactory.createEmptyBorder(padding[0], padding[1], padding[2], padding[3]);
        this.defaultBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(153, 217, 234), 5),
                buttonPadding);
        this.hoverBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(102, 199, 223), 5),
                buttonPadding);
        this.setFocusable(false);
        this.setName(text);
        this.setFont(font);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setContentAreaFilled(false);
        this.setBorder(defaultBorder);

        JButton _this = this;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Font font = _this.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
                _this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                _this.setFont(font.deriveFont(attributes));
                _this.setBorder(hoverBorder);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                _this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                _this.setFont(font);
                _this.setBorder(defaultBorder);
            }
        });
    }
}
