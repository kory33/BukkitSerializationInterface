package com.github.kory33.bukkitserializationinterface;

import com.github.kory33.bukkitserializationinterface.command.SerializationInterfaceCommandExecutor;
import com.github.kory33.updatenotificationplugin.bukkit.github.GithubUpdateNotifyPlugin;

public class BukkitSerializationInterfacePlugin extends GithubUpdateNotifyPlugin{
    @Override
    public void onEnable() {
        super.onEnable();
        this.getCommand("serialinterface").setExecutor(new SerializationInterfaceCommandExecutor());
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
    
    @Override
    public String getGithubRepository() {
        return "Kory33/BukkitSerializationInterface";
    }
}
