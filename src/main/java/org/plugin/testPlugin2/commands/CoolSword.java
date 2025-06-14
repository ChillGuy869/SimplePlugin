package org.plugin.testPlugin2.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.plugin.testPlugin2.events.PlayerSpawn;

public class CoolSword implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;

            String rank = PlayerSpawn.ranks.get(p.getName());
            if (!"Admin".equals(rank)) {
                p.sendMessage("This command can be used only by people with admin rank");
                return false;
            }

            ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.addEnchant(Enchantment.SHARPNESS, 1000, true);
            itemMeta.addEnchant(Enchantment.LOOTING, 1000, true);
            itemMeta.addEnchant(Enchantment.UNBREAKING, 1000, true);
            item.setItemMeta(itemMeta);

            p.getInventory().addItem(item);
            p.sendMessage("You got a nice sword");
        }
        return true;
    }
}

