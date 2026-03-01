package net.withrage.infestationbackport;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public final class ModPotions {
    public static RegistryEntry.Reference<Potion> INFESTATION;
    public static RegistryEntry.Reference<Potion> LONG_INFESTATION;

    public static void register() {
        INFESTATION = Registry.registerReference(
                Registries.POTION,
                new Identifier(PotionOfInfestationBackport.MOD_ID, "infestation"),
                new Potion(new StatusEffectInstance(ModEffects.INFESTED, 20 * 180))
        );

        LONG_INFESTATION = Registry.registerReference(
                Registries.POTION,
                new Identifier(PotionOfInfestationBackport.MOD_ID, "long_infestation"),
                new Potion(new StatusEffectInstance(ModEffects.INFESTED, 20 * 480))
        );
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Items.STONE, ModPotions.INFESTATION);
            builder.registerPotionRecipe(ModPotions.INFESTATION, Items.REDSTONE, ModPotions.LONG_INFESTATION);
        });
    }
}
