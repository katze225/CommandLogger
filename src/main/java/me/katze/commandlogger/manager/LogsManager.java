package me.katze.commandlogger.manager;

import me.katze.commandlogger.CommandLogger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsManager {

    private static File file;
    private static YamlConfiguration config;

    public LogsManager() {
        file = new File("plugins/" + CommandLogger.getInstance().getPluginMeta().getName(), "logs.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void addLog(Player player, String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM HH:mm:ss");
        String timestamp = dateFormat.format(new Date());

        String logKey = player.getName() + "." + timestamp;

        config.set(logKey, message);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
