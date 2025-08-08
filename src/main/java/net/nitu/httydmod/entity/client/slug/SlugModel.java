package net.nitu.httydmod.entity.client.slug;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.nitu.httydmod.HttydMod;
import net.nitu.httydmod.entity.custom.slug.SlugEnitity;

public class SlugModel<T extends SlugEnitity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "slug"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart mid;
    private final ModelPart tail;

    public SlugModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.mid = this.body.getChild("mid");
        this.tail = this.body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        PartDefinition mid = body.addOrReplaceChild("mid", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.5F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -4.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(SlugEnitity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(SlugAnimations.ANIM_SLUG_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimState, SlugAnimations.ANIM_SLUG_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -10f, 10f);
        headPitch = Mth.clamp(headPitch, -5f, 5f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int colour) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
    }

    @Override
    public ModelPart root() { return body; }
}
