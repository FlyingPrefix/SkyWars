/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.commands;

import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.game.GameState;
import net.flyingprefix.skywars.maps.GameMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author canschmitt
 */
public class ForceMapCommand implements CommandExecutor{ 

    public ForceMapCommand() {
        SkyWars.getPlugin().getCommand("forcemap").setExecutor(this);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if(SkyWars.getPlugin().getGameManager().getGameState().equals(GameState.LOBBY) || SkyWars.getPlugin().getGameManager().getLobbyPhase().getTimeLeft() > 9) {
            if(args.length == 1) {
                forcemap(player, args[0]);
            } else {
                listMaps(player);
            }
        } else {
            player.sendMessage(SkyWars.prefix + "§cDu kannst die Map nicht mehr ändern");
        }
        return false;
    }
    
    private void forcemap(Player player, String map) {
        GameMap gameMap = SkyWars.getPlugin().getMapManager().getMap(map);
        if(gameMap == null) {
            player.sendMessage(SkyWars.prefix + "§cDie Map gibt es nicht");
            return;
        }
        
        SkyWars.getPlugin().getGameManager().setMap(gameMap);
        player.sendMessage(SkyWars.prefix + "§7Die Map wurde zu §a" + map + " §7geändert");
    }
    
    public void listMaps(Player player) {
        StringBuilder builder = new StringBuilder();
        SkyWars.getPlugin().getMapManager().getMaps().values().forEach(map -> {
            if(builder.length() > 0) {
                builder.append("§7, §a");
            }
            builder.append("§a"+map.getName());
        });
        
        player.sendMessage(SkyWars.prefix + "§7Spielbare §aMaps§8:");
        player.sendMessage(builder.toString());
    }
}
