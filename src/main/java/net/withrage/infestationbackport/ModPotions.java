package net.withrage.infestationbackport;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModPotions {
    public static Potion INFESTATION;
    public static Potion LONG_INFESTATION;

    private ModPotions() {}

    public static void register() {
        // 3:00
        INFESTATION = Registry.register(
                Registries.POTION,
                new Identifier(PotionOfInfestationBackport.MOD_ID, "infestation"),
                new Potion(new StatusEffectInstance(ModEffects.INFESTED, 20 * 180))
        );
        // 8:00
        LONG_INFESTATION = Registry.register(
                Registries.POTION,
                new Identifier(PotionOfInfestationBackport.MOD_ID, "long_infestation"),
                new Potion(new StatusEffectInstance(ModEffects.INFESTED, 20 * 480))
        );
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.STONE, INFESTATION);
        BrewingRecipeRegistry.registerPotionRecipe(INFESTATION, Items.REDSTONE, LONG_INFESTATION);
    }
}
