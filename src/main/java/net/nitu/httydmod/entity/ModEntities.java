package net.nitu.httydmod.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.custom.HobgobblerEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, HttydMod.MODID);

    public static final Supplier<EntityType<HobgobblerEntity>> HOBGOBBLER =
            ENTITY_TYPES.register("hobgoblin", () -> EntityType.Builder.of(HobgobblerEntity::new, MobCategory.CREATURE)
                    .sized(0.8f,0.8f).build("hobgoblin"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
