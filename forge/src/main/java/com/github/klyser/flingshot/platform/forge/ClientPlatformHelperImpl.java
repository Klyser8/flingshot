package com.github.klyser.flingshot.platform.forge;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class ClientPlatformHelperImpl {

    public static void setBlockRenderType(Block block, RenderType renderType) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
    }
}
