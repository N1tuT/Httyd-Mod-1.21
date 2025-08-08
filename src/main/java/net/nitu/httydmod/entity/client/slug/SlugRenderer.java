package net.nitu.httydmod.entity.client.slug;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.custom.slug.SlugEnitity;
import net.nitu.httydmod.entity.custom.slug.SlugVariant;

import java.util.Map;

public class SlugRenderer extends MobRenderer<SlugEnitity, SlugModel<SlugEnitity>> {
    private static final Map<SlugVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SlugVariant.class), map -> {
                map.put(SlugVariant.BROWN,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/slug/slug_brown.png"));
                map.put(SlugVariant.RED,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/slug/slug_red.png"));
                map.put(SlugVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/slug/slug_green.png"));
            });

    public SlugRenderer(EntityRendererProvider.Context context) {
        super(context, new SlugModel<>(context.bakeLayer(SlugModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(SlugEnitity slugEnitity) {
        return LOCATION_BY_VARIANT.get(slugEnitity.getVariant());
    }

    @Override
    public void render(SlugEnitity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        LOCATION_BY_VARIANT.get(entity.getVariant());

        if (entity.isBaby()) {
            poseStack.scale(0.5f,0.5f,0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
