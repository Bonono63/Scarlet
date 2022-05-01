package net.mrbonono63.scarlet.client;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.mrbonono63.scarlet.blocks.entities.ContraptionCoreBlockEntity;
import net.mrbonono63.scarlet.server.SScreenHandlers;

public class BoxScreenHandler extends ScreenHandler {



    public BoxScreenHandler(int i) {
        this(SScreenHandlers.AMOGUS_SUSSY, i);
    }


    public BoxScreenHandler(ScreenHandlerType<?> type, int i) {
        super(type, i);
    }

    public boolean canUse(PlayerEntity player) {
        return false;
    }
}