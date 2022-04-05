package com.company.classes.arenas;

import com.company.Team;

import java.awt.*;

public abstract class Arena implements BaseArena{

    protected String arenaName;
    protected String arenaImageURL;
    protected Color backgroundColor;

    private boolean isOpened = false;

    public boolean open(Team team) {
        if (team.getTeamMembers().length < 1) {
            System.out.println("Not enough party members!");
            isOpened = false;
        } else {
            System.out.println("Welcome, heroes");
            isOpened = true;
        }
        return isOpened;
    }

    public String getArenaName() {
        return arenaName;
    }
}
