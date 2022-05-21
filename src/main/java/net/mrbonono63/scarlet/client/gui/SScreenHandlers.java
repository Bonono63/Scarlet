package net.mrbonono63.scarlet.client.gui;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;
import net.mrbonono63.scarlet.Main;

public class SScreenHandlers {

    public static ScreenHandlerType<ContraptionScreenDescription> CONTRAPTION_SCREEN_HANDLER_TYPE;

    public static void init() {
        //Simple gui that will be used in the contraption block entity
        CONTRAPTION_SCREEN_HANDLER_TYPE = new ScreenHandlerType<>((int syncId, PlayerInventory inventory) -> new ContraptionScreenDescription(syncId, inventory, ScreenHandlerContext.EMPTY));
        Registry.register(Registry.SCREEN_HANDLER, Main.identifier("contraption_screen"), CONTRAPTION_SCREEN_HANDLER_TYPE);
    }

    public SScreenHandlers()
    {}
}
