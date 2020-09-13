/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.maps.GameMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        GameMap map = SkyWars.getPlugin().getGameManager().getMap();
        if(!teams.isEmpty()) {
            teams.clear();
        }
        for (int i = 1; i < (map.getTeams() + 1); i++) {
            Team team = new Team();
            team.setId(i);
       
            teams.put(i, team);
        }
        SkyWars.getPlugin().getGameManager().getTeamInventory().updateInventory();
    }

    public boolean unfairTeams() {
        double av = Double.valueOf(Bukkit.getOnlinePlayers().size()) / Double.valueOf(teams.size());
        for (Team team : teams.values()) {
            double teamSize = team.getPlayers().size();
            if (teamSize == Math.ceil(av) || Math.floor(av) == teamSize) {
                return true;
            }
        }
        return false;
    }

    public Team getLowestTeam() {
        Team low = null;
        for (Team team : teams.values()) {
            if (low == null) {
                low = team;
            }
            if (team.getPlayers().size() < low.getPlayers().size()) {
                low = team;
            }
        }

        return low;
    }

    public int getTeamsWithPlayers() {
        int i = 0;
        for (Team team : teams.values()) {
            if (!team.getPlayers().isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public List<Player> getPlayersWithNoTeam() {
        List<Player> no = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (getTeam(player) == null) {
                no.add(player);
            }
        }
        return no;
    }

    public Team getTeam(Player player) {

        for (Team t : teams.values()) {
            if (t.getPlayers().contains(player)) {
                return t;
            }
        }
        return null;
    }

    public void setTeam(Player player, Team team) {
        Team before = getTeam(player);

        if (team != null) {
            if (!(team.getPlayers().size() >= SkyWars.getPlugin().getGameManager().getMap().getPerTeam())) {
                if(before != null) {
                    if(before.getId() == team.getId()) {
                        player.sendMessage(SkyWars.prefix + "§cDu bist bereits in diesem Team");
                        return;
                    }
                    before.removePlayer(player);
                }
                player.sendMessage(SkyWars.prefix + "§7Du bist Team §8#§e" + team.getId());
            } else {
                player.sendMessage(SkyWars.prefix + "§cDas Team ist voll");
            }
        } else if (before != null) {
            before.removePlayer(player);
        }
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }

}
