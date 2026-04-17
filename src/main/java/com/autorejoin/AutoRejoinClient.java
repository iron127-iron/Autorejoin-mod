package com.autorejoin;

import com.autorejoin.command.AutoRejoinCommand;
import com.autorejoin.config.ServerConfigManager;
import com.autorejoin.reconnect.ReconnectManager;
import net.fabricmc.api.ClientModInitializer;

public class AutoRejoinClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ServerConfigManager.load();
        ReconnectManager.init();
        AutoRejoinCommand.register();

        System.out.println("[AutoRejoin] Loaded");
    }
}