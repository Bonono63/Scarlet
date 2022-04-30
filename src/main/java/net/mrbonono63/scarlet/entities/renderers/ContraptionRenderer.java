package net.mrbonono63.scarlet.entities.renderers;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
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
    public void render(ContraptionEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();

        blockRenderManager.renderBlockAsEntity(Blocks.OAK_PLANKS.getDefaultState(), matrices, vertexConsumers, light, 1);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
