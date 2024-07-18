package com.tinyshellzz.permissionManager.config.permission;

import com.tinyshellzz.permissionManager.config.ConfigWrapper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static com.tinyshellzz.permissionManager.ObjectPool.plugin;
import static com.tinyshellzz.permissionManager.ObjectPool.yaml;

public class PermissionGroups {
    public static HashMap<String, PermissionGroup> groups = new HashMap<>();

    public static ConfigWrapper configWrapper = new ConfigWrapper(plugin, "permission/groups.yml");

    public static void reload() {
        configWrapper.reloadConfig(); // 重新加载配置文件

        YamlConfiguration config = configWrapper.getConfig();
        Set<String> itemKeySet = config.getKeys(false);
        for (String itemKey : itemKeySet) {
            try {
                MemorySection obj = (MemorySection)config.get(itemKey);
                PermissionGroup o = PermissionGroup.deserialize(obj);
                groups.put(itemKey, o);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED.toString() + e.getMessage() + "\n" + e.getStackTrace());
            }
        }
    }

    public static void saveConfig() {
        YamlConfiguration config = new YamlConfiguration();
        for(String key: groups.keySet()) {
            config.set(key, groups.get(key));
        }
        configWrapper.setConfig(config);
        configWrapper.saveConfig();
    }
}
