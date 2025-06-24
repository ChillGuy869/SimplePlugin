package org.plugin.testPlugin2;

import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.testPlugin2.commands.*;
import org.plugin.testPlugin2.events.*;
import org.plugin.testPlugin2.managers.Color;
import org.plugin.testPlugin2.events.PlayerSpawn.*;


// root
public final class TestPlugin2 extends JavaPlugin {
    private Dotenv config;
    private PlayerSpawn playerSpawn;




    @Override
    public void onEnable() {

        config = Dotenv.configure().load();
        playerSpawn = new PlayerSpawn(config, this);
        playerSpawn.loadRanks();
        getDataFolder().mkdirs();


        //getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        // getServer().getPluginManager().registerEvents(new ZombieSpawn(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathEvent(), this);
        getServer().getPluginManager().registerEvents(playerSpawn, this);
        getServer().getPluginManager().registerEvents(new PlayerFallDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new TeleportBowEvent(this), this);
        getServer().getPluginManager().registerEvents(new NonVisibleCommands(playerSpawn), this);
        getServer().getPluginManager().registerEvents(new PlayerLookingAtTheSunEvent(), this);

        JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());


        Bukkit.getScheduler().runTask(plugin, () -> {
            if (getCommand("sword") != null) getCommand("sword").setExecutor(new CoolSword());
            if (getCommand("feed_me") != null) getCommand("feed_me").setExecutor(new FeedPlayer());
            if (getCommand("stick") != null) getCommand("stick").setExecutor(new CoolStick());
            if (getCommand("heal") != null) getCommand("heal" ).setExecutor(new HealPlayerCommand());
            if (getCommand("tp") != null) getCommand("tp" ).setExecutor(new TeleportPlayer());
            if (getCommand("strike") != null) getCommand("strike" ).setExecutor(new PlayerLightningStrike());
            if (getCommand("armor") != null) getCommand("armor" ).setExecutor(new CoolArmor());
            if (getCommand("set_rank") != null) getCommand("set_rank" ).setExecutor(new SetRank(playerSpawn));
            if (getCommand("bow") != null) getCommand("bow" ).setExecutor(new TeleportBow(this));
            if (getCommand("villager") != null) getCommand("villager" ).setExecutor(new CustomVillager(this));
        });

        getServer().getConsoleSender().sendMessage(Color.instance.convert("&aServer is working"));
    }

    @Override
    public void onDisable() {
        playerSpawn.saveRanks();
        getServer().getConsoleSender().sendMessage(Color.instance.convert("&cServer is down"));
    }
}

