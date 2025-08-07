package net.nitu.httydmod.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.ModEntities;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HttydMod.MODID);

    public static final DeferredItem<Item> NIGHT_FURY_SCALE = ITEMS.register("night_fury_scale",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> HOBGOBBLER_SPAWN_EGG = ITEMS.register("hobgobbler_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HOBGOBBLER, 0x320553, 0x87974e,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
