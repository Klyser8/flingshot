package com.github.klyser.flingshot.forge.client;

import com.github.klyser.flingshot.client.FlingshotClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class FlingshotForgeClient {

    public static void subscribeClientEvents() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(FlingshotForgeClient::clientSetup);
    }

    private static void clientSetup(FMLClientSetupEvent event) {
        FlingshotClient.init();
    }

}
