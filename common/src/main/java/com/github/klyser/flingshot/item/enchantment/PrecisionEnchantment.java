package com.github.klyser.flingshot.item.enchantment;

import com.github.klyser.flingshot.item.FlingshotItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class PrecisionEnchantment extends Enchantment {

    private final int BASE_POWER = 1;
    private final int POWER_PER_LEVEl = 9;

    public PrecisionEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]
                {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinCost(int level) {
        return BASE_POWER + (level - 1) * POWER_PER_LEVEl;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    /**
     * Enchantment can only be applied to flingshots.
     */
    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof FlingshotItem || stack.getItem() instanceof BookItem;
    }

    public float getDivergenceModifier(int level) {
        return Math.max( 0.8f * level, 1);
    }

}
