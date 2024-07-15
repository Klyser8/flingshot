package com.github.klyser.flingshot.registry;

import com.github.klyser.flingshot.item.FlingshotItem;
import com.github.klyser.flingshot.platform.CommonPlatformHelper;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class FlingshotItems {

    public static void init() {}

    public static Supplier<Item> FLINGSHOT = CommonPlatformHelper.registerItem(
            "flingshot", () -> new FlingshotItem(new Item.Properties()
                    .defaultDurability(320)
                    .stacksTo(1)));

}
