package com.company.components.controls;

import com.company.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextField extends JTextField {

    public TextField(String defaultText){
        super(defaultText);
        this.setMaximumSize(new Dimension(300, 30));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(Constants.paragraphFont);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));

        JTextField _this = this;

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (_this.getText().length() >= 12 )
                    e.consume();
            }
        });
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                _this.selectAll();
            }
        });
    }
}
