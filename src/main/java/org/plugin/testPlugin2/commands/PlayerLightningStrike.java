package org.plugin.testPlugin2.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.plugin.testPlugin2.events.PlayerSpawn;

public class PlayerLightningStrike implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                          @NotNull String label, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player p = (Player) sender;

        String rank = PlayerSpawn.ranks.get(p.getName());
        if (!"Admin".equals(rank)) {
            p.sendMessage("This command can be used only by people with Admin rank");
            return false;
        }


        if (!command.getName().equalsIgnoreCase("strike")) {
            return false;
        }


        if (args.length < 1) {
            // p.sendMessage("Usage: /strike <playername>");
            return false;
        }


        String targetName = args[0];
        Player target = Bukkit.getPlayerExact(targetName);
        Location targetLocation = p.getLocation();

        if (target != null && target.isOnline()) {
            for (int i = 0; i < 4; i++) {
                targetLocation.getWorld().strikeLightning(targetLocation);
            }
            p.sendMessage("Player " + target.getName() + " just got striked by a lightning! 😈");
        } else {
            p.sendMessage("Player " + targetName + " is not online.");
        }

        return true;
    }
}