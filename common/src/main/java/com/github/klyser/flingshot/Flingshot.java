package com.github.klyser.flingshot;

import com.github.klyser.flingshot.registry.FlingshotCreativeTabs;
import com.github.klyser.flingshot.registry.FlingshotItems;
import com.github.klyser.flingshot.registry.FlingshotSoundEvents;
import net.minecraft.resources.ResourceLocation;

public class Flingshot
{
	public static final String MOD_ID = "flingshot";

	public static void init() {
		FlingshotItems.init();
		FlingshotSoundEvents.init();
		FlingshotCreativeTabs.init();
	}

	public static ResourceLocation id(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
}	
