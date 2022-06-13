package me.hamsi.hmetintasi.until;

import com.hakan.core.HCore;
import com.hakan.core.hologram.HHologram;
import me.hamsi.hmetintasi.HMetinTasi;
import org.bukkit.Bukkit;
import org.bukkit.Location;


public class MetinManager {

    private HMetinTasi plugin;

    public MetinManager(HMetinTasi plugin){
        this.plugin = plugin;
    }

    public int health = 20;


    public Location getlocation(){
        Location location = new Location(Bukkit.getWorld(plugin.getManager().getLocationconfig().getString("Locations.World")),
                plugin.getManager().getLocationconfig().getDouble("Locations.X"),
                plugin.getManager().getLocationconfig().getDouble("Locations.Y"),
                plugin.getManager().getLocationconfig().getDouble("Locations.Z"));

        return location;
    }

    public HHologram hologram(){
        Location location = plugin.getMetinManager().getlocation();
        location.add(0.5, 1, 0.5);

        HHologram hologram = HCore.createHologram("Metin", location).
                addLine("Metin Taşı").
                addLine(" ").
                addLine("Can "+this.getHealth()).showEveryone(true);

        return hologram;
    }



    public int getHealth() {
        return health;
    }

    public void setHealth(int metinHealth) {
        this.health = metinHealth;
    }

    public void decreaseHealth(int metinHealth) {
        this.health -= metinHealth;
    }
}
