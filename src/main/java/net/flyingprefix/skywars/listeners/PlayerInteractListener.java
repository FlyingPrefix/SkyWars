/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.listeners;

import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.game.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author cansc
 */
public class PlayerInteractListener implements Listener {
    
    @EventHandler
    public void on(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getItem() != null || event.getItem().getItemMeta() != null) {
                String dis = event.getItem().getItemMeta().getDisplayName();
                if(SkyWars.getPlugin().getGameManager().getGameState() == GameState.LOBBY) {
                    if(dis.equals("§9Team wechseln")) {
                        player.openInventory(SkyWars.getPlugin().getGameManager().getTeamInventory().getInventory());
                    }
                }
            }
        }
    }
    
}
