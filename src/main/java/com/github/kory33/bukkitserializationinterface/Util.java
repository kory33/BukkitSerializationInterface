package com.github.kory33.bukkitserializationinterface;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.json.JSONObject;

public class Util {
    static public String serializeItem(ItemStack itemStack) {
        return (new JSONObject(itemStack.serialize())).toString();
        
    }
    
    static public ItemStack deserializeItem(String serial) throws InvalidConfigurationException {
        YamlConfiguration virtualConfiguration = new YamlConfiguration();
        virtualConfiguration.loadFromString(serial);
        return virtualConfiguration.getItemStack("item");
    }
}
