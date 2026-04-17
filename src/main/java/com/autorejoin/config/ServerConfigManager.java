package com.autorejoin.config;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ServerConfigManager {

    private static final File FILE = new File("config/autorejoin_servers.json");
    private static final Gson GSON = new Gson();

    public static Map<String, ServerConfig> SERVERS = new HashMap<>();

    public static void load() {
        try {
            if (!FILE.exists()) return;
            SERVERS = GSON.fromJson(new FileReader(FILE), HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            FILE.getParentFile().mkdirs();
            FileWriter w = new FileWriter(FILE);
            GSON.toJson(SERVERS, w);
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ServerConfig get(String server) {
        return SERVERS.computeIfAbsent(server, k -> new ServerConfig());
    }
}