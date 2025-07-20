package net.nitu.httydmod.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.ModEntities;
import net.nitu.httydmod.entity.client.HobgoblinModel;
import net.nitu.httydmod.entity.custom.HobgoblinEntity;

@EventBusSubscriber(modid = HttydMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HobgoblinModel.LAYER_LOCATION, HobgoblinModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.HOBGOBLIN.get(), HobgoblinEntity.createAttributes().build());
    }
}
