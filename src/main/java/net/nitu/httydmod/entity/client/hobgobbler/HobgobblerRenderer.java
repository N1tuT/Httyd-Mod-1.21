package net.nitu.httydmod.entity.client.hobgobbler;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.custom.hobgobbler.HobgobblerVariant;
import net.nitu.httydmod.entity.custom.hobgobbler.HobgobblerEntity;

import java.util.Map;

public class HobgobblerRenderer extends MobRenderer<HobgobblerEntity, HobgobblerModel<HobgobblerEntity>> {
    private static final Map<HobgobblerVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HobgobblerVariant.class), map -> {
                map.put(HobgobblerVariant.PURPLE,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/hobgobbler/hobgobbler_purple.png"));
                map.put(HobgobblerVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/hobgobbler/hobgobbler_blue.png"));

            });

    public HobgobblerRenderer(EntityRendererProvider.Context context) {
        super(context, new HobgobblerModel<>(context.bakeLayer(HobgobblerModel.LAYER_LOCATION)),0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(HobgobblerEntity hobgobblerEntity) {
        return LOCATION_BY_VARIANT.get(hobgobblerEntity.getVariant());
    }

    @Override
    public void render(HobgobblerEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        LOCATION_BY_VARIANT.get(entity.getVariant());

        if (entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
