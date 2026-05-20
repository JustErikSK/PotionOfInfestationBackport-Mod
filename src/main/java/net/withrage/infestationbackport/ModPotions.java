package net.withrage.infestationbackport;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, PotionOfInfestationBackport.MOD_ID);

    public static final DeferredHolder<Potion, Potion> INFESTATION =
            POTIONS.register("infestation", () ->
                    new Potion(new MobEffectInstance(ModEffects.INFESTED, 20 * 180)));

    public static final DeferredHolder<Potion, Potion> LONG_INFESTATION =
            POTIONS.register("long_infestation", () ->
                    new Potion(new MobEffectInstance(ModEffects.INFESTED, 20 * 480)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}