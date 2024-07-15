package com.github.klyser.flingshot.item.enchantment;

import com.github.klyser.flingshot.item.FlingshotItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AutomationEnchantment extends Enchantment {

    public AutomationEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]
                {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinCost(int level) {
        return 20;
    }

    @Override
    public int getMaxCost(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
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

}
