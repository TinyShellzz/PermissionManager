package com.tinyshellzz.permissionManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;

public class ObjectPool {
    public static PermissionManager plugin;

    public final static Gson gson;

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
    }

    public final static Yaml yaml = new Yaml();

    public static HashMap<String, Player> players = new HashMap<>();   // 存储所有player
}
