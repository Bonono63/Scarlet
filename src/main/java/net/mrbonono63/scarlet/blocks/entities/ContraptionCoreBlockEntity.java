package net.mrbonono63.scarlet.blocks.entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbonono63.scarlet.Main;
import net.mrbonono63.scarlet.client.gui.ContraptionScreenDescription;

public class ContraptionCoreBlockEntity extends BlockEntity{
    public static int weight;
    public static int buoyancy;
    private boolean isAssembled;

    public ContraptionCoreBlockEntity(BlockPos pos, BlockState state) {
        super(SBlockEntity.CONTRAPTION_CORE_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ContraptionCoreBlockEntity be) {

    }

    //Tells the BlockEntity to assemble the contraption
    public void Assemble()
    {
        if (!isAssembled) {
            isAssembled = true;
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }
}