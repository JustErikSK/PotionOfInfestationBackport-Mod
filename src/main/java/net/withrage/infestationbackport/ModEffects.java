package net.withrage.infestationbackport;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, PotionOfInfestationBackport.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> INFESTED =
            MOB_EFFECTS.register("infested", () ->
                    new InfestedStatusEffect(MobEffectCategory.HARMFUL, 0x6B6B6B));

    private ModEffects() {}

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}