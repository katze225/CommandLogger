package me.katze.commandlogger.listener;

import me.katze.commandlogger.CommandLogger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().replaceFirst("/", "").replace("cmi", "").replaceFirst("^\\s+", "");

        if (!isListCommand(command)) {
            return;
        }

        CommandLogger.getInstance().logsManager.addLog(player, command);
    }

    private static boolean isListCommand(String command) {
        String replacedCommand = command.split(" ")[0];
        List<String> commandList = CommandLogger.getInstance().COMMANDS;

        for (String string : commandList) {
            if (string.equalsIgnoreCase(replacedCommand)) {
                return true;
            }
        }
        return false;
    }

}
