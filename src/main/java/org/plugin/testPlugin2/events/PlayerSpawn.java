package org.plugin.testPlugin2.events;



import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;


// player spawn
public class PlayerSpawn implements Listener {

    private Dotenv config;
    public static HashMap<String, String> ranks = new HashMap<>();

    public PlayerSpawn(Dotenv config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        String adminName = config.get("NAME");

        Player player = event.getPlayer();
        player.teleport(event.getPlayer().getLocation());

        String name = player.getName();
        player.sendTitle("Hello, " + name + "!", null, 10, 70, 20);
        // player.sendMessage("Hello, " + name + "!");

        if (name.equals(adminName)) {
            ranks.put(name, "Admin");
        }


        else {
            ranks.put(name, "None");
        }
    }
}


