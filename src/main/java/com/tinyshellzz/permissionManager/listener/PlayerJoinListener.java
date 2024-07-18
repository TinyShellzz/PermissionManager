package com.tinyshellzz.permissionManager.listener;

import com.tinyshellzz.permissionManager.ObjectPool;
import com.tinyshellzz.permissionManager.core.PermissionService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.security.Permission;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ObjectPool.players.put(player.getDisplayName().toLowerCase(), player);
        PermissionService.updatePermission(player);
    }
}
