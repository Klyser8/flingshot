package com.github.klyser.flingshot.forge;

import com.github.klyser.flingshot.Flingshot;
import com.github.klyser.flingshot.forge.client.FlingshotForgeClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Flingshot.MOD_ID)
public class FlingshotForge {
    public FlingshotForge() {
        Flingshot.init();
        if (FMLEnvironment.dist.isClient()) {
            FlingshotForgeClient.subscribeClientEvents();
        }
    }
}