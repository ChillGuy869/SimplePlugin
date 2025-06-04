package org.plugin.testPlugin2.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerSpawn implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(event.getPlayer().getLocation());
        String name = player.getName();
        player.sendTitle("Hello, " + name + "!", null, 10, 70, 20);
        // player.sendMessage("Hello, " + name + "!");
    }
}
