package com.github.klyser.groupid.artifactid;

import net.minecraft.resources.ResourceLocation;

public class Modname
{
	public static final String MOD_ID = "modid";

	public static void init() {

	}

	public static ResourceLocation id(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
}	
