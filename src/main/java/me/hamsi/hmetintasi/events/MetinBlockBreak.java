package me.hamsi.hmetintasi.events;

import me.hamsi.hmetintasi.HMetinTasi;
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
        if (!(event.getBlock().getLocation().equals(plugin.getMetinManager().location()))) return;

        plugin.getMetinManager().decreaseHealth(1);

        player.sendMessage(String.valueOf(plugin.getMetinManager().getHealth()));

        event.setCancelled(true);
    }
}
