package net.mrbonono63.scarlet;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.entities.renderers.ContraptionRenderer;

public class MainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(SEntity.CONTRAPTION_ENTITY_TYPE, ContraptionRenderer::new);

    }
}
