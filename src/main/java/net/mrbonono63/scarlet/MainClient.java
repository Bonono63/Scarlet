package net.mrbonono63.scarlet;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.mrbonono63.scarlet.client.gui.ContraptionScreenDescription;
import net.mrbonono63.scarlet.client.gui.SScreenHandlers;
import net.mrbonono63.scarlet.entities.SEntity;
import net.mrbonono63.scarlet.entities.renderers.ContraptionRenderer;
import net.mrbonono63.scarlet.network.EntitySpawnPacket;

import java.util.UUID;

import static net.mrbonono63.scarlet.network.SPackets.PacketID;

public class MainClient implements ClientModInitializer {

    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setPitch(pitch);
                e.setYaw(yaw);
                e.setId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(SEntity.CONTRAPTION_ENTITY_TYPE, ContraptionRenderer::new);

        HandledScreens.<ContraptionScreenDescription, CottonInventoryScreen<ContraptionScreenDescription>>register(
                SScreenHandlers.CONTRAPTION_SCREEN_HANDLER_TYPE, CottonInventoryScreen::new
        );

        receiveEntityPacket();
    }
}
