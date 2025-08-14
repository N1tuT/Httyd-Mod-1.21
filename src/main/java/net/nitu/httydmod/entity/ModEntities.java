package net.nitu.httydmod.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.custom.hobgobbler.HobgobblerEntity;
import net.nitu.httydmod.entity.custom.nightterror.NightTerrorEntity;
import net.nitu.httydmod.entity.custom.slug.SlugEnitity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, HttydMod.MODID);

    public static final Supplier<EntityType<HobgobblerEntity>> HOBGOBBLER =
            ENTITY_TYPES.register("hobgobbler", () -> EntityType.Builder.of(HobgobblerEntity::new, MobCategory.CREATURE)
                    .sized(0.8f,0.8f).build("hobgobbler"));
    public static final Supplier<EntityType<NightTerrorEntity>> NIGHT_TERROR =
            ENTITY_TYPES.register("night_terror", () -> EntityType.Builder.of(NightTerrorEntity::new, MobCategory.CREATURE)
                    .sized(0.8f,0.8f).build("night_terror"));

    public static final Supplier<EntityType<SlugEnitity>> SLUG =
            ENTITY_TYPES.register("slug", () -> EntityType.Builder.of(SlugEnitity::new, MobCategory.CREATURE)
                    .sized(0.85f,0.3f).build("slug"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
