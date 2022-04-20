package net.mrbonono63.scarlet;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.mrbonono63.scarlet.blocks.SBlocks;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.items.SItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MOD_ID = "scarlet";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Identifier identifier(String name)
	{
		return new Identifier(Main.MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		SBlocks.init();
		SItems.init();
		SEntity.init();
		//LOGGER.info("Hello Fabric world!");
	}
}
