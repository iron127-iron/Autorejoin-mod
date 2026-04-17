package com.autorejoin.gui;

import com.autorejoin.config.ServerConfigManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ServerSelectScreen extends Screen {

    public ServerSelectScreen() {
        super(Text.literal("Auto Rejoin Servers"));
    }

    @Override
    protected void init() {

        int y = height / 4;

        for (String server : new ArrayList<>(ServerConfigManager.SERVERS.keySet())) {

            addDrawableChild(ButtonWidget.builder(
                    Text.literal(server),
                    b -> client.setScreen(new ServerConfigScreen(server))
            ).dimensions(width / 2 - 100, y, 200, 20).build());

            y += 25;
        }
    }
}