package com.tinyshellzz.permissionManager.config.permission;

import org.bukkit.configuration.MemorySection;

import java.util.Arrays;
import java.util.List;

public class PermissionUser {
    public List<String> group;
    public List<String> permission;
    public List<String> no_permission;
    public static PermissionUser deserialize(MemorySection obj) {
        PermissionUser ret = new PermissionUser();
        ret.group = (List)obj.get("group");
        ret.permission = (List)obj.get("permission");
        ret.no_permission = (List)obj.get("no_permission");
        return ret;
    }

    public void removePermission(String perm) {
        if(no_permission != null) no_permission.add(perm);
        else {
            no_permission = Arrays.asList(perm);
        }
        if(permission != null) {
            for(int i = 0; i < permission.size(); i++) {
                if(perm.equals(permission.get(i))) permission.remove(i);
            }
        }
    }

    public void addPermission(String perm) {
        if(no_permission != null) {
            for(int i = 0; i < no_permission.size(); i++) {
                if(perm.equals(no_permission.get(i))) no_permission.remove(i);
            }
        }
        if(permission != null) permission.add(perm);
        else {
            permission = Arrays.asList(perm);
        }
    }
}
