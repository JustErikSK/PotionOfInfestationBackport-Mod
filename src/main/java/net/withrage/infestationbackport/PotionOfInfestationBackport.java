package net.withrage.infestationbackport;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.withrage.infestationbackport.config.InfestationConfig;
import org.slf4j.Logger;

@Mod(PotionOfInfestationBackport.MOD_ID)
public class PotionOfInfestationBackport {
	public static final String MOD_ID = "infestationbackport";
	public static final Logger LOGGER = LogUtils.getLogger();

	public PotionOfInfestationBackport() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		InfestationConfig.load();

		ModEffects.register(modEventBus);
		ModPotions.register(modEventBus);
	}
}