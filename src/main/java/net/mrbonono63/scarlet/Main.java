package net.mrbonono63.scarlet;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.mrbonono63.scarlet.blocks.SBlocks;
import net.mrbonono63.scarlet.blocks.entities.SBlockEntity;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.items.SItems;
import net.mrbonono63.scarlet.server.ContraptionChunkGenerator;
import net.mrbonono63.scarlet.server.ContraptionDimensionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qouteall.q_misc_util.LifecycleHack;
import qouteall.q_misc_util.api.DimensionAPI;

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
		LOGGER.info("Block Entities have been intialized");


		DimensionAPI.serverDimensionsLoadEvent.register((generatorOptions, registryManager) -> {
			Registry<DimensionOptions> registry = generatorOptions.getDimensions();

			// get the dimension type
			RegistryEntry<DimensionType> dimType = registryManager.get(Registry.DIMENSION_TYPE_KEY).getEntry(
					RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier(MOD_ID, "contraption"))
			).orElseThrow(() -> new RuntimeException("Missing dimension type"));

			Identifier dimId = new Identifier(MOD_ID,"contraption");

			// get the biome registry for initializing the biome source
			Registry<Biome> biomeRegistry = registryManager.get(Registry.BIOME_KEY);
			Registry<StructureSet> structureRegistry = registryManager.get(Registry.STRUCTURE_SET_KEY);

			// add the dimension
			DimensionAPI.addDimension(
					registry, dimId, dimType,
					new ContraptionChunkGenerator(structureRegistry, biomeRegistry)
			);

			// Mark the dimension non-persistent so it won't be saved into level.dat
			//TODO decide whether to make the dimension persistent (this will decide whether it's contents will be saved or not within the level.dat)
			DimensionAPI.markDimensionNonPersistent(dimId);
			LOGGER.info("Scarlet Contraption Dimension marked as non persistent");
			// Supposed to mark scarlet as stable
			LifecycleHack.markNamespaceStable("scarlet");
			LOGGER.info("Scarlet marked as safe namespace");
		});


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
