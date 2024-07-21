package com.tinyshellzz.permissionManager.listener;

import com.tinyshellzz.permissionManager.ObjectPool;
import com.tinyshellzz.permissionManager.core.PermissionService;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import static com.tinyshellzz.permissionManager.ObjectPool.plugin;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ObjectPool.players.put(player.getDisplayName().toLowerCase(), player);
        PermissionAttachment attachment = player.addAttachment(plugin);
        ObjectPool.attachments.put(player.getUniqueId(), attachment);
        PermissionService.updatePermission(player);
    }
}
