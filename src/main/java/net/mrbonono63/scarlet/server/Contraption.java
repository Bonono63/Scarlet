package net.mrbonono63.scarlet.server;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class Contraption {

    public Identifier originDimensionID;
    public BlockPos originCorner1;
    public BlockPos originCorner2;
    public BlockPos contraptionCorner1;
    public BlockPos contraptionCorner2;

    public Contraption(Identifier origin, BlockPos originCorner1, BlockPos originCorner2, BlockPos contraptionCorner1, BlockPos contraptionCorner2)
    {
        this.originDimensionID = origin;
        this.originCorner1 = originCorner1;
        this.originCorner2 = originCorner2;
        this.contraptionCorner1 = contraptionCorner1;
        this.contraptionCorner2 = contraptionCorner2;
    }


    //return the 1st origin corner
    public BlockPos getOriginCorner1()
    {
        return this.originCorner1;
    }

    //return the 2nd origin corner
    public BlockPos getOriginCorner2()
    {
        return this.originCorner2;
    }

    //return the 1st contraption corner
    public BlockPos getContraptionCorner1()
    {
        return this.contraptionCorner1;
    }

    //return the 2nd contraption corner
    public BlockPos getContraptionCorner2()
    {
        return this.contraptionCorner2;
    }

    //set the 1st origin corner
    public void setOriginCorner1(BlockPos pos)
    {
        this.originCorner1 = pos;
    }

    //set the 2nd origin corner
    public void setOriginCorner2(BlockPos pos)
    {
        this.originCorner2 = pos;
    }

    //set the 1st contraption corner
    public void setContraptionCorner1(BlockPos pos)
    {
        this.contraptionCorner1 = pos;
    }

    //set the 2nd contraption corner
    public void setContraptionCorner2(BlockPos pos)
    {
        this.contraptionCorner2 = pos;
    }

    //set the dimension ID (useful for incase it moves to the nether, end, or other dimensions)
    public void setDimension(Identifier id)
    {
        this.originDimensionID = id;
    }
}
