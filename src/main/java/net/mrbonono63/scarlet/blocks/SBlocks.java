package net.mrbonono63.scarlet.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.mrbonono63.scarlet.Main;
import net.mrbonono63.scarlet.items.SItems;

import java.util.function.Function;

public class SBlocks {

    public static final Block CONTRAPTION_CORE = register("contraption_core", new ContraptionCore(FabricBlockSettings.copy(Blocks.BEDROCK)));

    public SBlocks() {}

    public static void init()
    {}

    static <T extends Block> T register(String name, T block, Item.Settings settings) {
        return register(name, block, new BlockItem(block, settings));
    }

    static <T extends Block> T register(String name, T block) {
        return register(name, block, new Item.Settings().group(ItemGroup.MISC));
    }

    static <T extends Block> T register(String name, T block, Function<T, BlockItem> itemFactory) {
        return register(name, block, itemFactory.apply(block));
    }

    static <T extends Block> T register(String name, T block, BlockItem item) {
        T b = Registry.register(Registry.BLOCK, Main.identifier(name), block);
        if (item != null) {
            SItems.register(name, item);
        }
        return b;
    }
}
