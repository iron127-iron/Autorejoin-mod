package com.autorejoin.gui;

import com.autorejoin.config.ServerConfig;
import com.autorejoin.config.ServerConfigManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ServerConfigScreen extends Screen {

    private final String server;
    private ServerConfig cfg;

    public ServerConfigScreen(String server) {
        super(Text.literal("Server Config"));
        this.server = server;
    }

    @Override
    protected void init() {

        cfg = ServerConfigManager.get(server);

        int cx = width / 2;

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Delay: " + cfg.delay),
                b -> {
                    cfg.delay++;
                    if (cfg.delay > 30) cfg.delay = 1;
                    b.setMessage(Text.literal("Delay: " + cfg.delay));
                }
        ).dimensions(cx - 100, height / 3, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Max Retry: " + cfg.maxRetries),
                b -> {
                    cfg.maxRetries++;
                    if (cfg.maxRetries > 10) cfg.maxRetries = -1;
                    b.setMessage(Text.literal("Max Retry: " + cfg.maxRetries));
                }
        ).dimensions(cx - 100, height / 3 + 25, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Smart: " + cfg.smartDelay),
                b -> {
                    cfg.smartDelay = !cfg.smartDelay;
                    b.setMessage(Text.literal("Smart: " + cfg.smartDelay));
                }
        ).dimensions(cx - 100, height / 3 + 50, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Random: " + cfg.randomDelay),
                b -> {
                    cfg.randomDelay = !cfg.randomDelay;
                    b.setMessage(Text.literal("Random: " + cfg.randomDelay));
                }
        ).dimensions(cx - 100, height / 3 + 75, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Save"),
                b -> {
                    ServerConfigManager.SERVERS.put(server, cfg);
                    ServerConfigManager.save();
                    close();
                }
        ).dimensions(cx - 100, height / 3 + 110, 200, 20).build());
    }
}