package net.mrbonono63.scarlet.client.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbonono63.scarlet.Main;
import net.mrbonono63.scarlet.blocks.entities.ContraptionCoreBlockEntity;

public class ContraptionScreenDescription extends SyncedGuiDescription {

    public static BlockPos getBlockPos(ScreenHandlerContext c){
        return c.get((world, pos) -> pos, null);
    }

    public static World getWorld(ScreenHandlerContext c){
        return c.get((world, pos) -> world , null);
    }

    public ContraptionScreenDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(SScreenHandlers.CONTRAPTION_SCREEN_HANDLER_TYPE, syncId, playerInventory, null, null);
        setTitleVisible(false);
        WGridPanel grid = (WGridPanel) rootPanel;

        BlockPos position = null;
        ServerWorld serverWorld = null;
        ContraptionCoreBlockEntity entity = null;

        int weight = -1;
        if (context != null)
        {
            BlockPos pos = getBlockPos(context);
            if (pos != null)
            {
                position = pos;
            }
            World world = getWorld(context);
            if (world != null && !world.isClient)
            {
                serverWorld = (ServerWorld) world;
            }
        }

        if (serverWorld != null)
        {
             entity = (ContraptionCoreBlockEntity) serverWorld.getBlockEntity(position);
        }

        if (entity != null)
        {
            weight = entity.getWeight();
            Main.LOGGER.info(String.valueOf(weight));
        }

        //TODO make a custom toggle widget that can be disabled if needed (you can add a timer in between the time you can toggle it)
        WToggleButton assembled_toggle = new WToggleButton(
                new TranslatableText("contraptions.scarlet.assemble")
        ) {
            @Override
            public void onToggle(boolean on) {
            }
        };

        assembled_toggle.setToggle(true);

        rootPanel.setSize(5*18, 7*18);

        //The player inventory
        ((WGridPanel) getRootPanel()).add(createPlayerInventoryPanel(), 0, 2);
        grid.add(assembled_toggle, 2, 1, 5, 1);
        grid.add(new WText(new LiteralText("Weight: "+weight+"Kg")), 0, 0, 10, 1);
        getRootPanel().validate(this);
    }
}
