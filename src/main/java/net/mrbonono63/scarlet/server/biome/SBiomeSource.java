package net.mrbonono63.scarlet.server.biome;

import com.mojang.serialization.Codec;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

import java.util.List;

public class SBiomeSource extends BiomeSource {

    public SBiomeSource(List<RegistryEntry<Biome>> biomes) {
        super(biomes);
    }

    @Override
    protected Codec<? extends BiomeSource> getCodec() {
        return null;
    }

    @Override
    public BiomeSource withSeed(long seed) {
        return null;
    }

    @Override
    public RegistryEntry<Biome> getBiome(int x, int y, int z, MultiNoiseUtil.MultiNoiseSampler noise) {
        return null; //TODO add void biome to be passed as the only biome in the chunk generator
    }
}
