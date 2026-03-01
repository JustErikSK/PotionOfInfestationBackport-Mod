package net.withrage.infestationbackport.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;

import net.withrage.infestationbackport.ModEffects;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {

	@Inject(method = "damage", at = @At("TAIL"))
	private void infestation$spawnSilverfish(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity self = (LivingEntity) (Object) this;
		if (!cir.getReturnValue()) return;
		if (!(self.getWorld() instanceof ServerWorld world)) return;
		if (amount <= 0.0f) return;
		if (!self.hasStatusEffect(ModEffects.INFESTED)) return;
		if (world.random.nextFloat() >= 0.30f) return;
		int count = 1 + world.random.nextInt(3);
		for (int i = 0; i < count; i++) {
			SilverfishEntity sf = EntityType.SILVERFISH.create(world);
			if (sf == null) continue;
			double ox = (world.random.nextDouble() - 0.5);
			double oz = (world.random.nextDouble() - 0.5);
			sf.refreshPositionAndAngles(self.getX() + ox, self.getY(), self.getZ() + oz, world.random.nextFloat() * 360f, 0f);
			world.spawnEntity(sf);
		}
	}
}