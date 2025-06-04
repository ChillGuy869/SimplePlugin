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

public class CoolStick implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            ItemStack item = new ItemStack(Material.STICK);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.addEnchant(Enchantment.KNOCKBACK, 1000, true);
            item.setItemMeta(itemMeta);
            p.getInventory().addItem(item);
            p.sendMessage("You got a nice stick");
        }
        return true;
    }
}
