package net.withrage.infestationbackport;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;

public final class ModCreative {
    private ModCreative() {}

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.INFESTATION));
            entries.add(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.LONG_INFESTATION));
            entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.INFESTATION));
            entries.add(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), ModPotions.LONG_INFESTATION));
            entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.INFESTATION));
            entries.add(PotionUtil.setPotion(new ItemStack(Items.LINGERING_POTION), ModPotions.LONG_INFESTATION));
        });
    }
}