package net.mrbonono63.scarlet.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.mrbonono63.scarlet.Main;

public class SItems {

    public static final Item BEAN = register("bean", new Item(newSettings()));

    public SItems(){}

    public static void init()
    {}

    static Item.Settings newSettings() {
        return new Item.Settings().group(ItemGroup.MISC);
    }

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, Main.identifier(name),item);
    }


}
