package net.mrbonono63.scarlet.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.mrbonono63.scarlet.Main;
import net.mrbonono63.scarlet.blocks.entities.ContraptionCoreBlockEntity;
import net.mrbonono63.scarlet.blocks.entities.SBlockEntity;
import net.mrbonono63.scarlet.entities.ContraptionEntity;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.server.Contraption;
import net.mrbonono63.scarlet.util.OriginBoxUtil;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Objects;

public class ContraptionCore extends BlockWithEntity implements BlockEntityProvider {

    public static BlockBox box = BlockBox.create(Vec3i.ZERO, Vec3i.ZERO);

    public ContraptionCore(Settings settings) {
        super(settings);
    }


    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ContraptionCoreBlockEntity(pos, state);
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, SBlockEntity.CONTRAPTION_CORE_BLOCK_ENTITY, (world1, pos, state1, be) -> ContraptionCoreBlockEntity.tick(world1, pos, state1, be));
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