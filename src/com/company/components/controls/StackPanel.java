package com.company.components.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public abstract class StackPanel<E> extends JPanel {
    protected JButton nextBtn;
    protected JButton prevBtn;
    protected List<E> elements;
    protected int currentIndex;

    public StackPanel(JButton nextBtn, JButton prevBtn, List<E> elements) {
        this.nextBtn = nextBtn;
        this.prevBtn = prevBtn;
        this.currentIndex = 0;
        this.elements = elements;

        this.nextBtn.addActionListener(this::handleChangeElement);
        this.prevBtn.addActionListener(this::handleChangeElement);
    }

    protected abstract void refresh();

    private void handleChangeElement(ActionEvent actionEvent){
        if(actionEvent.getSource()==nextBtn){
            changeCurrentElementIndex("next");
        }
        else if(actionEvent.getSource()==prevBtn){
            changeCurrentElementIndex("previous");
        }
        refresh();
    }

    protected void changeCurrentElementIndex(String which){
        if (which=="next") {
            if(currentIndex != elements.size()-1) currentIndex++;
            else currentIndex = 0;
        }
        else if(which=="previous"){
            if(currentIndex != 0) currentIndex--;
            else currentIndex = elements.size()-1;
        }
    }
}
