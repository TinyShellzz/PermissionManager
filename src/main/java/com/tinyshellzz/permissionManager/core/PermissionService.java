package com.tinyshellzz.permissionManager.core;

import com.tinyshellzz.permissionManager.ObjectPool;
import com.tinyshellzz.permissionManager.config.permission.PermissionGroup;
import com.tinyshellzz.permissionManager.config.permission.PermissionGroups;
import com.tinyshellzz.permissionManager.config.permission.PermissionUser;
import com.tinyshellzz.permissionManager.config.permission.PermissionUsers;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Arrays;
import java.util.HashMap;

import static com.tinyshellzz.permissionManager.ObjectPool.plugin;

public class PermissionService {
    public static HashMap<String, HashMap<String, Boolean>> permissions = new HashMap<>();  // 存储所有玩家的权限

    public static void reloadConfig() {
        PermissionUsers.reload();
        PermissionGroups.reload();
        calculatePermission();
    }

    public static void recalculate() {
        calculatePermission();
    }

    /**
     * 在登录，以及玩家换世界的时候调用
     * @param player
     */
    public static void updatePermission(Player player) {
        PermissionAttachment attachment = player.addAttachment(plugin);
        HashMap<String, Boolean> perms = PermissionService.permissions.get(player.getDisplayName().toString().toLowerCase());
        if(perms != null) {
            for (String perm : perms.keySet()) {
                attachment.setPermission(perm, perms.get(perm));
            }
        }

//        player.recalculatePermissions();
        player.updateCommands();    // 让 tab complete 生效
    }

    private static void calculatePermission() {
        HashMap<String, Boolean> sub_permission = new HashMap<>();
        for(String user: PermissionUsers.users.keySet()) {
//            UUID uuid = BukkitTools.getUUID(user);
            PermissionUser permissionUser = PermissionUsers.users.get(user);

            PermissionGroup defaultGroup = PermissionGroups.groups.get("Default");
            if(defaultGroup.permission != null) {
                for (String per : defaultGroup.permission) {
                    sub_permission.put(per, true);
                }
            }
            if(defaultGroup.no_permission != null) {
                for (String per : defaultGroup.no_permission) {
                    sub_permission.put(per, false);
                }
            }


            if(permissionUser.group != null) {
                for(String group: permissionUser.group) {
                    PermissionGroup permissionGroup = PermissionGroups.groups.get(group);
                    if(permissionGroup.permission != null) {
                        for (String per : permissionGroup.permission) {
                            sub_permission.put(per, true);
                        }
                    }
                    if(permissionGroup.no_permission != null) {
                        for (String per : permissionGroup.no_permission) {
                            sub_permission.put(per, false);
                        }
                    }
                }
            }

            if(permissionUser.permission != null) {
                for (String per : permissionUser.permission) {
                    sub_permission.put(per, true);
                }
            }
            if(permissionUser.no_permission != null) {
                for (String per : permissionUser.no_permission) {
                    sub_permission.put(per, false);
                }
            }



            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + user.toLowerCase());
            for(String key: sub_permission.keySet()) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + key + ":" + sub_permission.get(key));
            }
            permissions.put(user.toLowerCase(), sub_permission);
        }
    }

    public static boolean give(String playerName, String permission) {
        playerName = playerName.toLowerCase();
        PermissionUser permissionUser = PermissionUsers.users.get(playerName);
        if(permissionUser != null) permissionUser.addPermission(permission);

        PermissionService.calculatePermission();
        PermissionUsers.saveConfig();

        Player player = ObjectPool.players.get(playerName);
        if(player != null) {
            PermissionService.updatePermission(player);
        }
        return true;
    }

    public static boolean remove(String playerName, String permission) {
        playerName = playerName.toLowerCase();
        PermissionUser permissionUser = PermissionUsers.users.get(playerName);
        if(permissionUser != null) permissionUser.removePermission(permission);

        PermissionService.calculatePermission();
        PermissionUsers.saveConfig();

        Player player = ObjectPool.players.get(playerName);
        if(player != null) {
            PermissionService.updatePermission(player);
        }
        return true;
    }
}
