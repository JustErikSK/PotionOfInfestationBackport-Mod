package net.withrage.infestationbackport;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PotionOfInfestationBackport.MOD_ID);

    public static final RegistryObject<MobEffect> INFESTED =
            MOB_EFFECTS.register("infested", () ->
                    new InfestedStatusEffect(MobEffectCategory.HARMFUL, 0x6B6B6B));

    private ModEffects() {}

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}