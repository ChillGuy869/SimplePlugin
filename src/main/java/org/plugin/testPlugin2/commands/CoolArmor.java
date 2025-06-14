package org.plugin.testPlugin2.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.plugin.testPlugin2.events.PlayerSpawn;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoolArmor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        String rank = PlayerSpawn.ranks.get(player.getName());
        if (!"VIP".equals(rank) && !"Admin".equals(rank)) {
            player.sendMessage("This command can be used only by people with VIP or Admin rank");
            return false;
        }

        // Define armor and their enchantments
        giveItem(player, Material.NETHERITE_HELMET, new LinkedHashMap<Enchantment, Integer>() {{
            put(Enchantment.PROJECTILE_PROTECTION, 1000);
            put(Enchantment.BLAST_PROTECTION, 1000);
            put(Enchantment.FIRE_PROTECTION, 1000);
            put(Enchantment.UNBREAKING, 1000);
            put(Enchantment.THORNS, 1000);
            put(Enchantment.MENDING, 1000);
            put(Enchantment.AQUA_AFFINITY, 1000);
            put(Enchantment.RESPIRATION, 1000);
        }});

        giveItem(player, Material.NETHERITE_CHESTPLATE, new LinkedHashMap<Enchantment, Integer>() {{
            put(Enchantment.PROJECTILE_PROTECTION, 1000);
            put(Enchantment.BLAST_PROTECTION, 1000);
            put(Enchantment.FIRE_PROTECTION, 1000);
            put(Enchantment.UNBREAKING, 1000);
            put(Enchantment.THORNS, 1000);
            put(Enchantment.MENDING, 1000);
        }});

        giveItem(player, Material.NETHERITE_LEGGINGS, new LinkedHashMap<Enchantment, Integer>() {{
            put(Enchantment.PROJECTILE_PROTECTION, 1000);
            put(Enchantment.BLAST_PROTECTION, 1000);
            put(Enchantment.FIRE_PROTECTION, 1000);
            put(Enchantment.UNBREAKING, 1000);
            put(Enchantment.THORNS, 1000);
            put(Enchantment.MENDING, 1000);
            put(Enchantment.SWIFT_SNEAK, 1000);
        }});

        giveItem(player, Material.NETHERITE_BOOTS, new LinkedHashMap<Enchantment, Integer>() {{
            put(Enchantment.PROJECTILE_PROTECTION, 1000);
            put(Enchantment.BLAST_PROTECTION, 1000);
            put(Enchantment.FIRE_PROTECTION, 1000);
            put(Enchantment.UNBREAKING, 1000);
            put(Enchantment.DEPTH_STRIDER, 1000);
            put(Enchantment.FROST_WALKER, 1000);
            put(Enchantment.SOUL_SPEED, 1000);
            put(Enchantment.FEATHER_FALLING, 1000);
            put(Enchantment.THORNS, 1000);
            put(Enchantment.MENDING, 1000);
        }});

        giveItem(player, Material.SHIELD, new LinkedHashMap<Enchantment, Integer>() {{
            put(Enchantment.UNBREAKING, 1000);
            put(Enchantment.MENDING, 1000);
        }});

        player.sendMessage("§aYou received your ultimate armor set!");
        return true;
    }

    private void giveItem(Player player, Material material, Map<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            enchants.forEach((enchant, level) -> meta.addEnchant(enchant, level, true));
            item.setItemMeta(meta);
        }
        player.getInventory().addItem(item);
    }
}