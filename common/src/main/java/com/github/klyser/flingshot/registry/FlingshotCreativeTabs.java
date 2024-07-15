package com.github.klyser.flingshot.registry;

import com.github.klyser.flingshot.Flingshot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;
import static com.github.klyser.flingshot.platform.CommonPlatformHelper.registerCreativeTab;

public class FlingshotCreativeTabs {

    public static void init() {

    }

    public static final Supplier<CreativeModeTab> ITEMS = registerCreativeTab("items",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(Component.translatable("itemGroup." + Flingshot.MOD_ID + ".items"))
                    .icon(() -> FlingshotItems.FLINGSHOT.get().getDefaultInstance())
                    .displayItems((features, entries) -> {
                        entries.accept(FlingshotItems.FLINGSHOT.get());
                    }).build()
    );
}
