package com.company.classes;

public interface BaseClass {
    void attack(CharacterClass attackedPlayer);
    void restoreHealth(int amount);
    void loseHealth(int amount);
    void restoreMana(int amount);
    void loseMana(int amount);
    void levelUp();
    void info();
}
