package org.teneted.tenet.init;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.teneted.tenet.TenetMC;
import org.teneted.tenet.util.MaterialHelper;

import java.util.ArrayList;
import java.util.Locale;

public class BukkitRegistry {

    public static void registerAll(DedicatedServer console) {
        TenetMC.init();
        loadItems();
        loadBlocks();
    }

    public static void loadItems() {
        var registry = BuiltInRegistries.ITEM;
        var newTypes = new ArrayList<Material>();
        for (Item item : registry) {
            ResourceLocation resourceLocation = registry.getKey(item);
            if (isMods(resourceLocation)) {
                // inject item materials into Bukkit for Fabric
                String materialName = normalizeName(resourceLocation.toString());
                int id = Item.getId(item);
                Material material = MaterialHelper.addMaterial(materialName, id, item.getDefaultMaxStackSize(), false, true, resourceLocation);

                newTypes.add(material);

                CraftMagicNumbers.ITEM_MATERIAL.put(item, material);
                CraftMagicNumbers.MATERIAL_ITEM.put(material, item);
                TenetMC.LOGGER.debug("Save-ITEM: " + material.name() + " - " + material.key);
            }
        }
        TenetMC.LOGGER.info("Registered {} new items", newTypes.size());
    }

    public static void loadBlocks() {
        var registry = BuiltInRegistries.BLOCK;
        var newTypes = new ArrayList<Material>();

        for (Block block : registry) {
            ResourceLocation resourceLocation = registry.getKey(block);
            if (isMods(resourceLocation)) {
                // inject block materials into Bukkit for Fabric
                String materialName = normalizeName(resourceLocation.toString());
                int id = Item.getId(block.asItem());
                Item item = Item.byId(id);
                Material material = MaterialHelper.addMaterial(materialName, id, item.getDefaultMaxStackSize(), true, false, resourceLocation);
                newTypes.add(material);

                if (material != null) {
                    CraftMagicNumbers.BLOCK_MATERIAL.put(block, material);
                    CraftMagicNumbers.MATERIAL_BLOCK.put(material, block);
                    TenetMC.LOGGER.debug("Registered {0} as block {1}" + material.name() + " - " + material.key);
                }
            }
        }
        TenetMC.LOGGER.info("Registered {} new blocks", newTypes.size());
    }

    public static String normalizeName(String name) {
        return name.replace(':', '_')
                .replaceAll("\\s+", "_")
                .replaceAll("\\W", "")
                .toUpperCase(Locale.ENGLISH);
    }

    public static boolean isMods(ResourceLocation resourceLocation) {
        return !resourceLocation.getNamespace().equals(NamespacedKey.MINECRAFT);
    }

    public static boolean isMods(NamespacedKey namespacedkey) {
        return !namespacedkey.getNamespace().equals(NamespacedKey.MINECRAFT);
    }
}
