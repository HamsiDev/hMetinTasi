package me.hamsi.hmetintasi.until;

import me.hamsi.hmetintasi.HMetinTasi;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private HMetinTasi plugin;

    FileConfiguration locationconfig, settingsconfig;

    public ConfigManager(HMetinTasi plugin){
        this.plugin = plugin;
        ManagerConfig();
    }

    public void ManagerConfig(){
        File configfile = new File(plugin.getDataFolder(), "locations.yml");

        if (!configfile.exists()){
            plugin.saveResource("locations.yml", true);
        }

        this.locationconfig = YamlConfiguration.loadConfiguration(configfile);

        File settingsfile = new File(plugin.getDataFolder(), "settings.yml");

        if (!settingsfile.exists()){
            plugin.saveResource("settings.yml", true);
        }

        this.settingsconfig = YamlConfiguration.loadConfiguration(settingsfile);
    }

    public void reloadConfig(){
        ManagerConfig();
    }

    public void saveConfig(){
        File settings = new File(plugin.getDataFolder(), "locations.yml");
        try {
            locationconfig.save(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getLocationconfig() {
        if (this.locationconfig == null)
            reloadConfig();
        return this.locationconfig;
    }

    public FileConfiguration getSettingsconfig() {
        if (this.settingsconfig == null)
            reloadConfig();
        return this.settingsconfig;
    }
}
