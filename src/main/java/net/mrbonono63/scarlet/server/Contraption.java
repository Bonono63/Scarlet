package net.mrbonono63.scarlet.server;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

public class Contraption {

    public BlockBox originBlockBox;
    public BlockBox contraptionBlockBox;

    public Contraption(BlockBox originBlockBox, BlockBox contraptionBlockBox)
    {
        this.originBlockBox = BlockBox.create(BlockPos.ZERO, BlockPos.ZERO);
        this.contraptionBlockBox = BlockBox.create(BlockPos.ZERO, BlockPos.ZERO);
    }

    public void setOriginBlockBox(BlockPos begin, BlockPos end)
    {
        this.originBlockBox = BlockBox.create(begin, end);
    }

    public void setContraptionBlockBox(BlockPos begin, BlockPos end)
    {
        this.contraptionBlockBox = BlockBox.create(begin, end);
    }
}
