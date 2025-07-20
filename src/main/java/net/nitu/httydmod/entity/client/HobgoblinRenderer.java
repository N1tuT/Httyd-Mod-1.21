package net.nitu.httydmod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.HobgoblinVariant;
import net.nitu.httydmod.entity.custom.HobgoblinEntity;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

public class HobgoblinRenderer extends MobRenderer<HobgoblinEntity, HobgoblinModel<HobgoblinEntity>> {
    private static final Map<HobgoblinVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HobgoblinVariant.class), map -> {
                map.put(HobgoblinVariant.PURPLE,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/hobgoblin/hobgoblin_purple.png"));
                map.put(HobgoblinVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "textures/entity/hobgoblin/hobgoblin_blue.png"));

            });



    public HobgoblinRenderer(EntityRendererProvider.Context context) {
        super(context, new HobgoblinModel<>(context.bakeLayer(HobgoblinModel.LAYER_LOCATION)),0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(HobgoblinEntity hobgoblinEntity) {
        return LOCATION_BY_VARIANT.get(hobgoblinEntity.getVariant());
    }

    @Override
    public void render(HobgoblinEntity entity, float entityYaw, float partialTicks,
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
