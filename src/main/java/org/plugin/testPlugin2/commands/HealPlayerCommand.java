package org.plugin.testPlugin2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.plugin.testPlugin2.events.PlayerSpawn;

public class HealPlayerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            String rank = PlayerSpawn.ranks.get(p.getName());
            if (!"VIP".equals(rank) && !"Admin".equals(rank)) {
                p.sendMessage("This command can be used only by people with VIP or Admin rank");
                return true;
            }

            p.setHealth(20);
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 5, false, false));

            p.sendMessage("You are healed now!");
        }
        return true;
    }
}



