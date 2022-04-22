package net.mrbonono63.scarlet.server;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ContraptionChunkGenerator extends ChunkGenerator {


    public ContraptionChunkGenerator(Registry<StructureSet> registry, Optional<RegistryEntryList<StructureSet>> optional, BiomeSource biomeSource) {
        super(registry, optional, biomeSource);
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return null;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return null;
    }

    @Override
    public MultiNoiseUtil.MultiNoiseSampler getMultiNoiseSampler() {
        return null;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver generationStep) {

    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, Chunk chunk) {

    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return 128;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, StructureAccessor structureAccessor, Chunk chunk) {
        return null;
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinimumY() {
        return 0;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world) {
        return 128;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world) {
        return null;
    }

    @Override
    public void getDebugHudText(List<String> text, BlockPos pos) {

    }
}
