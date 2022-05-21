package net.mrbonono63.scarlet.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.mrbonono63.scarlet.Main;
import net.mrbonono63.scarlet.util.CloneUtil;

import java.util.ArrayList;
import java.util.List;

public class ContraptionDimensionHandler {

    //List of the contraptions the server is keeping track of
    public List<Contraption> activeList = new ArrayList<>();
    public List<Contraption> addList = new ArrayList<>();
    public List<Contraption> removeList = new ArrayList<>();

    int previousListSize = 0;
    int ListSize = 0;

    //maximum contraption length in blocks
    public static final int MAXIMUM_BLOCK_LENGTH = 128;
    //maximum contraption length in chunks
    public static final int MAXIMUM_CONTRAPTION_CHUNK_LENGTH = 8;
    //space in between allocated chunk spaces
    public static final int CHUNK_OFFSET = 1;

    //constructor
    public ContraptionDimensionHandler() {

    }

    //List management
    public int addContraption(Contraption contraption)
    {
        addList.add(new Contraption(contraption.getOriginBlockBox(), contraption.getServerWorld()));
        return addList.size();
    }

    public  void removeContraption(int i)
    {
        removeList.add(activeList.get(i));
    }

    public int offsetChunk(int a)
    {
        /*  Current chunk position (inside the loop)
            + 1 (add the buffer we have for the current contraption)
             + 1*number of active contraptions (adjust for number of current offsets already present)
             + the max contraption length (8 chunks)*the active contraption list

             The goal of this function is to force load the chunks the contraption will use
             I don't know how to manage the chunks in a better way perhaps a list of chunk coordinates or a custom data
             type could organize and manage the chunk loading better, however that is for future improvement
             Also only force loading chunks with actual content (blocks or block entities) will improve performance.
         */
        return a+CHUNK_OFFSET+CHUNK_OFFSET*activeList.size()+MAXIMUM_CONTRAPTION_CHUNK_LENGTH*activeList.size();
    }

    //Similar to the chunk offset however instead it calculates the block coordinates instead of the chunk coordinates.
    public int offsetPosition(int a)
    {

        /*  Current chunk position (inside the loop)
            + 1 (add the buffer we have for the current contraption) * 16
             + 1*number of active contraptions (adjust for number of current offsets already present)
             + the max contraption length (8 chunks)*the active contraption list
        * */

        return a+CHUNK_OFFSET*16+CHUNK_OFFSET*16*activeList.size()+MAXIMUM_CONTRAPTION_CHUNK_LENGTH*16*activeList.size();
    }

    //Run every tick to add and remove the contraption block boxes passed through and manage that
    public void tick(ServerWorld serverWorld)
    {
        this.previousListSize = this.ListSize;
        this.ListSize = activeList.size();

        if (!serverWorld.isClient()) {
            MinecraftServer server = serverWorld.getServer();

            //serverWorld.setChunkForced(0,0,true);

            if (server.getWorldRegistryKeys().contains(RegistryKey.of(Registry.WORLD_KEY, new Identifier("scarlet", "contraption"))))
            {
                ServerWorld contraptionDimension =  server.getWorld(RegistryKey.of(Registry.WORLD_KEY, new Identifier("scarlet", "contraption")));

                if (!addList.isEmpty())
                {
                    for (int i = 0 ; i < addList.size() ;i++) {
                        //Get the first Box in the list
                        BlockBox origin = addList.get(0).getOriginBlockBox();

                        //allocate forced chunks for the contraption
                        for (int x = 0; x < MAXIMUM_CONTRAPTION_CHUNK_LENGTH; x++) {
                            for (int y = 0; y < 8; y++) {
                                contraptionDimension.setChunkForced(offsetChunk(x), offsetChunk(y), true);
                            }
                        }

                        BlockBox destination = makeDestinationBlockBox();

                        CloneUtil.cloneToContraptionDimension(serverWorld, origin, destination);

                        //add the box to the active list and remove the box from the add list
                        activeList.add(addList.get(0));
                        addList.remove(0);
                        Main.LOGGER.info("new contraption added to the contraption dimension");
                    }
                }

                //Main.LOGGER.info(contraptionDimension.getForcedChunks().toString());
            }
        }
    }

    //decides the space the contraption will be placed inside of
    public BlockBox makeDestinationBlockBox()
    {
        // number of blocks allocated to each contraption + the buffer chunks length in blocks
        int x = activeList.size()*MAXIMUM_BLOCK_LENGTH+16*activeList.size();
        int y = activeList.size()*MAXIMUM_BLOCK_LENGTH+16*activeList.size();
        int z = 0;

        // the origin pos + the size of the allocated space + maximum height
        int i = x+MAXIMUM_BLOCK_LENGTH;
        int j = y+MAXIMUM_BLOCK_LENGTH;
        int k = 128;

        return BlockBox.create(new Vec3i(x, y, z), new Vec3i(i, j, k));
    }

    //assuming that the new contraption is added on the end of the contraptionList this will get the contraption's
    //position in the list and allow it to remove itself in the future by adding
    public int getListSize()
    {
        return this.ListSize;
    }

    //returns the contraption at the desired index
    public Contraption getContraption(int listIndex)
    {
        return this.activeList.get(listIndex);
    }
}
