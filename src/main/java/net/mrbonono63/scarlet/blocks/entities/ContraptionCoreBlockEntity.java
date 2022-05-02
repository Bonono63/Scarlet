package net.mrbonono63.scarlet.blocks.entities;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContraptionCoreBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory {

    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {

        return new BoxScreenHandler(syncId, playerInventory, this);
    }

    public ContraptionCoreBlockEntity(BlockPos pos, BlockState state) {
        super(SBlockEntity.CONTRAPTION_CORE_BLOCK_ENTITY, pos, state);
    }
    public static void tick(World world, BlockPos pos, BlockState state, ContraptionCoreBlockEntity be) {
    }

    @Override
    public Text getDisplayName() {
        return Text.of("AMONGUS HAHA FUNNY");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

    }
}