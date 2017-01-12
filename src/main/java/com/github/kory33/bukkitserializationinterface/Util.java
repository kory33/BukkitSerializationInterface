package com.github.kory33.bukkitserializationinterface;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class Util {
    static public String serializeItem(ItemStack itemStack) {
        YamlConfiguration virtualConfiguration = new YamlConfiguration();
        virtualConfiguration.set("item", itemStack);
        return virtualConfiguration.saveToString();
    }
    
    static public ItemStack deserializeItem(String serial) throws InvalidConfigurationException {
        YamlConfiguration virtualConfiguration = new YamlConfiguration();
        virtualConfiguration.loadFromString(serial);
        return virtualConfiguration.getItemStack("item");
    }
}
