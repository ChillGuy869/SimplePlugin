package org.plugin.testPlugin2.events;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.plugin.testPlugin2.managers.Color;
import org.plugin.testPlugin2.managers.EntityManager;

public class ZombieSpawn implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();


        if (!item.getType().toString().endsWith("_SWORD")) {
            Location location = event.getPlayer().getLocation();
            World world = location.getWorld();
            Entity zombie = world.spawnEntity(location, EntityType.ZOMBIE);
            EntityManager.instance.zombies.add(zombie);
            event.getPlayer().sendMessage(Color.instance.convert("&d&1Beware! Zombie is here!"));
        }

    }
}


