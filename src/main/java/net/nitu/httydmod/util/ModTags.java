package net.nitu.httydmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.nitu.httydmod.HttydMod;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DRAGON_SCALE = createTag("dragon_scale");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, name));
        }
    }
}
