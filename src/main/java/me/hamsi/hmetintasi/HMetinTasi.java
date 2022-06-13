package me.hamsi.hmetintasi;

import com.hakan.core.HCore;
import me.hamsi.hmetintasi.commands.MetinCommand;
import me.hamsi.hmetintasi.events.MetinBlockBreak;
import me.hamsi.hmetintasi.until.ConfigManager;
import me.hamsi.hmetintasi.until.MetinManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HMetinTasi extends JavaPlugin {

    private ConfigManager manager;
    private MetinManager metinManager;

    @Override
    public void onEnable() {

        this.manager = new ConfigManager(this);
        this.metinManager = new MetinManager(this);

        this.getServer().getPluginManager().registerEvents(new MetinBlockBreak(this), this);

        HCore.registerCommands(new MetinCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public ConfigManager getManager() {
        return manager;
    }

    public MetinManager getMetinManager() {
        return metinManager;
    }
}
