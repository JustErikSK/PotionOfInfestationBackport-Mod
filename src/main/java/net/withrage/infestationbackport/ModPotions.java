package net.withrage.infestationbackport;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, PotionOfInfestationBackport.MOD_ID);

    public static final DeferredHolder<Potion, Potion> INFESTATION =
            POTIONS.register("infestation", () ->
                    new Potion(new MobEffectInstance(ModEffects.INFESTED.get(), 20 * 180)));

    public static final DeferredHolder<Potion, Potion> LONG_INFESTATION =
            POTIONS.register("long_infestation", () ->
                    new Potion(new MobEffectInstance(ModEffects.INFESTED.get(), 20 * 480)));

    private ModPotions() {}

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);

        eventBus.addListener((net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent event) -> {
            event.enqueueWork(() -> {
                BrewingRecipeRegistry.addRecipe(
                        Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                        Ingredient.of(Items.STONE),
                        PotionUtils.setPotion(new ItemStack(Items.POTION), INFESTATION.get())
                );

                BrewingRecipeRegistry.addRecipe(
                        Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), INFESTATION.get())),
                        Ingredient.of(Items.REDSTONE),
                        PotionUtils.setPotion(new ItemStack(Items.POTION), LONG_INFESTATION.get())
                );
            });
        });
    }
}