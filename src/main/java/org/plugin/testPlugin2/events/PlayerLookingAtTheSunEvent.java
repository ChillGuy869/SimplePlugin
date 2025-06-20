package org.plugin.testPlugin2.events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerLookingAtTheSunEvent implements Listener {

    private final Map<UUID, Long> eventStartTimes = new HashMap<>();

    @EventHandler
    public void onPlayerLooksAtTheSun(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        World world = p.getWorld();
        long time = world.getTime();

        if (time >= 0 && time <= 12000 && !world.hasStorm() && !world.isThundering()) { // Daytime only and weather is clear(visible sun)
            Vector lookDirection = p.getLocation().getDirection().normalize();
            double pitchToSky = lookDirection.getY();

            // Looking mostly upwards
            if (pitchToSky > 0.8) {
                UUID uuid = p.getUniqueId();

                if (!eventStartTimes.containsKey(uuid)) {
                    eventStartTimes.put(uuid, System.currentTimeMillis());
                } else {
                    long elapsed = System.currentTimeMillis() - eventStartTimes.get(uuid);
                    if (elapsed >= 5_000) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10 * 20, 5, false, false));
                        p.sendMessage("Don't stare at the sun too long!");
                        eventStartTimes.remove(uuid);
                    }
                }
            } else {
                // Reset timer if player stops looking up
                eventStartTimes.remove(p.getUniqueId());
            }
        } else {
            // Not daytime, reset if stored
            eventStartTimes.remove(p.getUniqueId());
        }
    }

}
