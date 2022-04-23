package com.company.classes;

import com.company.components.GameField;

public interface BaseClass {
    void attack(int direction, CharacterClass[] players, GameField gameField);
    void restoreHealth(int amount);
    void loseHealth(int amount);
    void restoreMana(int amount);
    void loseMana(int amount);
    void levelUp();
    void info();
}
