package net.mrbonono63.scarlet.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.mrbonono63.scarlet.Main;

import java.util.ArrayList;

/*
*   Utility for cloning blocks across dimensions
* */
public class CloneUtil {

    public static void cloneToContraptionDimension(ServerWorld originDimension, BlockBox origin, BlockBox destination)
    {
        MinecraftServer server = originDimension.getServer();

        ServerWorld contraptionDimension =  server.getWorld(RegistryKey.of(Registry.WORLD_KEY, new Identifier("scarlet", "contraption")));

        /*
        * retrieve the block and entity data from the server world
        * */

        int v = origin.getBlockCountX()* origin.getBlockCountY()* origin.getBlockCountZ();
        if ( v > 2097152 )
        {
            Main.LOGGER.info("Volume of the BlockBox provided to the CloneUtil exceeds 2097152");
            int d = 2097152-v;
            Main.LOGGER.info("The Volume was " + d + " more Blocks than acceptable");
            return;
        }

        //List<BlockInfo> asd = new ArrayList<>();

        //TODO set the block with offsets in the scarlet dimension to the same blockstate in the origin dimension and then remove it from the overworld + block entity cloning
        for (int a  = 0 ; origin.getBlockCountX() > a ; a++)
        {
            for (int b = 0; origin.getBlockCountY() > b ; b++)
            {
                for (int c = 0; origin.getBlockCountZ() > c ; c++)
                {
                    int x = origin.getMinX() + a;
                    int y = origin.getMinY() + b;
                    int z = origin.getMinZ() + c;
                    CachedBlockPosition block = new CachedBlockPosition(originDimension, new BlockPos(x,y,z), true);
                    BlockState blockState = block.getBlockState();
                    BlockEntity blockEntity = block.getBlockEntity();
                    BlockPos destinationPos = new BlockPos(x,y,z);
                    if (contraptionDimension != null && !contraptionDimension.isClient) {
                        contraptionDimension.setBlockState(destinationPos, blockState);
                        if (blockEntity != null)
                        {
                            contraptionDimension.addBlockEntity(blockEntity);
                        }
                    }

                }
            }
        }

        /*
        * Copy the data retrieved to the Contraption Dimension
        * */

        /*
        * remove the data in the original dimension to air
        * */
    }

    public static void removalFromContraptionDimension(ServerWorld destinationServerWorld, BlockBox contraptionBox, BlockPos destinationCoordinates)
    {

    }
}
