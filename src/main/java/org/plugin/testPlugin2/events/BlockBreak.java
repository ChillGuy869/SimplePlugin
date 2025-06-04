package org.plugin.testPlugin2.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.plugin.testPlugin2.managers.Color;

public class BlockBreak implements Listener {
    @EventHandler
    @Deprecated
    public void onBlockBreak(BlockBreakEvent event) {
        //event.getPlayer().setHealth(event.getPlayer().getHealth() - 1);
        event.getPlayer().damage(1);
        event.getPlayer().sendMessage(Color.instance.convert("&cТы &nлох&r&b&o!!!&r &a&kwhore"));
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Ты лох!!!");
        item.setItemMeta(itemMeta);
        item.setAmount(1);
        event.getPlayer().getInventory().addItem(item);
    }
}

