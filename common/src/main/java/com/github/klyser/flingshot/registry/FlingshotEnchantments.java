package com.github.klyser.flingshot.registry;

import com.github.klyser.flingshot.item.enchantment.AutomationEnchantment;
import com.github.klyser.flingshot.item.enchantment.ForceEnchantment;
import com.github.klyser.flingshot.item.enchantment.PrecisionEnchantment;
import com.github.klyser.flingshot.item.enchantment.VersatilityEnchantment;
import com.github.klyser.flingshot.platform.CommonPlatformHelper;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.Supplier;

public class FlingshotEnchantments {

    public static void init() {}

    public static final Supplier<ForceEnchantment> FORCE = CommonPlatformHelper.registerEnchantment("force", ForceEnchantment::new);
    public static final Supplier<PrecisionEnchantment> PRECISION = CommonPlatformHelper.registerEnchantment("precision", PrecisionEnchantment::new);
    public static final Supplier<AutomationEnchantment> AUTOMATION = CommonPlatformHelper.registerEnchantment("automation", AutomationEnchantment::new);
    public static final Supplier<VersatilityEnchantment> VERSATILITY = CommonPlatformHelper.registerEnchantment("versatility", VersatilityEnchantment::new);

}
