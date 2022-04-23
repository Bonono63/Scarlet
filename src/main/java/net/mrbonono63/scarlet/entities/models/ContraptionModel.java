package net.mrbonono63.scarlet.entities.models;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.mrbonono63.scarlet.Palette.BlockPalette;
import net.mrbonono63.scarlet.entities.ContraptionEntity;

public class ContraptionModel extends EntityModel<ContraptionEntity> {

    //BLOCK ARRAY
    //stores the block id in a point inside an array
    //the array's size correlates with the size of the assembled entity
    private BlockPalette blocks;

    //local declaration of the model's yaw pitch and roll which will correlate with the entity class's numbers
    private int yaw;
    private int pitch;
    private int roll;


    @Override
    public void setAngles(ContraptionEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        //add/sort the blocks passed from the entity to the model and then drawn

        //TODO add rendering stuff
    }
}
