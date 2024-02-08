package com.github.klyser.groupid.artifactid.fabric;

import com.github.klyser.groupid.artifactid.Modname;
import net.fabricmc.api.ModInitializer;

public class ModnameFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Modname.init();
    }
}