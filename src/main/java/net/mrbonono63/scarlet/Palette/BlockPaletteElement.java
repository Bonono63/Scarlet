package net.mrbonono63.scarlet.Palette;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class BlockPaletteElement {

    private BlockPos pos;
    private BlockState state;

    public BlockPaletteElement(BlockPos pos , BlockState state)
    {
        this.pos = pos;
        this.state = state;
    }

    public BlockPos getPos()
    {
        return this.pos;
    }

    public BlockState getState()
    {
        return this.state;
    }
}
