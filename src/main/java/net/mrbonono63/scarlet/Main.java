package net.mrbonono63.scarlet;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.Identifier;
import net.mrbonono63.scarlet.blocks.SBlocks;
import net.mrbonono63.scarlet.blocks.entities.SBlockEntity;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.items.SItems;
import net.mrbonono63.scarlet.server.ContraptionDimensionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Main implements ModInitializer {
	public static final String MOD_ID = "scarlet";
	public static final Logger LOGGER = LoggerFactory.getLogger("Scarlet");
	public static Identifier identifier(String name)
	{
		return new Identifier(Main.MOD_ID, name);
	}
	public static ContraptionDimensionHandler contraptionDimensionHandler = new ContraptionDimensionHandler();

	@Override
	public void onInitialize() {
		SBlocks.init();
		LOGGER.info("Blocks have been initialized");
		SItems.init();
		LOGGER.info("Items have been initialized");
		SEntity.init();
		LOGGER.info("Entities have been initialized");
		SBlockEntity.init();
		LOGGER.info("Block Entities have been initialized");

		ServerTickEvents.START_WORLD_TICK.register((serverWorld) -> {
			//either compare the index size or the whole list to a previous ticks list to decide whether to update the dimension or not
			//The contraption entity can then add or remove its contraption data from the dimension handler
			if (!serverWorld.isClient) {
				contraptionDimensionHandler.tick(serverWorld);
			}
		});

		LOGGER.info("Scarlet Initialization complete");
	}
}
