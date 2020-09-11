/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.commands;

import net.flyingprefix.skywars.SkyWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author canschmitt
 */
public class SetupCommand implements CommandExecutor {

    public SetupCommand() {
        SkyWars.getPlugin().getCommand("setup").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length == 4) {
                switch (args[0]) {
                    case "create":
                        String map = args[1];
                        int teams = Integer.valueOf(args[2]);
                        int perTeam = Integer.valueOf(args[3]);
                        
                        if(SkyWars.getPlugin().getMapManager().addMap(map, teams, perTeam)) {
                            player.sendMessage(SkyWars.getPrefix() + "§aMap wurde erstellt");
                        } else {
                            player.sendMessage(SkyWars.getPrefix() + "§cDie Map existiert bereits");
                        }
                        break;
                    default:
                        break;
                }
            } else if(args.length == 1) {
                switch(args[0]) {
                    case "lobby": 
                        SkyWars.getPlugin().getMapManager().setLobby(player.getLocation());
                        player.sendMessage(SkyWars.prefix + "§aLobby gesetzt");
                        break;
                    default:
                        break;
                }
            } else if(args.length == 2) {
                switch(args[0]) {
                    case "spawn":
                        String map = args[1];
                        int teams = SkyWars.getPlugin().getMapManager().getTeams(map);
                        int spawns = SkyWars.getPlugin().getMapManager().getSpawns(map).size();
                        
                        int diff = teams - spawns;
                        
                        if(diff <= 0) {
                            player.sendMessage(SkyWars.prefix + "§cDu kannst keine Spawns mehr setzen");
                        } else {
                            SkyWars.getPlugin().getMapManager().addSpawn(map, player.getLocation());
                            player.sendMessage(SkyWars.prefix + "§aSpawn wurde gesetzt");
                            if((diff - 1) == 0) {
                                player.sendMessage(SkyWars.prefix + "§aEs wurden alle Spawns gesetzt");
                                return false;
                            }
                            player.sendMessage(SkyWars.prefix + "§7Es müssen noch §e" + (diff - 1) + " §7Spawn gesetzt werden");
                        }
                        break;
                }
            }
        }
        return false;
    }

}
