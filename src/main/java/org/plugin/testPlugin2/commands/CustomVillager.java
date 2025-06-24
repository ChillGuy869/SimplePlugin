package org.plugin.testPlugin2.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.plugin.testPlugin2.events.PlayerSpawn;
import org.plugin.testPlugin2.managers.Color;

import java.util.Collections;
import java.util.List;

public class CustomVillager implements CommandExecutor {

    private final Plugin plugin;


    public CustomVillager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Location location = p.getLocation(); // or wherever you want to spawn
            World world = location.getWorld();


            String rank = PlayerSpawn.ranks.get(p.getName());
            if (!"Admin".equals(rank)) {
                p.sendMessage("This command can be used only by people with Admin rank");
                return true;
            }


            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);

                villager.setCustomName("§bMagic Trader");
                villager.setCustomNameVisible(true);
                villager.setProfession(Villager.Profession.LIBRARIAN);
                villager.setVillagerLevel(5);
                villager.setVillagerType(Villager.Type.PLAINS);
                villager.setAI(false);

                // Trade 1: Emerald -> Diamond
                ItemStack result_1 = new ItemStack(Material.DIAMOND, 1);
                MerchantRecipe recipe_1 = new MerchantRecipe(result_1, 9999);
                recipe_1.addIngredient(new ItemStack(Material.DIRT, 1));


                // Trade 2: Emerald -> Custom Sword
                ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
                ItemMeta meta = sword.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName("§bCool Sword");
                    meta.addEnchant(Enchantment.SHARPNESS, 10000, true);
                    meta.addEnchant(Enchantment.LOOTING, 10000, true);
                    meta.setUnbreakable(true);
                    sword.setItemMeta(meta);
                }

                MerchantRecipe recipe_2 = new MerchantRecipe(sword, 9999);
                recipe_2.addIngredient(new ItemStack(Material.DIRT, 1));

                ItemStack result_3 = new ItemStack(Material.IRON_BOOTS, 1);
                MerchantRecipe recipe_3 = new MerchantRecipe(result_3, 9999);
                recipe_3.addIngredient(new ItemStack(Material.IRON_INGOT, 5));

                ItemStack result_4 = new ItemStack(Material.BEDROCK, 1);
                MerchantRecipe recipe_4 = new MerchantRecipe(result_4, 9999);
                recipe_4.addIngredient(new ItemStack(Material.FIRE, 1));

                // Set both trades
                villager.setRecipes(List.of(recipe_1, recipe_2, recipe_3, recipe_4));
            }, 1L);


            p.sendMessage(Color.instance.convert("&c&nCustom &nvillager&r&b&ohas been&r &a&kspawned"));
        }
        return true;
    }
}
