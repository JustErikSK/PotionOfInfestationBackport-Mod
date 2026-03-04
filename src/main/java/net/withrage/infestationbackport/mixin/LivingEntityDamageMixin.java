package net.withrage.infestationbackport.mixin;

import net.withrage.infestationbackport.config.InfestationConfig;
import net.withrage.infestationbackport.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {

	@Inject(method = "damage", at = @At("RETURN"))
	private void infestation_backport$onDamageReturn(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (!cir.getReturnValue()) return;

		LivingEntity self = (LivingEntity) (Object) this;

		if (self.getWorld().isClient) return;
		if (!(self.getWorld() instanceof ServerWorld world)) return;

		if (!self.hasStatusEffect(ModEffects.INFESTED)) return;

		int chance = InfestationConfig.spawnChance;
		chance = Math.max(0, Math.min(100, chance));
		if (chance == 0) return;
		if (self.getRandom().nextInt(100) >= chance) return;

		int min = Math.max(1, InfestationConfig.minSpawn);
		int max = Math.max(min, InfestationConfig.maxSpawn);
		int count = min + self.getRandom().nextInt(max - min + 1);

		for (int i = 0; i < count; i++) {
			SilverfishEntity silverfish = EntityType.SILVERFISH.create(world);
			if (silverfish == null) continue;

			double dx = (self.getRandom().nextDouble() - 0.5) * 1.5;
			double dz = (self.getRandom().nextDouble() - 0.5) * 1.5;

			Vec3d pos = self.getPos().add(dx, 0.1, dz);
			silverfish.refreshPositionAndAngles(pos.x, pos.y, pos.z, self.getRandom().nextFloat() * 360f, 0f);

			if (source.getAttacker() instanceof LivingEntity attacker) {
				silverfish.setTarget(attacker);
			}

			world.spawnEntity(silverfish);
		}
	}
}