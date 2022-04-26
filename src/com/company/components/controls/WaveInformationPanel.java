package com.company.components.controls;

import com.company.Constants;
import com.company.utils.PausableSwingWorker;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class WaveInformationPanel extends JPanel {
    public WaveInformationPanel(int wave) {
        setBackground(new Color(255, 255, 255, 60));

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Wave "+wave);
        label.setFont(new Font(Constants.defaultFontFamily, Font.PLAIN, 32));
        label.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255, 60));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.black);
        add(label, BorderLayout.NORTH);

        SwingWorker<Void, Void> startGameWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                for(int i=0; i<5; i++){
                    setBackground(new Color(121, 121, 121, 77));
                    label.setBackground(new Color(121, 121, 121, 77));
                    Thread.sleep(200);
                    setBackground(new Color(255, 255, 255, 60));
                    label.setBackground(new Color(255, 255, 255, 60));
                    Thread.sleep(200);
                }
                return null;
            }
        };
        startGameWorker.execute();
    }
}
