/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.inventories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.teams.Team;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author cansc
 */
public class TeamInventory {
    
    private Inventory inventory;

    public TeamInventory() {
        this.inventory = Bukkit.createInventory(null, 9, "§9Team wechseln");
    }
    
    public void updateInventory() {
        
        this.fillGlass();
        if(SkyWars.getPlugin().getTeamManager().getTeams().isEmpty()) {
            System.out.println("NO TEAM");
            return;
        }
        for(Team team : SkyWars.getPlugin().getTeamManager().getTeams().values()) {
            ItemStack item = new ItemStack(Material.WOOL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§7Team§8#§e"+team.getId());
            
            meta.setLore(getLore(team));
            item.setItemMeta(meta);
            
            this.inventory.setItem(team.getId() - 1,  item);
            this.inventory.getViewers().forEach(all -> {
                Player player = (Player) all;
                player.updateInventory();
            });
        }
    }
    
    public List<String> getLore(Team team) {
        List<String> lore = new ArrayList<>();
        IntStream.range(0, SkyWars.getPlugin().getGameManager().getMap().getPerTeam()).forEach(i -> {
            Player player = team.getPlayers().get(i);
            lore.add("§e"+(i+1)+"§8#"+(player == null ? "§cLeer" : "§9"+player.getName()));
        });
        
        return lore;
    }
    
    public void fillGlass() {
        for(int i = 0; i < this.inventory.getSize(); i++) {
            this.inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
    
}
