/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.game;

import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.inventories.TeamInventory;
import net.flyingprefix.skywars.maps.GameMap;
import net.flyingprefix.skywars.phase.FriendlyPhase;
import net.flyingprefix.skywars.phase.LobbyPhase;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author canschmitt
 */
public class GameManager {
    
    private GameMap map;
    private GameState gameState = GameState.LOBBY;
    
    private LobbyPhase lobbyPhase;
    private FriendlyPhase friendlyPhase;
    
    private ItemStack teamChangerItem;
    
    private TeamInventory teamInventory;
    
    public SkyWars plugin;
    
    public GameManager(SkyWars plugin) {
        this.plugin = plugin;
        this.lobbyPhase = new LobbyPhase(SkyWars.getPlugin().getTeamManager());
        this.friendlyPhase = new FriendlyPhase();
        this.teamInventory = new TeamInventory();
        
        ItemStack item = new ItemStack(Material.BED);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Team wechseln");
        item.setItemMeta(meta);
        this.teamChangerItem = item;
    }

    public ItemStack getTeamChangerItem() {
        return teamChangerItem;
    }

    public void setTeamChangerItem(ItemStack teamChangerItem) {
        this.teamChangerItem = teamChangerItem;
    }

    public TeamInventory getTeamInventory() {
        return teamInventory;
    }
    
    
    
    

    public GameState getGameState() {
        return gameState;
    }

    public GameMap getMap() {
        return map;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setMap(GameMap map) {
        this.map = map;
        SkyWars.getPlugin().getTeamManager().loadTeams();
    }

    public FriendlyPhase getFriendlyPhase() {
        return friendlyPhase;
    }

    public LobbyPhase getLobbyPhase() {
        return lobbyPhase;
    }
    
    
}
