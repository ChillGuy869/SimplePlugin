package org.plugin.testPlugin2.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.plugin.testPlugin2.events.PlayerSpawn;

public class TeleportPlayer implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player p = (Player) sender;

        String rank = PlayerSpawn.ranks.get(p.getName());
        if (!"VIP".equals(rank) && !"Admin".equals(rank)) {
            p.sendMessage("This command can be used only by people with VIP or Admin rank");
            return true;
        }

        if (!command.getName().equalsIgnoreCase("tp")) {
            return false;
        }


        if (args.length < 1) {
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
