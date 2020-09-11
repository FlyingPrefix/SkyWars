/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.flyingprefix.skywars.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 *
 * @author canschmitt
 */
public class LocationUtil {
    public static String convertLocationToString(Location loc) {
        return loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch() + ";" + loc.getWorld().getName();
    }
    
    public static Location convertStringToLocation(String loc) {
        String[] split = loc.split(";");
        double x = Double.valueOf(split[0]);
        double y = Double.valueOf(split[1]);
        double z = Double.valueOf(split[2]);
        float yaw = Float.valueOf(split[3]);
        float pitch = Float.valueOf(split[4]);
        
        Location location = new Location(Bukkit.getWorld(split[5]), x, y, z, yaw, pitch);
        return location;
    }
}
