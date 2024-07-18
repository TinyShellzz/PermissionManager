package com.tinyshellzz.permissionManager;

import com.tinyshellzz.permissionManager.command.PermCommand;
import com.tinyshellzz.permissionManager.config.PluginConfig;
import com.tinyshellzz.permissionManager.listener.PlayerChangedWorldListener;
import com.tinyshellzz.permissionManager.listener.PlayerJoinListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermissionManager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "+++++++++++++++++++");

        init();
        register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void init(){
        ObjectPool.plugin = this;
        PluginConfig.reload();
    }

    public void register() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(), this);

        this.getCommand("perm").setExecutor(new PermCommand());
    }
}
