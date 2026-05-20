package net.withrage.infestationbackport.event;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.withrage.infestationbackport.ModPotions;

public class BrewingEvents {

    public static void register() {
        NeoForge.EVENT_BUS.register(new BrewingEvents());
    }

    @SubscribeEvent
    public void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(
                Potions.AWKWARD,
                Items.STONE,
                ModPotions.INFESTATION
        );

        event.getBuilder().addMix(
                ModPotions.INFESTATION,
                Items.REDSTONE,
                ModPotions.LONG_INFESTATION
        );
    }
}