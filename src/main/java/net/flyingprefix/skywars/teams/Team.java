/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.teams;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

/**
 *
 * @author canschmitt
 */
public class Team {
    
    private int id;
    private List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
