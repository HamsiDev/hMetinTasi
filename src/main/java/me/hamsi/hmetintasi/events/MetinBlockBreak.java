package me.hamsi.hmetintasi.events;

import com.hakan.core.HCore;
import me.hamsi.hmetintasi.HMetinTasi;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MetinBlockBreak implements Listener {

    private HMetinTasi plugin;

    public MetinBlockBreak(HMetinTasi plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        if (plugin.getManager().getLocationconfig().getString("Locations.World").isEmpty()) return;
        if (!(event.getBlock().getLocation().getWorld().getName().equalsIgnoreCase(player.getWorld().getName()))) return;
        if (!(event.getBlock().getLocation().equals(plugin.getMetinManager().getlocation()))) return;
        if (plugin.getMetinManager().getHealth() == 1){
            if (plugin.getMetinManager().isTimeOn()){

            }
            else {
                Location location = plugin.getMetinManager().getlocation();
                location.getBlock().setType(Material.BEDROCK);
                plugin.getMetinManager().timer();
            }
        }

        plugin.getMetinManager().decreaseHealth(1);

        plugin.getMetinManager().hologram().setLine(2, "Health "+plugin.getMetinManager().getHealth());

        player.sendMessage(String.valueOf(plugin.getMetinManager().getHealth()));

        event.setCancelled(true);
    }
}
