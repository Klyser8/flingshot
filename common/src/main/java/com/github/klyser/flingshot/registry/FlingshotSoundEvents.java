package com.github.klyser.flingshot.registry;

import com.github.klyser.flingshot.Flingshot;
import com.github.klyser.flingshot.platform.CommonPlatformHelper;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class FlingshotSoundEvents {

    public static void init() {}

    public static final Supplier<SoundEvent> FLINGSHOT_SHOOT = CommonPlatformHelper.registerSoundEvent("flingshot_shoot", () ->
            SoundEvent.createVariableRangeEvent(Flingshot.id("flingshot_shoot")));

}
