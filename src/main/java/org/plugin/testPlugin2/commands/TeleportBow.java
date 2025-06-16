package org.plugin.testPlugin2.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.plugin.testPlugin2.events.PlayerSpawn;

public class TeleportBow implements CommandExecutor {

    private final NamespacedKey key;

    public TeleportBow(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "teleport_bow");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        String rank = PlayerSpawn.ranks.get(player.getName());
        if (!"Admin".equals(rank)) {
            player.sendMessage("This command can be used only by people with Admin rank");
            return true;
        }

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§bTeleport Bow");
            meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
            bow.setItemMeta(meta);
        }

        player.getInventory().addItem(bow);
        player.sendMessage("You've received the Teleport Bow!");
        return true;
    }
}
