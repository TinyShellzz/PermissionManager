package com.tinyshellzz.permissionManager.listener;

import com.tinyshellzz.permissionManager.ObjectPool;
import com.tinyshellzz.permissionManager.core.PermissionService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.permissions.PermissionAttachment;

import static com.tinyshellzz.permissionManager.ObjectPool.plugin;

public class PlayerChangedWorldListener  implements Listener {
    @EventHandler
    public void handle(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        PermissionAttachment attachment = player.addAttachment(plugin);
        ObjectPool.attachments.put(player.getUniqueId(), attachment);
        PermissionService.updatePermission(player);
    }
}
