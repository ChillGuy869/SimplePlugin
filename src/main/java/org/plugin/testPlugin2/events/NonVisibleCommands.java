package org.plugin.testPlugin2.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import java.util.Collection;

public class NonVisibleCommands implements Listener {

    private final PlayerSpawn playerSpawn;

    public NonVisibleCommands(PlayerSpawn playerSpawn) {
        this.playerSpawn = playerSpawn;
    }

    @EventHandler
    public void onPlayerSendCommand(PlayerCommandSendEvent event) {

        Player player = event.getPlayer();
        String rank = PlayerSpawn.ranks.getOrDefault(player.getName(), "None");
        Collection<String> commands = event.getCommands();

        System.out.println("[DEBUG] Player " + player.getName() + " has rank: " + rank);

        if (!rank.equalsIgnoreCase("Admin")) {
            commands.remove("strike");
            commands.remove("bow");
            commands.remove("sword");
        }

        if (!rank.equalsIgnoreCase("VIP") && !rank.equalsIgnoreCase("Admin")) {
            commands.remove("tp");
            commands.remove("heal");
            commands.remove("armor");
            commands.remove("set_rank");
        }
    }
}


// FAILED
// This event does not work properly. The commands are not shown, meaning no matter what rank you have,
// it will not show the commands for admins, if admin uses it, they will not see some command, but will be able
// to use it

// the original goal was to make an event that hides particular commands from users with particular ranks