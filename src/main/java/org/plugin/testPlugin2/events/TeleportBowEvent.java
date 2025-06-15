package org.plugin.testPlugin2.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

public class TeleportBowEvent implements Listener {

    private final NamespacedKey key;

    public TeleportBowEvent(JavaPlugin plugin) {
        this.key = new NamespacedKey(plugin, "teleport_bow");
    }

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getBow() != null && event.getBow().getType() == Material.BOW)) return;

        ItemMeta meta = event.getBow().getItemMeta();
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
            // Mark the arrow with a custom name or metadata
            Arrow arrow = (Arrow) event.getProjectile();
            arrow.setCustomName("teleport_arrow");
        }
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow arrow)) return;
        if (!(arrow.getShooter() instanceof Player player)) return;

        if ("teleport_arrow".equals(arrow.getCustomName())) {
            player.teleport(arrow.getLocation());
            player.sendMessage("You teleported!");
            arrow.remove();
        }
    }
}

