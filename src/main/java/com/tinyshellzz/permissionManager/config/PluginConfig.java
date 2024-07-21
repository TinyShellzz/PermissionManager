package com.tinyshellzz.permissionManager.config;


import com.tinyshellzz.permissionManager.core.PermissionService;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import static com.tinyshellzz.permissionManager.ObjectPool.plugin;

public class PluginConfig {
    public static String msg;

    private static ConfigWrapper configWrapper = new ConfigWrapper(plugin, "config.yml");
    public static void reload() {
        configWrapper.reloadConfig();

        YamlConfiguration config = configWrapper.getConfig();
        msg =  config.getString("msg");


        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "--------------");
        PermissionService.reloadConfig();
    }
}
