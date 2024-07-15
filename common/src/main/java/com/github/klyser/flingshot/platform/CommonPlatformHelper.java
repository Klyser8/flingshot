package com.github.klyser.flingshot.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
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

public class CommonPlatformHelper {

    @ExpectPlatform
    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends CreativeModeTab> Supplier<T> registerCreativeTab(String name, Supplier<T> tab) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P extends StructurePlacement> Supplier<StructurePlacementType<P>> registerPlacementType(String name, Supplier<StructurePlacementType<P>> placementType) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <E extends Enchantment> Supplier<E> registerEnchantment(String name, Supplier<E> enchantment) { throw new AssertionError();}

    @ExpectPlatform
    public static <E extends Entity> Supplier<EntityType<E>> registerEntityType(String name, Supplier<EntityType<E>> entityType) {
        throw new AssertionError();
    }

}
