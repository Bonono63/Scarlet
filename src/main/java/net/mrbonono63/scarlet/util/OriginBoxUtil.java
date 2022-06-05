package net.mrbonono63.scarlet.util;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbonono63.scarlet.Main;

import java.util.ArrayList;
import java.util.List;

public class OriginBoxUtil {

    public static BlockBox calc(World world, BlockPos pos, int scale)
    {
        // we will calculate the length of the x-axis first, then the y, then the z, all in the negative direction
        // then we will follow up by going in the positive direction
        // the problem is we might snag blocks in the perpendicular points, so we should also look in those directions
        if (world != null && !world.isClient)
        {

            List<BlockPos> blockList = new ArrayList<>();
            for ( int x = -16; x < 16 ; x++)
            {
                for (int y = -16; y < 16 ; y++)
                {
                    for (int z = -16; z < 16 ; z++)
                    {
                        int i = pos.getX() + x;
                        int j = pos.getX() + y;
                        int k = pos.getX() + z;
                        //Main.LOGGER.info(x + " " + y + " " + z);
                        //Main.LOGGER.info(i + " " + j + " " + k);
                        BlockPos tempPos = new BlockPos(i, j , k);
                        if (!world.isAir(tempPos))
                        {
                            blockList.add(tempPos);
                        }
                    }
                }
            }

            Main.LOGGER.info(blockList.toString());

            BlockPos max = new BlockPos(0, 0, 0);
            BlockPos min = new BlockPos(0, 0, 0);

            for (BlockPos x : blockList) {
                if (x.getX() > max.getX() || x.getY() > max.getY() || x.getZ() > max.getZ())
                    max = x;
            }

            for (BlockPos x : blockList) {
                if (min.getX() < x.getX() || min.getY() < x.getY() || min.getZ() < x.getZ())
                    min = x;
            }

            Main.LOGGER.info(max.toString());
            Main.LOGGER.info(min.toString());

            BlockBox box = BlockBox.create(min, max);
            Main.LOGGER.info(box.toString());
            return box;
        }
        //if the world passed through isn't applicable
        return null;
    }
}