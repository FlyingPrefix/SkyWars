/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.listeners;

import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author cansc
 */
public class PlayerJoinListener implements Listener {
    
    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        event.setJoinMessage(null);
        
        if(!SkyWars.getPlugin().getMapManager().isReady()) {
            return;
        }
        
        player.getInventory().clear();
        player.getEquipment().clear();
        
        if(SkyWars.plugin.getGameManager().getGameState().equals(GameState.LOBBY)) {
            Bukkit.broadcastMessage(SkyWars.prefix + "§a"+player.getName() + " §7ist dem Server beigetreten");
            
            player.teleport(SkyWars.plugin.getMapManager().getLobbyLocation());
            player.getInventory().setItem(0, SkyWars.plugin.getGameManager().getTeamChangerItem());
            
            if(Bukkit.getOnlinePlayers().size() >= 1 && !SkyWars.getPlugin().getGameManager().getLobbyPhase().isStarted()) {
                SkyWars.getPlugin().getGameManager().getLobbyPhase().runTaskTimer(SkyWars.plugin, 20, 20);
            }
        }
    }
    
}
