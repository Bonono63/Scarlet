package net.mrbonono63.scarlet.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class NBTUtil {

    /*
    *   Minecraft doesn't have its own way of writing Block Pos/Box to NbtCompounds
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

    public static BlockBox readBlockBox(String key, NbtCompound nbt)
    {
        int i = nbt.getInt(key+"i");
        int j = nbt.getInt(key+"j");
        int k = nbt.getInt(key+"k");

        int x = nbt.getInt(key+"x");
        int y = nbt.getInt(key+"y");
        int z = nbt.getInt(key+"z");

        return BlockBox.create(new Vec3i(i, j, k), new Vec3i(x, y, z));
    }

    public static void writeBlockBox(String key, BlockBox box, NbtCompound nbt)
    {
        nbt.putInt(key+"i", box.getMinX());
        nbt.putInt(key+"j", box.getMinY());
        nbt.putInt(key+"k", box.getMinZ());

        nbt.putInt(key+"x", box.getMaxX());
        nbt.putInt(key+"y", box.getMaxY());
        nbt.putInt(key+"z", box.getMaxZ());
    }
}