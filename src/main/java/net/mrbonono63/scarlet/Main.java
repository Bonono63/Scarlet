package net.mrbonono63.scarlet;

import net.fabricmc.api.ModInitializer;
import net.mrbonono63.scarlet.registry.SBlocks;
import net.mrbonono63.scarlet.registry.SItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MOD_ID = "scarlet";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		SBlocks.init();
		SItems.init();
		LOGGER.info("Hello Fabric world!");
	}
}
