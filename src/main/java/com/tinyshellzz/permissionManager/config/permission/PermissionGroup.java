package com.tinyshellzz.permissionManager.config.permission;

import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.tinyshellzz.permissionManager.ObjectPool.yaml;

public class PermissionGroup {
    public List<String> permission;
    public List<String> no_permission;

    public static PermissionGroup deserialize(MemorySection obj) {
        PermissionGroup ret = new PermissionGroup();
        ret.permission = (List)obj.get("permission");
        ret.no_permission = (List)obj.get("no_permission");
        return ret;
    }
}
