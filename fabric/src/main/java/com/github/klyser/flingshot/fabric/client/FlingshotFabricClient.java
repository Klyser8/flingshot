package com.github.klyser.flingshot.fabric.client;

import com.github.klyser.flingshot.client.FlingshotClient;
import net.fabricmc.api.ClientModInitializer;

public class FlingshotFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FlingshotClient.init();
    }
}
