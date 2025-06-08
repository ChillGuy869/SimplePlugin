package org.plugin.testPlugin2.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class TeleportPlayer implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;

        if (!command.getName().equalsIgnoreCase("tp")) {
            return false;
        }


        if (args.length < 1) {
            p.sendMessage("Usage: /tp <playername>");
            return false;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayerExact(targetName);

        if (target != null && target.isOnline()) {
            p.teleport(target.getLocation());
            p.sendMessage("Teleported to " + target.getName());
        } else {
            p.sendMessage("Player " + targetName + " is not online.");
        }

        return true;
    }
}
