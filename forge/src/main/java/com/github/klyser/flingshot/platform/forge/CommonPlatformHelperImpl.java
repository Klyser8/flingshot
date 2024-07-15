package com.github.klyser.flingshot.platform.forge;

import com.github.klyser.flingshot.Flingshot;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Flingshot.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonPlatformHelperImpl {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Flingshot.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Flingshot.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Flingshot.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Flingshot.MOD_ID);
    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, Flingshot.MOD_ID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, Flingshot.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Flingshot.MOD_ID);

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static <T extends CreativeModeTab> Supplier<T> registerCreativeTab(String name, Supplier<T> tab) {
        return CREATIVE_MODE_TABS.register(name, tab);
    }

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        return SOUND_EVENTS.register(name, soundEvent);
    }

    public static <P extends StructurePlacement> Supplier<StructurePlacementType<P>> registerPlacementType(String name, Supplier<StructurePlacementType<P>> placementType) {
        return STRUCTURE_PLACEMENT_TYPES.register(name, placementType);
    }

    public static <E extends Enchantment> Supplier<E> registerEnchantment(String name, Supplier<E> enchantment) {
        return ENCHANTMENTS.register(name, enchantment);
    }

    public static <E extends Entity> Supplier<EntityType<E>> registerEntityType(String name, Supplier<EntityType<E>> entityType) {
        return ENTITY_TYPES.register(name, entityType);
    }

}
