package net.nitu.httydmod.entity.client.nightterror;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.custom.nightterror.NightTerrorEntity;
import net.nitu.httydmod.entity.custom.nightterror.NightTerrorVariant;

import java.util.Map;

public class NightTerrorRenderer extends MobRenderer<NightTerrorEntity, NightTerrorModel<NightTerrorEntity>> {
    private static final Map<NightTerrorVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(NightTerrorVariant.class), map -> {
                map.put(NightTerrorVariant.BLACK,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/night_terror/night_terror_black.png"));
                map.put(NightTerrorVariant.WHITE,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/night_terror/night_terror_white.png"));
                map.put(NightTerrorVariant.BLACK_SADDLED,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/night_terror/night_terror_black_saddled.png"));
                map.put(NightTerrorVariant.WHITE_SADDLED,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/night_terror/night_terror_white_saddled.png"));

            });

    public NightTerrorRenderer(EntityRendererProvider.Context context) {
        super(context, new NightTerrorModel<>(context.bakeLayer(NightTerrorModel.LAYER_LOCATION)), 0.25f);
    }


    @Override
    public ResourceLocation getTextureLocation(NightTerrorEntity nightTerrorEntity) {
        return LOCATION_BY_VARIANT.get(nightTerrorEntity.getVariant());
    }

    @Override
    public void render(NightTerrorEntity entity, float entityYaw, float partialTicks,
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
