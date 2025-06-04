package org.plugin.testPlugin2.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.plugin.testPlugin2.managers.EntityManager;


public class EntityDeathEvent implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getType().equals(EntityType.ZOMBIE))
            return;

        if (!(event.getDamager().getType().equals(EntityType.PLAYER)))
            return;

        if (((LivingEntity) event.getEntity()).getHealth() - event.getDamage() > 0)
            return;


        Player player = (Player) event.getDamager();


        Entity mob = event.getEntity();



        if (EntityManager.instance.zombies.contains(mob)) {
            ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
            player.getInventory().addItem(item);
        }

    }
}
