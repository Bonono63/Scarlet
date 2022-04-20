package net.mrbonono63.scarlet.entities.renderers;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.mrbonono63.scarlet.entities.ContraptionEntity;

public class ContraptionRenderer extends EntityRenderer<ContraptionEntity> {

    public ContraptionRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(ContraptionEntity entity) {
        return new Identifier("minecraft", "textures/block/oak_planks.png");
    }

    @Override
    public boolean shouldRender(ContraptionEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
