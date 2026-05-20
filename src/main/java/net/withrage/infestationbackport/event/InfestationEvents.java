package net.withrage.infestationbackport.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.withrage.infestationbackport.ModEffects;
import net.withrage.infestationbackport.PotionOfInfestationBackport;
import net.withrage.infestationbackport.config.InfestationConfig;

@Mod.EventBusSubscriber(modid = PotionOfInfestationBackport.MOD_ID)
public class InfestationEvents {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.level().isClientSide()) return;
        if (!(entity.level() instanceof ServerLevel level)) return;

        if (!entity.hasEffect(ModEffects.INFESTED.get())) return;

        int chance = InfestationConfig.spawnChance;
        chance = Math.max(0, Math.min(100, chance));

        if (chance == 0) return;
        if (entity.getRandom().nextInt(100) >= chance) return;

        int min = Math.max(1, InfestationConfig.minSpawn);
        int max = Math.max(min, InfestationConfig.maxSpawn);
        int count = min + entity.getRandom().nextInt(max - min + 1);

        DamageSource source = event.getSource();

        for (int i = 0; i < count; i++) {
            Silverfish silverfish = EntityType.SILVERFISH.create(level);
            if (silverfish == null) continue;

            double dx = (entity.getRandom().nextDouble() - 0.5D) * 1.5D;
            double dz = (entity.getRandom().nextDouble() - 0.5D) * 1.5D;

            Vec3 pos = entity.position().add(dx, 0.1D, dz);

            silverfish.moveTo(
                    pos.x,
                    pos.y,
                    pos.z,
                    entity.getRandom().nextFloat() * 360.0F,
                    0.0F
            );

            if (source.getEntity() instanceof LivingEntity attacker) {
                silverfish.setTarget(attacker);
            }

            level.addFreshEntity(silverfish);
        }
    }
}