package com.github.klyser.flingshot.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class ClientPlatformHelper {

    @ExpectPlatform
    public static void setBlockRenderType(Block block, RenderType renderType) {
        throw new AssertionError();
    }

}
