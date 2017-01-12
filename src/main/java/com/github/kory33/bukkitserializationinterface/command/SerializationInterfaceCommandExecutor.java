package com.github.kory33.bukkitserializationinterface.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.kory33.bukkitserializationinterface.Util;

public class SerializationInterfaceCommandExecutor implements CommandExecutor {
    private final JavaPlugin plugin;
    
    public SerializationInterfaceCommandExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    
    private boolean itemStackFromSerial(CommandSender sender, List<String> args) {
        String playerName = args.remove(0);
        Player player = this.plugin.getServer().getPlayer(playerName);
        
        if(player == null) {
            sender.sendMessage(ChatColor.RED + "Player " + playerName + " not found!");
            return true;
        }
        
        ItemStack itemStack = null;
        try {
            itemStack = Util.deserializeItem(args.remove(0));
        } catch (InvalidConfigurationException e) {}
        
        if(itemStack == null) {
            sender.sendMessage(ChatColor.RED + "Itemstack data could not be read!");
            return true;
        }
        
        HashMap<Integer, ItemStack> overflowItemStack = player.getInventory().addItem(itemStack);
        
        if(overflowItemStack.isEmpty()) {
            return true;
        }
        
        player.getWorld().dropItemNaturally(player.getLocation(), overflowItemStack.get(0));
        
        return true;
    }
    
    private boolean serialFromItemStack(CommandSender sender, List<String> listArgs) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("The command is only available for players.");
            return true;
        }
        Player playerSender = (Player) sender;
        ItemStack itemStack = playerSender.getInventory().getItemInMainHand();
        
        if(itemStack == null) {
            playerSender.sendMessage(ChatColor.RED +
                    "Could not get item to be serialized. It should be in your main hand.");
            return true;
        }
        playerSender.sendMessage(Util.serializeItem(itemStack));
        return true;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> listArgs = Arrays.asList(args);
        String commandName = listArgs.remove(0);

        switch(commandName) {
            case "serialfromitem":
                return this.serialFromItemStack(sender, listArgs);
            case "itemfromserial":
                return this.itemStackFromSerial(sender, listArgs);
            default:
                return false;
        }
    }
}
