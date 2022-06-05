package net.mrbonono63.scarlet.client.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
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

    private final PropertyDelegate propertyDelegate;

    public ContraptionScreenDescription(int syncId, PlayerInventory playerInventory, PropertyDelegate propertyDelegate1) {
        super(SScreenHandlers.CONTRAPTION_SCREEN_HANDLER_TYPE, syncId, playerInventory, null, propertyDelegate1);
        checkDataCount(propertyDelegate1, 2);
        this.propertyDelegate = propertyDelegate1;
        this.addProperties(propertyDelegate);

        checkDataCount(propertyDelegate1, 2);

        setTitleVisible(false);
        WGridPanel grid = (WGridPanel) rootPanel;

        //TODO make a custom toggle widget that can be disabled if needed (you can add a timer in between the time you can toggle it)
        WToggleButton assembled_toggle = new WToggleButton(
                new TranslatableText("contraptions.scarlet.assemble")
        ) {
            @Override
            public void onToggle(boolean on) {
            }
        };

        assembled_toggle.setToggle(true);

        rootPanel.setSize(6*18, 7*18);

        //The player inventory
        ((WGridPanel) getRootPanel()).add(createPlayerInventoryPanel(), 0, 2);
        grid.add(assembled_toggle, 2, 1, 5, 1);
        grid.add(new WText(new LiteralText("Weight: "+propertyDelegate1.get(0)+"Kg")), 0, 0, 10, 1);
        getRootPanel().validate(this);
    }
}
