package com.github.kory33.bukkitserializationinterface;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

public class Util {
    private static final String ITEMSTACK_TOP_KEY = "itemstack";

    static public String serializeItem(ItemStack itemStack, Logger logger) {
        YamlConfiguration virtualConfiguration = new YamlConfiguration();
        virtualConfiguration.set(ITEMSTACK_TOP_KEY, itemStack);
        
        
        Map<?, ?> map = (Map<?, ?>) (new Yaml()).load(virtualConfiguration.saveToString());
        String jsonString = (new JSONObject(map)).toString();
        
        if(logger != null) {
            logger.info("converted data: " + jsonString);
        }
        
        return jsonString;
    }
    
    static public ItemStack deserializeItem(String serial) throws InvalidConfigurationException {
        YamlConfiguration virtualConfiguration = new YamlConfiguration();
        virtualConfiguration.loadFromString(serial);
        return virtualConfiguration.getItemStack(ITEMSTACK_TOP_KEY);
    }
}
