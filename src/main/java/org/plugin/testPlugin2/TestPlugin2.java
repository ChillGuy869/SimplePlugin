package org.plugin.testPlugin2;

import org.bukkit.plugin.java.JavaPlugin;
import org.plugin.testPlugin2.commands.CoolStick;
import org.plugin.testPlugin2.commands.CoolSword;
import org.plugin.testPlugin2.commands.FeedPlayer;
import org.plugin.testPlugin2.commands.HealPlayerCommand;
import org.plugin.testPlugin2.events.BlockBreak;
import org.plugin.testPlugin2.events.EntityDeathEvent;
import org.plugin.testPlugin2.events.PlayerSpawn;
import org.plugin.testPlugin2.events.ZombieSpawn;
import org.plugin.testPlugin2.managers.Color;

import java.util.Objects;


public final class TestPlugin2 extends JavaPlugin {

    @Override
    public void onEnable() {
        //getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new ZombieSpawn(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerSpawn(), this);
        if (getCommand("sword") != null) getCommand("sword").setExecutor(new CoolSword());
        if (getCommand("feed_me") != null) getCommand("feed_me").setExecutor(new FeedPlayer());
        if (getCommand("stick") != null) getCommand("stick").setExecutor(new CoolStick());
        if (getCommand("heal") != null) getCommand("heal" ).setExecutor(new HealPlayerCommand());
        getServer().getConsoleSender().sendMessage(Color.instance.convert("&aServer is working"));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Color.instance.convert("&cServer is down"));
    }
}
