package net.withrage.infestationbackport;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;

public final class ModCreative {
    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(withPotion(new ItemStack(Items.POTION), ModPotions.INFESTATION));
            entries.add(withPotion(new ItemStack(Items.POTION), ModPotions.LONG_INFESTATION));

            entries.add(withPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.INFESTATION));
            entries.add(withPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.LONG_INFESTATION));

            entries.add(withPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.INFESTATION));
            entries.add(withPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.LONG_INFESTATION));
        });
    }

    private static ItemStack withPotion(ItemStack stack, RegistryEntry<Potion> potionEntry) {
        stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potionEntry));
        return stack;
    }
}