package me.hamsi.hmetintasi.commands;

import com.hakan.core.command.HCommandAdapter;
import com.hakan.core.command.executors.base.BaseCommand;
import com.hakan.core.command.executors.sub.SubCommand;
import me.hamsi.hmetintasi.HMetinTasi;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@BaseCommand(
        name = "metintasi",
        description = "Metin Taşı Ana Komutu",
        usage = "/metintasi",
        aliases = {"ex"}
)
public class MetinCommand implements HCommandAdapter {

    private HMetinTasi plugin;
    public MetinCommand(HMetinTasi plugin){
        this.plugin = plugin;
    }

    @SubCommand(
            permission = "metintasi.op",
            permissionMessage = "Yetkin yok"
    )
    public void basitCommand(CommandSender sender, String[] args){
        sender.sendMessage("aynen");
    }

    @SubCommand(
            args = "kur",
            permission = "metintasi.op",
            permissionMessage = "Yetkin yok"
    )
    public void crateCommand(Player player, String[] args){
        Location location = player.getLocation().getBlock().getLocation();

        plugin.getManager().getLocationconfig().set("Locations.World", location.getWorld().getName());
        plugin.getManager().getLocationconfig().set("Locations.X", location.getBlockX());
        plugin.getManager().getLocationconfig().set("Locations.Y", location.getBlockY());
        plugin.getManager().getLocationconfig().set("Locations.Z", location.getBlockZ());
        plugin.getManager().saveConfig();

        location.getBlock().setType(Material.END_STONE);

        plugin.getMetinManager().hologram();

        player.sendMessage("Kuruldu");
    }

    @SubCommand(
            args = "kaldır",
            permission = "metintasi.op",
            permissionMessage = "Yetkin yok"
    )
    public void removeCommand(Player player, String[] args){
        Location location = plugin.getMetinManager().getlocation();

        plugin.getManager().getLocationconfig().set("Locations.World", "");
        plugin.getManager().getLocationconfig().set("Locations.X", "");
        plugin.getManager().getLocationconfig().set("Locations.Y", "");
        plugin.getManager().getLocationconfig().set("Locations.Z", "");
        plugin.getManager().saveConfig();

        plugin.getMetinManager().hologram().delete();
        location.getBlock().setType(Material.AIR);
        plugin.getMetinManager().setHealth(20);

        player.sendMessage("Kaldırıldı");
    }
}
