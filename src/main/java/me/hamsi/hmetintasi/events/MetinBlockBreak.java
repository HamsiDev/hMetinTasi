package me.hamsi.hmetintasi.events;

import com.hakan.core.HCore;
import me.hamsi.hmetintasi.HMetinTasi;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MetinBlockBreak implements Listener {

    private HMetinTasi plugin;

    public MetinBlockBreak(HMetinTasi plugin){
        this.plugin = plugin;
    }


    public void DeleteSystem32(){

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        if (plugin.getManager().getLocationconfig().getString("Locations.World").isEmpty()) return;
        if (!(event.getBlock().getLocation().getWorld().getName().equalsIgnoreCase(player.getWorld().getName()))) return;
        if (!(event.getBlock().getLocation().equals(plugin.getMetinManager().getlocation()))) return;

        Location location = plugin.getMetinManager().getlocation();

        if (plugin.getMetinManager().getHealth() == 1){
            if (plugin.getMetinManager().isTimeOn()){

            }
            else {

                location.getBlock().setType(Material.BEDROCK);
                plugin.getMetinManager().setTime(30);
                plugin.getMetinManager().timer();
            }
        }



        Random random = new Random();
        int luck = random.nextInt(101);
        if (luck > 50 && luck < 100){

        } else if (luck > 40 && luck < 50) {

            player.damage(1.0);

        }else if (luck > 20 && luck < 50) {
            Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

            zombie.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
            zombie.setCustomName(ChatColor.RED+"Metin Guardian");
            zombie.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));

        }else if (luck > 5 && luck < 20) {

            player.getWorld().strikeLightning(player.getLocation());

        }else if (luck > 0 && luck < 5) {

            Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

            zombie.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
            zombie.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            zombie.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
            zombie.setCustomName(ChatColor.RED+"Metin Guardian");

            Skeleton skeleton = (Skeleton) location.getWorld().spawnEntity(location, EntityType.SKELETON);
            skeleton.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
            skeleton.setCustomName(ChatColor.RED+"Metin Guardian");
        }

        plugin.getMetinManager().decreaseHealth(1);

        plugin.getMetinManager().hologram().setLine(2, "Health "+plugin.getMetinManager().getHealth());

        player.sendMessage(String.valueOf(plugin.getMetinManager().getHealth()));

        event.setCancelled(true);
    }
}
