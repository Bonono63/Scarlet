package net.mrbonono63.scarlet.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class NBTReadBlockPos {

    /*
    *   Minecraft doesn't have its own way of writing Block Pos to NbtCompounds
    *   So I did it myself
    *   This just writes 3 ints with the original key + ijk for each coordinate
    *   this works for writing and reading and isn't super complicated
    * */
    public static BlockPos readBlockPos(String key, NbtCompound nbt)
    {
        int i = nbt.getInt(key+"i");
        int j = nbt.getInt(key+"j");
        int k = nbt.getInt(key+"k");

        return new BlockPos(i,j,k);
    }

    public static void writeBlockPos(String key, BlockPos pos, NbtCompound nbt)
    {
        nbt.putInt(key+"i", pos.getX());
        nbt.putInt(key+"j", pos.getY());
        nbt.putInt(key+"k", pos.getZ());
    }
}