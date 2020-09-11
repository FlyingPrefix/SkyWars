/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.teams;

import java.util.HashMap;
import java.util.Map;
import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.maps.GameMap;

/**
 *
 * @author canschmitt
 */
public class TeamManager {
    
    private Map<Integer, Team> teams;

    public TeamManager() {
        this.teams = new HashMap<>();
    }
    
    public void loadTeams() {
        //alle spieler aus team werfen
        GameMap map = SkyWars.getPlugin().getGameManager().getMap();
        for(int i = 1; i < (map.getTeams() + 1); i++) {
            System.out.println("Team#"+i);
        }
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }
    
    
    
}
