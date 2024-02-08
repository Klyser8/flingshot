package com.github.klyser.groupid.artifactid.forge;

import com.github.klyser.groupid.artifactid.Modname;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Modname.MOD_ID)
public class ModnameForge {
    public ModnameForge() {
        Modname.init();
    }
}