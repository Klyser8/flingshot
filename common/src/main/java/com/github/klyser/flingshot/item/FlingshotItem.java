package com.github.klyser.flingshot.item;

import com.github.klyser.flingshot.registry.FlingshotEnchantments;
import com.github.klyser.flingshot.registry.FlingshotSoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class FlingshotItem extends Item implements Vanishable {

    public static final Predicate<ItemStack> FLINGSHOT_PROJECTILES = stack ->
            stack.getItem() instanceof Flingable;

    public static final int CHARGE_TIME = 15;
    private final Tier tier;

    public FlingshotItem(Tier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    public Tier getTier() {
        return this.tier;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return hasAutomation(stack) ? CHARGE_TIME : 72000;
    }

    @Override
    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return this.tier.getRepairIngredient().test(ingredient) || super.isValidRepairItem(stack, ingredient);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
        ItemStack flingShot = user.getItemInHand(hand);
        boolean isHoldingProjectile = !getHeldProjectile(flingShot, user).isEmpty();
        if (isHoldingProjectile) {//TODO change speed of player when using flingshot
            user.startUsingItem(hand);
            return InteractionResultHolder.consume(flingShot);
        } else {
            return InteractionResultHolder.fail(flingShot);
        }
    }

    @Override
    public void releaseUsing(ItemStack flingshot, Level level, LivingEntity user, int remainingUseTicks) {
        if (hasAutomation(flingshot)) {
            return;
        }
        handleFlingshotLogic(flingshot, level, user,remainingUseTicks);
    }



    @Override
    public ItemStack finishUsingItem(ItemStack flingshot, Level level, LivingEntity user) {
        handleFlingshotLogic(flingshot, level, user, 0);
        return super.finishUsingItem(flingshot, level, user);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    private void handleFlingshotLogic(ItemStack flingshot, Level level, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player player)) {
            return;
        }
        boolean isCreative = player.getAbilities().instabuild;
        ItemStack projStack = getHeldProjectile(flingshot, player);
        if (projStack.isEmpty()) {
            return;
        }
        int ticksPassed = this.getUseDuration(flingshot) - remainingUseTicks;
        if ((hasAutomation(flingshot) && ticksPassed % CHARGE_TIME != 0) || ticksPassed < CHARGE_TIME) {
            return;
        }
        if (!level.isClientSide) {
            float force = calculateForce(flingshot, projStack);
            float divergence = calculateDivergence(flingshot);
            if (projStack.getItem() instanceof Flingable flingable) {
                Projectile projEntity = flingable.createFlingableEntity(level, projStack, user);
                if (projEntity == null) {
                    return;
                }
                level.addFreshEntity(projEntity);
                projEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 1.0f, force, divergence);
                projEntity.hurtMarked = true;
                if (!isCreative) {
                    projStack.shrink(1);
                }
            } else {
                Vec3 velocity = calculateDeltaMovementFromShooter(player, player.getXRot(),
                        player.getYRot(), 1.0f, force, divergence, level);
                ItemEntity itemEntity = new ItemEntity(level,
                        player.getEyePosition().x, player.getEyeY(), player.getEyePosition().z, projStack.copy(),
                        velocity.x, velocity.y, velocity.z);
                itemEntity.setPickUpDelay(20);
                level.addFreshEntity(itemEntity);
            }
//            EarthboundsAdvancementCriteria.SHOT_FLINGSHOT.trigger((ServerPlayerEntity) player, projStack); //TODO add advancements
            flingshot.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            //If the item shot was not flingable originally, it means it in its entirety was thrown (versatility enchant)
            //Remove!
            if (!(projStack.getItem() instanceof Flingable)) {
                projStack.setCount(0);
            }
        }
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                FlingshotSoundEvents.FLINGSHOT_SHOOT.get(), SoundSource.PLAYERS,
                1.0f, 1 + level.random.nextFloat() / 4.0f);
        player.awardStat(Stats.ITEM_USED.get(this));
    }

    private float calculateForce(ItemStack flingshot, ItemStack projectile) {
        float force = 1.0f;
//        if (projectile.is(FlingshotItems.GLOW_GREASE)) { //TODO: Either remove or add replacement to glow grease.
//            force -= 0.2f;
//        }
        if (EnchantmentHelper.getEnchantments(flingshot).get(FlingshotEnchantments.FORCE.get()) != null) {
            force += FlingshotEnchantments.FORCE.get().getExtraForce(
                    EnchantmentHelper.getEnchantments(flingshot).get(FlingshotEnchantments.FORCE.get()));
        }
        return force;
    }

    private float calculateDivergence(ItemStack flingshot) {
        float divergence = 5.0f;
        if (EnchantmentHelper.getEnchantments(flingshot).get(FlingshotEnchantments.PRECISION.get()) != null) {
            divergence -= FlingshotEnchantments.PRECISION.get().getDivergenceModifier(
                    EnchantmentHelper.getEnchantments(flingshot).get(FlingshotEnchantments.PRECISION.get()));
        }
        return divergence;
    }

    private boolean hasAutomation(ItemStack flingshot) {
        return EnchantmentHelper.getEnchantments(flingshot).get(FlingshotEnchantments.AUTOMATION.get()) != null;
    }

    private static boolean hasVersatility(ItemStack flingshot) {
        return EnchantmentHelper.getEnchantments(flingshot).get(FlingshotEnchantments.VERSATILITY.get()) != null;
    }

    /**
     * Returns the projectile held in the hand which is not holding the flingshot.
     * In case the flingshot has the {@link com.github.klyser.flingshot.item.enchantment.VersatilityEnchantment}, any item can be flung.
     *
     * @param flingshot the flingshot item used
     * @param entity the entity to be checked
     * @return the itemstack if it is a valid projectile. An empty stack otherwise.
     */
    public static ItemStack getHeldProjectile(ItemStack flingshot, LivingEntity entity) {
        if (hasVersatility(flingshot)) {
            return hasVersatility(entity.getMainHandItem()) ? entity.getOffhandItem() : entity.getMainHandItem();
        }
        ItemStack offHandItem = entity.getItemInHand(InteractionHand.OFF_HAND);
        if (FLINGSHOT_PROJECTILES.test(offHandItem)) {
            return offHandItem;
        }
        ItemStack mainHandItem = entity.getItemInHand(InteractionHand.MAIN_HAND);
        if (FLINGSHOT_PROJECTILES.test(mainHandItem)) {
            return mainHandItem;
        }
        return ItemStack.EMPTY;
    }

    public static Vec3 calculateDeltaMovementFromShooter(LivingEntity shooter, float pitch, float yaw,
                                                         float roll, float speed, float divergence, Level level) {
        float f = -Mth.sin(yaw * ((float)Math.PI / 180)) * Mth.cos(pitch * ((float)Math.PI / 180));
        float g = -Mth.sin((pitch + roll) * ((float)Math.PI / 180));
        float h = Mth.cos(yaw * ((float)Math.PI / 180)) * Mth.cos(pitch * ((float)Math.PI / 180));
        Vec3 deltaMovement = calculateDeltaMovementWithoutShooter(f, g, h, speed, divergence, level);
        Vec3 vec3d = shooter.getDeltaMovement();
        return deltaMovement.add(vec3d.x, shooter.onGround() ? 0.0 : vec3d.y, vec3d.z);
    }

    private static Vec3 calculateDeltaMovementWithoutShooter(double x, double y, double z, float speed, float divergence, Level level) {
        RandomSource random = level.random;
        return new Vec3(x, y, z).normalize().add(random.nextGaussian() *
                (double)0.0075f * (double)divergence, random.nextGaussian() * (double)0.0075f *
                (double)divergence, random.nextGaussian() * (double)0.0075f * (double)divergence).scale(speed);
    }
}