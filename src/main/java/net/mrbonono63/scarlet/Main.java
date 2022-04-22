package net.mrbonono63.scarlet;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.mrbonono63.scarlet.blocks.SBlocks;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.items.SItems;
import net.mrbonono63.scarlet.server.ContraptionChunkGenerator;
import net.mrbonono63.scarlet.server.biome.SBiomeSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import qouteall.q_misc_util.LifecycleHack;
import qouteall.q_misc_util.api.DimensionAPI;

import java.util.Optional;

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

		DimensionAPI.serverDimensionsLoadEvent.register((generatorOptions, registryManager) -> {
			Registry<DimensionOptions> registry = generatorOptions.getDimensions();

			// get the dimension type
			RegistryEntry<DimensionType> dimType = registryManager.get(Registry.DIMENSION_TYPE_KEY).getEntry(
					RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier(MOD_ID, "namespace:dimension_type_id"))
			).orElseThrow(() -> new RuntimeException("Missing dimension type"));

			Identifier dimId = new Identifier(MOD_ID,"dimension_id");

			// get the biome registry for initializing the biome source
			Registry<Biome> biomeRegistry = registryManager.get(Registry.BIOME_KEY);
			BiomeSource biomeSource = new SBiomeSource();

			// add the dimension
			DimensionAPI.addDimension(
					registry, dimId, dimType,
					new ContraptionChunkGenerator( , Optional.empty(), )
			);

			// mark it non-persistent so it won't be saved into level.dat
			DimensionAPI.markDimensionNonPersistent(dimId);
			LifecycleHack.markNamespaceStable("scarlet");
			LOGGER.info("contraption Dimension registered and added");
		});
		//LOGGER.info("Hello Fabric world!");
	}
}
