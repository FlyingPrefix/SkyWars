/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.listeners;

import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 *
 * @author cansc
 */
public class InventoryClickListener implements Listener {
    
    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        
        if(event.getClickedInventory().getTitle().contains("Team wechseln")) {
            Integer teamId = Integer.valueOf(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).split("#")[1]);
            Team team = SkyWars.getPlugin().getTeamManager().getTeams().get(teamId);
            
            SkyWars.getPlugin().getTeamManager().setTeam(player, team);
            player.closeInventory();
        }
    }
    
}
