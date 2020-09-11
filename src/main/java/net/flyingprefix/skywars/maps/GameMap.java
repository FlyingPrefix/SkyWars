/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.maps;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;

/**
 *
 * @author canschmitt
 */
public class GameMap {
    
    private String name;
    private int teams, perTeam;
    private List<Location> spawns = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public int getPerTeam() {
        return perTeam;
    }

    public void setPerTeam(int perTeam) {
        this.perTeam = perTeam;
    }

    public void setSpawns(List<Location> spawns) {
        this.spawns = spawns;
    }

    public void setTeams(int teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public List<Location> getSpawns() {
        return spawns;
    }

    public int getTeams() {
        return teams;
    }
    
    
    
}
