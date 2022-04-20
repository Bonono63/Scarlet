package net.mrbonono63.scarlet.Palette;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BlockPalette {

    BlockState[][][] palette;

    public BlockPalette() {

    }

    //returns the total length of the palette
    public int getPaletteSize()
    {
        return palette.length;
    }

    //returns the length of the x coordinate
    public int getPaletteLength()
    {
        return palette.length;
    }

    //returns the length of the y coordinate
    public int getPaletteWidth()
    {
        return palette[0].length;
    }

    //returns the length of the z coordinate
    public int getPaletteHeight()
    {
        return palette[0][0].length;
    }

    //The theoretical limit for the block palette array on each axis (might be set to an arbitrary value in the future to stop people from crashing their game)
    public int getPaletteMaxSize()
    {
        return Integer.MAX_VALUE;
    }


    //Adds a provided block state to the palette
    public void setBlockStateAt(long x, long y, long z, BlockState blockState)
    {
        Vec3i coordinates = snap(x, y, z);
        palette[coordinates.getX()][coordinates.getY()][coordinates.getZ()] = blockState;
    }

    public void setBlockStateAt(Vec3d vec3d, BlockState blockState)
    {
        Vec3i coordinates = snap(vec3d);
        palette[coordinates.getX()][coordinates.getY()][coordinates.getZ()] =  blockState;
    }


    //returns the block state based on the coordinates provided
    public BlockState getBlockStateAt(Vec3d vec3d)
    {
        Vec3i coordinates = snap(vec3d);
        return palette[coordinates.getX()][coordinates.getY()][coordinates.getZ()];
    }

    public BlockState getBlockStateAt(long x, long y, long z)
    {
        Vec3i coordinates = snap(x,y,z);
        return palette[coordinates.getX()][coordinates.getY()][coordinates.getZ()];
    }

    public int getRawBlockIDAt(long x, long y, long z)
    {
        Vec3i coordinates = snap(x,y,z);
        return Block.getRawIdFromState(palette[coordinates.getX()][coordinates.getY()][coordinates.getZ()]);
    }


    //rounds a provided 3 dimensional vector using the round math function
    public static Vec3i snap(Vec3d vec3d)
    {
        return new Vec3i((int) Math.round(vec3d.x), (int) Math.round(vec3d.y), (int) Math.round(vec3d.z));
    }

    public static Vec3i snap(long x, long y, long z)
    {
        return new Vec3i(Math.round(x), Math.round(y), Math.round(z));
    }
}
