package com.github.klyser.flingshot.fabric;

import com.github.klyser.flingshot.Flingshot;
import net.fabricmc.api.ModInitializer;

public class FlingshotFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Flingshot.init();
    }
}