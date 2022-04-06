package com.company;

import com.company.classes.CharacterClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel {
    private Team team;
    private CharacterClass[] players;
    public GameField(Team team) {
        this.team = team;
        this.players = team.getTeamMembers();

        setFocusable(true);
        addKeyListener(new FieldKeyListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CharacterClass player : players) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
            g.drawString(""+player.getHealthPoints(), player.getX(), player.getY()+12);
            g.drawString("steps", player.getX(), player.getY() + 26);
            System.out.println("health points =   " + player.getHealthPoints());
        }
    }

    public class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            for (CharacterClass player : players) {
                if (key == player.getLeftKey()) {
                    //player.setX(player.getX() - Constants.CHARACTER_WIDTH);
                    player.left();
                }
                if (key == player.getRightKey()) {
                    //player.setX(player.getX() + Constants.CHARACTER_WIDTH);
                    player.right();
                }
                if (key == player.getUpKey()) {
                    //player.setY(player.getY() - Constants.CHARACTER_WIDTH);
                    player.up();
                }
                if (key == player.getDownKey()) {
                    //player.setY(player.getY() + Constants.CHARACTER_WIDTH);
                    player.down();
                }
                if (key == player.getLeftAttackKey()) {
                    player.setAttackLeftImage();

                    player.setAttackLeftImage();

                    player.attack("left", players);
                    checkGameOver();


                    //timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200
                    );
                }
                if (key == player.getRightAttackKey()) {
                    player.setAttackRightImage();

                    player.attack("right", players);
                    checkGameOver();

                    //timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200
                    );
                }
            }
            repaint();
        }
    }
    private void checkGameOver(){
        for(CharacterClass player : players){
            if(player.getHealthPoints()<=0){
                setPlayersCanMove(false);

                break;
            }
        }
    }

    private void setPlayersCanMove(boolean canMove){
        for(CharacterClass player : players){
            player.setCanMove(canMove);
        }
    }
}
