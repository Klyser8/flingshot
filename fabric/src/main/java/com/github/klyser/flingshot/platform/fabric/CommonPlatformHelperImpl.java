package com.github.klyser.flingshot.platform.fabric;

import com.github.klyser.flingshot.Flingshot;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.function.Supplier;

public class CommonPlatformHelperImpl {

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        T registry = Registry.register(BuiltInRegistries.ITEM, Flingshot.id(name), item.get());
        return () -> registry;
    }

    public static <T extends CreativeModeTab> Supplier<T> registerCreativeTab(String name, Supplier<T> tab) {
        T registry = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Flingshot.id(name), tab.get());
        return () -> registry;
    }

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        T registry = Registry.register(BuiltInRegistries.BLOCK, Flingshot.id(name), block.get());
        return () -> registry;
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        T registry = Registry.register(BuiltInRegistries.SOUND_EVENT, Flingshot.id(name), soundEvent.get());
        return () -> registry;
    }

    public static <P extends StructurePlacement> Supplier<StructurePlacementType<P>> registerPlacementType(String name, Supplier<StructurePlacementType<P>> placementType) {
        StructurePlacementType<P> registry = Registry.register(BuiltInRegistries.STRUCTURE_PLACEMENT, Flingshot.id(name), placementType.get());
        return () -> registry;
    }

    public static <E extends Enchantment> Supplier<E> registerEnchantment(String name, Supplier<E> enchantment) {
        E registry = Registry.register(BuiltInRegistries.ENCHANTMENT, Flingshot.id(name), enchantment.get());
        return () -> registry;
    }

    public static <E extends Entity> Supplier<EntityType<E>> registerEntityType(String name, Supplier<EntityType<E>> entityType) {
        EntityType<E> registry = Registry.register(BuiltInRegistries.ENTITY_TYPE, Flingshot.id(name), entityType.get());
        return () -> registry;
    }

}
