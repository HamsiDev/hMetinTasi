package me.hamsi.hmetintasi.until;

import com.hakan.core.HCore;
import com.hakan.core.hologram.HHologram;
import com.hakan.core.scheduler.HScheduler;
import me.hamsi.hmetintasi.HMetinTasi;
import org.bukkit.Bukkit;
import org.bukkit.Location;


public class MetinManager {

    private HMetinTasi plugin;

    public MetinManager(HMetinTasi plugin){
        this.plugin = plugin;
    }

    public int health = 20;
    public int time = 10;

    public boolean timeOn = false;


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
                addLine("Metin Stone").
                addLine(" ").
                addLine("Health "+this.getHealth()).showEveryone(true);

        return hologram;
    }

    public void timer(){
        HCore.syncScheduler().every(20).run(() -> plugin.getMetinManager().downTime(1));
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void downTime(int time) {
        plugin.getMetinManager().hologram().setLine(2, "Time "+plugin.getMetinManager().getTime());
        System.out.println(plugin.getMetinManager().getTime());
        if (plugin.getMetinManager().getTime() == 0){
            plugin.getMetinManager().setHealth(20);
            plugin.getMetinManager().hologram().setLine(2, "Health "+this.getHealth());
            plugin.getMetinManager().setTimeOn(false);
        }
        this.time -= time;
    }

    public boolean isTimeOn() {
        return timeOn;
    }

    public void setTimeOn(boolean timeOn) {
        this.timeOn = timeOn;
    }
}
