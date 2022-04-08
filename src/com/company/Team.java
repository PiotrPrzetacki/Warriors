package com.company;

import com.company.classes.CharacterClass;
import com.company.classes.arenas.Arena;

import java.util.List;

public class Team {
    private CharacterClass[] teamMembers;
    private Arena arena;

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public Team(CharacterClass... members) {
        teamMembers = new CharacterClass[members.length];
        for (int i = 0; i < teamMembers.length; i++) {
            teamMembers[i] = members[i];
        }
    }

    public Team(List<CharacterClass> members){
        teamMembers = new CharacterClass[members.size()];
        for (int i = 0; i < teamMembers.length; i++) {
            teamMembers[i] = members.get(i);
        }
    }

    public CharacterClass[] getTeamMembers() {
        return teamMembers;
    }

    public void info(){
        for(CharacterClass teamMembers : teamMembers){
            teamMembers.info();
        }
    }
}
