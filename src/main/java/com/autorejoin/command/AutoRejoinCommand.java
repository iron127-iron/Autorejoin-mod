package com.autorejoin.command;

import com.autorejoin.gui.ServerSelectScreen;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;

public class AutoRejoinCommand {

    public static void register() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(
                    ClientCommandManager.literal("autorejoin")
                            .then(ClientCommandManager.literal("gui")
                                    .executes(ctx -> {
                                        MinecraftClient.getInstance().execute(() ->
                                                MinecraftClient.getInstance().setScreen(new ServerSelectScreen())
                                        );
                                        return 1;
                                    })
                            )
            );
        });
    }
}