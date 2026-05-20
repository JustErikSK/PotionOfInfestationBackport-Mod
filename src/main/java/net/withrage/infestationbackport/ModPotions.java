package net.withrage.infestationbackport;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, PotionOfInfestationBackport.MOD_ID);

    public static final RegistryObject<Potion> INFESTATION =
            POTIONS.register("infestation", () ->
                    new Potion(new MobEffectInstance(ModEffects.INFESTED.get(), 20 * 180)));

    public static final RegistryObject<Potion> LONG_INFESTATION =
            POTIONS.register("long_infestation", () ->
                    new Potion(new MobEffectInstance(ModEffects.INFESTED.get(), 20 * 480)));

    private ModPotions() {}

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);

        eventBus.addListener((net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent event) -> {
            event.enqueueWork(() -> {
                BrewingRecipeRegistry.addRecipe(
                        net.minecraft.world.item.crafting.Ingredient.of(
                                PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)
                        ),
                        net.minecraft.world.item.crafting.Ingredient.of(Items.STONE),
                        PotionUtils.setPotion(new ItemStack(Items.POTION), INFESTATION.get())
                );

                BrewingRecipeRegistry.addRecipe(
                        net.minecraft.world.item.crafting.Ingredient.of(
                                PotionUtils.setPotion(new ItemStack(Items.POTION), INFESTATION.get())
                        ),
                        net.minecraft.world.item.crafting.Ingredient.of(Items.REDSTONE),
                        PotionUtils.setPotion(new ItemStack(Items.POTION), LONG_INFESTATION.get())
                );
            });
        });
    }
}