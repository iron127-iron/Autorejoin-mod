package com.autorejoin.reconnect;

import com.autorejoin.config.ServerConfig;
import com.autorejoin.config.ServerConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.network.ServerInfo;

public class ReconnectManager {

    private static long last = 0;
    private static int retry = 0;

    public static void init() {

        new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(500);

                    MinecraftClient client = MinecraftClient.getInstance();

                    if (!(client.currentScreen instanceof DisconnectedScreen)) {
                        retry = 0;
                        continue;
                    }

                    ServerInfo server = client.getCurrentServerEntry();
                    if (server == null) continue;

                    ServerConfig cfg = ServerConfigManager.get(server.address);

                    long delay = cfg.delay * 1000L;

                    if (System.currentTimeMillis() - last > delay) {

                        if (cfg.maxRetries != -1 && retry >= cfg.maxRetries)
                            continue;

                        retry++;
                        last = System.currentTimeMillis();

                        client.execute(() -> {
                            client.setScreen(null);
                            client.connect(server.address, server.port, server);
                        });
                    }

                } catch (Exception ignored) {}
            }

        }, "AutoRejoin").start();
    }
}