package net.mrbonono63.scarlet.server;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.mrbonono63.scarlet.client.BoxScreenHandler;
import net.mrbonono63.scarlet.client.Screen;
import org.lwjgl.system.CallbackI;

public class SScreenHandlers {

   public static final ScreenHandlerType<BoxScreenHandler> AMOGUS_SUSSY= new ScreenHandlerType<>(BoxScreenHandler::new);

    public static void init(){
        Registry.register(Registry.SCREEN_HANDLER, new Identifier("scarlet:amogus_sussy"));
    }
}
