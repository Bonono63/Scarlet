package net.mrbonono63.scarlet.server;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

public class Contraption {

    public BlockBox originBlockBox;
    public ServerWorld originDimension;

    public Contraption(BlockBox originBlockBox, ServerWorld originServer)
    {
        this.originBlockBox = BlockBox.create(BlockPos.ZERO, BlockPos.ZERO);
        this.originDimension = originServer;
    }

    public void setOriginBlockBox(BlockPos begin, BlockPos end)
    {
        this.originBlockBox = BlockBox.create(begin, end);
    }

    public BlockBox getOriginBlockBox() {
        return this.originBlockBox;
    }

    public ServerWorld getServerWorld()
    {
        return this.originDimension;
    }
}
