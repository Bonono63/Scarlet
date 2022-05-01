package net.mrbonono63.scarlet.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkManager;
import net.mrbonono63.scarlet.Main;

import java.util.ArrayList;
import java.util.List;

public class ContraptionDimensionHandler {

    //List of the contraptions the server is keeping track of
    public List<BlockBox> originList = new ArrayList<>();
    public List<BlockBox> denstinationList = new ArrayList<>();

    int previousListSize = 0;
    int ListSize = 0;

    //maximum entity length
    public static final int MAXIMUM_BLOCK_LENGTH = 128;
    //length in chunks of the maximum block length
    public static final int CHUNK_SIZE = MAXIMUM_BLOCK_LENGTH / 16;

    //constructor
    public ContraptionDimensionHandler() {

    }

    //List management
    public void addContraption(BlockBox box)
    {
        originList.add(box);
    }

    public  void removeContraption(int i)
    {
        originList.remove(i);
    }

    //Run every tick to add and remove the contraption block boxes passed through and manage that
    public void tick(ServerWorld serverWorld)
    {
        this.previousListSize = this.ListSize;
        this.ListSize = originList.size();

        if (!serverWorld.isClient()) {
            /*for (int i = 0; i < originList.size(); i++) {

            }
             */

            //serverWorld.getChunkManager().setChunkForced(new ChunkPos(0,0), true);

            MinecraftServer server = serverWorld.getServer();

            serverWorld.setChunkForced(0,0,true);


            if (server.getWorldRegistryKeys().contains(RegistryKey.of(Registry.WORLD_KEY, new Identifier("scarlet", "contraption"))))
            {
                ServerWorld contraptionDimension =  server.getWorld(RegistryKey.of(Registry.WORLD_KEY, new Identifier("scarlet", "contraption")));
                contraptionDimension.setChunkForced(0,0, true);
                Main.LOGGER.info(contraptionDimension.getForcedChunks().toString());
            }
        }
    }

    //Adding of the blocks to the worlds theme selves
    public static void update(ServerWorld serverWorld, BlockBox origin, BlockBox destination)
    {

    }

    public static BlockBox makeDestinationBlockBox(BlockBox origin)
    {
        return BlockBox.create(new Vec3i(origin.getMinX(), origin.getMinY(), origin.getMinZ()), new Vec3i(origin.getMaxX(), origin.getMaxY(), origin.getMaxZ()));
    }

    //assuming that the new contraption is added on the end of the contraptionList this will get the contraption's
    //position in the list and allow it to remove itself in the future by adding
    public int getListSize()
    {
        return this.ListSize;
    }

    //returns the contraption at the desired index
    public BlockBox getContraption(int listIndex)
    {
        return this.originList.get(listIndex);
    }
}
