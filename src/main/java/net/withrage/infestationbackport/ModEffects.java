package net.withrage.infestationbackport;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public final class ModEffects {
    public static RegistryEntry.Reference<StatusEffect> INFESTED;

    public static void register() {
        INFESTED = Registry.registerReference(
                Registries.STATUS_EFFECT,
                new Identifier(PotionOfInfestationBackport.MOD_ID, "infested"),
                new InfestedStatusEffect(StatusEffectCategory.HARMFUL, 0x6B6B6B)
        );
    }
}
