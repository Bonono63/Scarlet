package net.mrbonono63.scarlet.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import net.mrbonono63.scarlet.Main;

public class SEntity {

    public SEntity() {}

    public static final EntityType<ContraptionEntity> CONTRAPTION_ENTITY_TYPE = register("contraption", EntityType.Builder.create(ContraptionEntity::new, SpawnGroup.MISC));

    public static void init() {}

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, Main.identifier(id), type.build(id));
    }
}
