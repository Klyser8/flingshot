package com.github.klyser.groupid.artifactid.platform.fabric;

import com.github.klyser.groupid.artifactid.Modname;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.function.Supplier;

public class CommonPlatformHelperImpl {

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        T registry = Registry.register(BuiltInRegistries.ITEM, Modname.id( name), item.get());
        return () -> registry;
    }

    public static <T extends CreativeModeTab> Supplier<T> registerCreativeTab(String name, Supplier<T> tab) {
        T registry = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Modname.id( name), tab.get());
        return () -> registry;
    }

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        T registry = Registry.register(BuiltInRegistries.BLOCK, Modname.id( name), block.get());
        return () -> registry;
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        T registry = Registry.register(BuiltInRegistries.SOUND_EVENT, Modname.id( name), soundEvent.get());
        return () -> registry;
    }

    public static <P extends StructurePlacement> Supplier<StructurePlacementType<P>> registerPlacementType(String name, Supplier<StructurePlacementType<P>> placementType) {
        StructurePlacementType<P> registry = Registry.register(BuiltInRegistries.STRUCTURE_PLACEMENT, Modname.id( name), placementType.get());
        return () -> registry;
    }

}
