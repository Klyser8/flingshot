package com.github.klyser.flingshot.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Represents an item which can be flung using {@link FlingshotItem}
 */
public interface Flingable {

    Projectile createFlingableEntity(Level level, ItemStack stack, LivingEntity shooter);

}
