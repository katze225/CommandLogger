package me.katze.commandlogger;

import me.katze.commandlogger.listener.CommandListener;
import me.katze.commandlogger.manager.LogsManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class CommandLogger extends JavaPlugin {
    public final List<String> COMMANDS = getConfig().getStringList("commands");

    private static CommandLogger instance;
    private FileConfiguration config;
    public LogsManager logsManager;

    @Override
    public void onEnable() {
        instance = this;
        config = getConfig();

        loadConfig();
        loadListeners();

        logsManager = new LogsManager();
    }

    @Override
    public void onDisable() {}

    public void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static CommandLogger getInstance() {
        return instance;
    }
}
