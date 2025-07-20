package net.nitu.httydmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nitu.httydmod.HttydMod;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HttydMod.MODID);

    public static final Supplier<CreativeModeTab> HTTYD_TAB = CREATIVE_MODE_TAB.register("httyd_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NIGHT_FURY_SCALE.get()))
                    .title(Component.translatable("creativetab.httydmod.httyd"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.NIGHT_FURY_SCALE);
                        output.accept(ModItems.HOBGOBLIN_SPAWN_EGG);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
