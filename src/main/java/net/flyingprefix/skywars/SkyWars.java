/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars;

import net.flyingprefix.skywars.commands.ForceMapCommand;
import net.flyingprefix.skywars.commands.SetupCommand;
import net.flyingprefix.skywars.game.GameManager;
import net.flyingprefix.skywars.game.GameState;
import net.flyingprefix.skywars.listeners.InventoryClickListener;
import net.flyingprefix.skywars.listeners.PlayerInteractListener;
import net.flyingprefix.skywars.listeners.PlayerJoinListener;
import net.flyingprefix.skywars.maps.GameMap;
import net.flyingprefix.skywars.maps.MapManager;
import net.flyingprefix.skywars.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author canschmitt
 */
public class SkyWars extends JavaPlugin {

    public static SkyWars plugin;
    public static String prefix;
    
    private MapManager mapManager;
    private GameManager gameManager;
    private TeamManager teamManager;
    
    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
    
    }
    
    private void init() {
        plugin = this;
        prefix = "§9SkyWars§8> ";
        
        this.mapManager = new MapManager();
        this.teamManager = new TeamManager();
        this.gameManager = new GameManager(this);
        
        
        new SetupCommand();
        new ForceMapCommand();
        
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        
        GameMap m = this.plugin.getMapManager().getRandomMap();
        System.out.println(m.getName());
    }

    public static String getPrefix() {
        return prefix;
    }
    
    public MapManager getMapManager() {
        return mapManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }
    
    

    public static SkyWars getPlugin() {
        return plugin;
    }
    
}
