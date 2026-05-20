package net.withrage.infestationbackport;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, PotionOfInfestationBackport.MOD_ID);

    public static final RegistryObject<Potion> INFESTATION =
            POTIONS.register("infestation", () ->
                    new Potion(new MobEffectInstance(
                            ModEffects.INFESTED.getHolder().get(),
                            20 * 180
                    )));

    public static final RegistryObject<Potion> LONG_INFESTATION =
            POTIONS.register("long_infestation", () ->
                    new Potion(new MobEffectInstance(
                            ModEffects.INFESTED.getHolder().get(),
                            20 * 480
                    )));

    private ModPotions() {}

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
        MinecraftForge.EVENT_BUS.addListener(ModPotions::registerBrewingRecipes);
    }

    private static void registerBrewingRecipes(BrewingRecipeRegisterEvent event) {
        event.getBuilder().addMix(
                Potions.AWKWARD,
                Items.STONE,
                INFESTATION.getHolder().get()
        );

        event.getBuilder().addMix(
                INFESTATION.getHolder().get(),
                Items.REDSTONE,
                LONG_INFESTATION.getHolder().get()
        );
    }
}