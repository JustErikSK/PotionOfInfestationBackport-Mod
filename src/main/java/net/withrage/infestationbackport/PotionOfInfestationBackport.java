package net.withrage.infestationbackport;

import net.fabricmc.api.ModInitializer;
import net.withrage.infestationbackport.config.InfestationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PotionOfInfestationBackport implements ModInitializer {
	public static final String MOD_ID = "infestationbackport";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		InfestationConfig.load();
		ModEffects.register();
		ModPotions.register();
		ModCreative.init();
	}
}