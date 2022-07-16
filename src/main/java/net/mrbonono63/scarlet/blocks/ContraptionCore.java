package net.mrbonono63.scarlet.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.mrbonono63.scarlet.Main;
import net.mrbonono63.scarlet.util.OriginBoxUtil;

public class ContraptionCore extends Block {

    public static BlockBox box = BlockBox.create(Vec3i.ZERO, Vec3i.ZERO);

    public ContraptionCore(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Main.LOGGER.info("Clicked "+ this);
        Main.LOGGER.info("Pos: "+ pos);
        int scale = 16;
        if (OriginBoxUtil.calc(world, pos, scale) != null)
        {
            box = OriginBoxUtil.calc(world, pos, scale);
            Main.LOGGER.info(box.getDimensions().toString());
        } else
        {
            Main.LOGGER.info("OriginBoxUtil.Calc returned null, this means the contraption exceeded size limits");
            return ActionResult.FAIL;
        }
        return ActionResult.SUCCESS;
    }
}