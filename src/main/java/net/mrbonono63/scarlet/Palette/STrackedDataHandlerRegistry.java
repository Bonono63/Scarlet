package net.mrbonono63.scarlet.Palette;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.system.CallbackI;

import java.nio.ByteBuffer;

public class STrackedDataHandlerRegistry {

    public static void init()
    {}

    public STrackedDataHandlerRegistry()
    {}

    public static final TrackedDataHandler<BlockPalette> BLOCK_PALETTE = new TrackedDataHandler<BlockPalette>() {

        @Override
        public void write(PacketByteBuf buf, BlockPalette palette) {

            //a temporary block state variable to be translated and stored in the packet byte buffer
            BlockState temp;

            for (int x = 0; x < palette.getPaletteLength(); x++)
            {
                for (int y = 0; y < palette.getPaletteWidth(); y++)
                {
                    for (int z = 0; z < palette.getPaletteHeight(); z++)
                    {
                        //assigns to the current
                        temp = palette.getBlockStateAt(x, y, z);

                        //write the block position within the packet byte buffer for the block state
                        buf.writeBlockPos(new BlockPos(x,y,z));
                        //TODO make the block state stored Optional throughout the code so that air can be stored as null rather than a fully fledged block and save space in packets and storage
                        //write the raw block id just like an Ender man does for its block
                        buf.writeVarInt(palette.getRawBlockIDAt(x,y,z));
                        /*
                            We are using raw block ids along with the block position because storing the block palette
                            directly isn't built into minecraft and block states aren't either ender men only store
                            raw block ids not all of the blocks attributes (including block states).
                         */
                        //TODO write the code to store block state properties if ever want stair and fences to work
                    }
                }
            }
        }

        public BlockPalette read(PacketByteBuf buf) {
            BlockPalette palette;
            BlockPaletteElement temp;

            return new BlockPalette();
        }

        @Override
        public BlockPalette copy(BlockPalette value) {
            return null;
        }
    };
}
