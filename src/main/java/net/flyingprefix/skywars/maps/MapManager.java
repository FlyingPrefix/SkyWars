/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.maps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.flyingprefix.skywars.SkyWars;
import net.flyingprefix.skywars.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author canschmitt
 */
public class MapManager {

    public File file;
    public YamlConfiguration cfg;

    private Map<String, GameMap> maps;
    
    public MapManager() {
        this.file = new File(SkyWars.getPlugin().getDataFolder(), "maps.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
        
        this.maps = new HashMap<>();

        this.loadMaps();
    }

    public void loadMaps() {
        if (cfg.contains("Maps")) {
            for (String name : cfg.getConfigurationSection("Maps").getKeys(false)) {
                GameMap map = new GameMap();
                map.setName(name);
                map.setTeams(cfg.getInt("Maps."+name+".Teams"));
                map.setPerTeam(cfg.getInt("Maps."+name+".PerTeam"));
                ArrayList<Location> spawns = new ArrayList<Location>();
                for (String locs : cfg.getStringList("Maps." + name + ".Spawns")) {
                    spawns.add(LocationUtil.convertStringToLocation(locs));
                }
                map.setSpawns(spawns);
                Collections.shuffle(map.getSpawns());
                maps.put(name, map);
            }
        }
    }
    
    public GameMap getMap(String name) {
        GameMap map = maps.get(name);
        if(map != null) {
            return map;
        }
        
        return null;
    }
    

    public void setLobby(Location loc) {
        String s = LocationUtil.convertLocationToString(loc);
        cfg.set("Lobby", s);
        save();
    }

    public boolean addMap(String name, int teams, int perTeam) {
        if (!exists(name)) {
            cfg.set("Maps." + name + ".Teams", teams);
            cfg.set("Maps." + name + ".PerTeam", perTeam);
            save();
            return true;
        } else {
            return false;
        }
    }

    public void addSpawn(String map, Location loc) {
        List<String> s = getSpawns(map);
        s.add(LocationUtil.convertLocationToString(loc));

        cfg.set("Maps." + map + ".Spawns", s);
        save();
    }

    public GameMap getRandomMap() {
        if(maps.size() == 0) return null;
        
        int rnd = new Random().nextInt(maps.size());
        GameMap map = (GameMap) maps.values().toArray()[rnd];
        SkyWars.getPlugin().getGameManager().setMap(map);
        return map;
    }
    
    public int getTeams(String name) {
        return cfg.getInt("Maps." + name + ".Teams");
    }

    public List<String> getSpawns(String name) {
        return cfg.getStringList("Maps." + name + ".Spawns");
    }

    public void save() {
        try {
            cfg.save(file);
        } catch (IOException ex) {
            Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean exists(String name) {
        return cfg.contains("Maps." + name);
    }

    public YamlConfiguration getCfg() {
        return cfg;
    }

    public File getFile() {
        return file;
    }

    public Map<String, GameMap> getMaps() {
        return maps;
    }
    
    

}
