package com.company.components.controls;

import com.company.classes.CharacterClass;
import com.company.classes.characters.Archer;
import com.company.classes.characters.Healer;
import com.company.classes.characters.Mage;
import com.company.classes.characters.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import static com.company.utils.ResourceLoader.load;

public class PlayerSelectionStackPanel extends StackPanel {

    private ImageIcon classImage;
    private final JLabel classNameLabel;
    private final JLabel imageLabel;
    private Map<String, String> playerData;

    private JTextField playerNameTextField;

    public PlayerSelectionStackPanel(List<String> availableCharacters, String defaultPlayerName){
        super(new PrimaryButton(" > ", 1), new PrimaryButton(" < ", 1), availableCharacters);

        this.playerNameTextField = new TextField(defaultPlayerName);
        this.playerNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (playerNameTextField.getText().length() >= 12 )
                    e.consume();
            }
        });
        this.playerNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                playerNameTextField.selectAll();
            }
        });

        this.playerData = getClassData(availableCharacters.get(currentIndex));
        this.classImage = new ImageIcon(load(playerData.get("imageURL")));

        this.classNameLabel = new HeaderLabel(playerData.get("className"), 2);

        this.imageLabel = new JLabel();
        this.imageLabel.setIcon(classImage);
        this.imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.imageLabel.setPreferredSize(new Dimension(140, 2000));
        this.imageLabel.setMinimumSize(new Dimension(140, 200));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(140, 320));
        this.setMinimumSize(new Dimension(140, 320));
        this.setPreferredSize(new Dimension(140, 320));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonsPanel.add(prevBtn);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(nextBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));


        add(classNameLabel);
        add(imageLabel);
        add(playerNameTextField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonsPanel);

    }

    private Map<String, String> getClassData(String characterClassName){
        Map<String, String> data = new HashMap<>();
        data.put("className", characterClassName);
        switch (characterClassName) {
            case "Warrior" -> data.put("imageURL", "/images/characters/warrior/WarriorResizedBaseImage.png");
            case "Archer" -> data.put("imageURL", "/images/characters/archer/ArcherResizedBaseImage.png");
            case "Mage" -> data.put("imageURL", "/images/characters/mage/MageResizedBaseImage.png");
            case "Healer" -> data.put("imageURL", "/images/characters/healer/HealerBaseResized.png");
        }

        return data;
    }

    protected void refresh(){
        this.playerData = getClassData((String) elements.get(currentIndex));
        this.classImage = new ImageIcon(load(playerData.get("imageURL")));
        this.classNameLabel.setText(playerData.get("className"));
        this.imageLabel.setIcon(classImage);

        SwingUtilities.updateComponentTreeUI(this);
    }

    public JTextField getPlayerNameTextField() {
        return playerNameTextField;
    }

    public CharacterClass getSelectedCharacter(){
        return switch (playerData.get("className")) {
            case "Archer" -> new Archer(getPlayerNameTextField().getText(), 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E);
            case "Mage" -> new Mage(getPlayerNameTextField().getText(), 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E);
            case "Healer" -> new Healer(getPlayerNameTextField().getText(), 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E);
            default -> new Warrior(getPlayerNameTextField().getText(), 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E);
        };
    }
}
