package net.nitu.httydmod.events;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.DragonEntity;
import net.nitu.httydmod.entity.ModEntities;
import net.nitu.httydmod.entity.client.hobgobbler.HobgobblerModel;
import net.nitu.httydmod.entity.client.nightterror.NightTerrorModel;
import net.nitu.httydmod.entity.client.slug.SlugModel;
import net.nitu.httydmod.entity.custom.hobgobbler.HobgobblerEntity;
import net.nitu.httydmod.entity.custom.nightterror.NightTerrorEntity;
import net.nitu.httydmod.entity.custom.slug.SlugEnitity;

@EventBusSubscriber(modid = HttydMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HobgobblerModel.LAYER_LOCATION, HobgobblerModel::createBodyLayer);
        event.registerLayerDefinition(NightTerrorModel.LAYER_LOCATION, NightTerrorModel::createBodyLayer);
        event.registerLayerDefinition(SlugModel.LAYER_LOCATION, SlugModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.HOBGOBBLER.get(), HobgobblerEntity.createAttributes().build());
        event.put(ModEntities.SLUG.get(), SlugEnitity.createAttributes().build());
        event.put(ModEntities.NIGHT_TERROR.get(), NightTerrorEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                ModEntities.SLUG.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                SlugEnitity::canSlugSpawn,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(
                ModEntities.HOBGOBBLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                Animal::checkAnimalSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
