package org.plugin.testPlugin2.events;



import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.lang.reflect.Type;


// player spawn
public class PlayerSpawn implements Listener {

    private final File file;
    private Dotenv config;
    public static HashMap<String, String> ranks = new HashMap<>();
    private final JavaPlugin plugin;

    public PlayerSpawn(Dotenv config, JavaPlugin plugin) {
        this.config = config;
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "ranks.json");
    }

    public void saveRanks() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(ranks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRanks() {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        if (!file.exists()) {
            ranks = new HashMap<>();
            return;
        }
        try (FileReader reader = new FileReader(file)) {
            ranks = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            ranks = new HashMap<>();
        }
    }




    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        String adminName = config.get("NAME");

        Player player = event.getPlayer();
        player.teleport(event.getPlayer().getLocation());

        String name = player.getName();
        player.sendTitle("Hello, " + name + "!", null, 10, 70, 20);
        // player.sendMessage("Hello, " + name + "!");

        if (!ranks.containsKey(name)) {
            if (name.equals(adminName)) {
                ranks.put(name, "Admin");
            } else {
                ranks.put(name, "None");
            }
            saveRanks();
        }
    }
}


