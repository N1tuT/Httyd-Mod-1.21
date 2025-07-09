package net.nitu.httydmod.item;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nitu.httydmod.HttydMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HttydMod.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
