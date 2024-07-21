package com.tinyshellzz.permissionManager.config.permission;

import com.tinyshellzz.permissionManager.config.ConfigWrapper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Set;

import static com.tinyshellzz.permissionManager.ObjectPool.plugin;

public class PermissionUsers {
    public static HashMap<String, PermissionUser> users = new HashMap<>();

    public static ConfigWrapper configWrapper = new ConfigWrapper(plugin, "permission/users.yml");;

    public static void reload() {
        configWrapper.reloadConfig();
        users.clear();

        YamlConfiguration config = configWrapper.getConfig();
        Set<String> itemKeySet = config.getKeys(false);
        for (String itemKey : itemKeySet) {
            try {
                MemorySection obj = (MemorySection)config.get(itemKey);
                PermissionUser o = PermissionUser.deserialize(obj);
                users.put(itemKey.toLowerCase(), o);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED.toString() + e.getMessage() + "\n" + e.getStackTrace());
            }
        }
    }

    public static void saveConfig() {
        YamlConfiguration config = new YamlConfiguration();
        for(String key: users.keySet()) {
            config.set(key, users.get(key));
        }
        configWrapper.setConfig(config);
        configWrapper.saveConfig();
    }


}
