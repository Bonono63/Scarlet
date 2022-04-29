package net.mrbonono63.scarlet.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbonono63.scarlet.Main;

public class ContraptionCoreBlockEntity extends BlockEntity {

    public ContraptionCoreBlockEntity(BlockPos pos, BlockState state) {
        super(SBlockEntity.CONTRAPTION_CORE_BLOCK_ENTITY, pos, state);
    }
    public static void tick(World world, BlockPos pos, BlockState state, ContraptionCoreBlockEntity be) {
        Main.LOGGER.info("deez");
    }
}