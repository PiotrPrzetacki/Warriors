package com.company.classes;

import com.company.components.GameField;

public interface BaseClass {
    void attack(int direction, CharacterClass[] players, GameField gameField);
    void reduceHealth(int amount);
    int getX();
    int getY();
    void info();
}
