package net.mrbonono63.scarlet.server;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.ChunkManager;

import java.util.*;

public class ContraptionDimensionHandler {

    //List of the contraptions the server is keeping track of
    public  List<BlockBox> originList = new ArrayList<>();
    public  List<BlockBox> denstinationList = new ArrayList<>();

    int previousListSize = 0;
    int ListSize = 0;

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

        if (!serverWorld.isClient())
        {
            ChunkManager chunkManager = serverWorld.getChunkManager();
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
/*
    public static boolean checkConflicts()
    {

    }


 */
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
