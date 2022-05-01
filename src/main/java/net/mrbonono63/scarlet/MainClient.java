package net.mrbonono63.scarlet;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.mrbonono63.scarlet.client.BoxScreenHandler;
import net.mrbonono63.scarlet.entities.renderers.ContraptionRenderer;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.server.SScreenHandlers;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(SEntity.CONTRAPTION_ENTITY_TYPE, ContraptionRenderer::new);
        HandledScreens.register(SScreenHandlers.AMOGUS_SUSSY, BoxScreenHandler::new);
    }
}
