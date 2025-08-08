package net.nitu.httydmod.entity.client.hobgobbler;

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
import net.nitu.httydmod.entity.custom.hobgobbler.HobgobblerEntity;

public class HobgobblerModel<T extends HobgobblerEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "hobgoblin"), "main");

    private final ModelPart body;
    private final ModelPart head;

    public HobgobblerModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body1 = body.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 4.0F));

        PartDefinition bone2 = legs.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(32, 0).addBox(-0.5F, 0.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -1.0F));

        PartDefinition bone4 = legs.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(32, 6).addBox(-1.5F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -2.0F, -1.0F));

        PartDefinition bone5 = legs.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(12, 32).addBox(-1.5F, -1.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -2.0F, -7.0F));

        PartDefinition bone6 = legs.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(32, 12).addBox(-0.5F, -1.0F, -0.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -8.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(22, 32).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(32, 32).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 41).addBox(-0.5F, -3.5F, 4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-0.5F, -4.5F, 5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -5.0F));

        PartDefinition spike_head = head.addOrReplaceChild("spike_head", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 5.0F));

        PartDefinition cube_r1 = spike_head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(42, 30).addBox(-0.5F, -3.5F, 0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 41).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -7.5F, -0.6109F, 0.0F, 0.0F));

        PartDefinition head_structure = head.addOrReplaceChild("head_structure", CubeListBuilder.create().texOffs(18, 16).addBox(-1.5F, -9.0F, -7.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 5.0F));

        PartDefinition cube_r2 = head_structure.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, -3.5F, -0.6F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8441F, -5.0F, -7.0085F, 0.0F, 0.6109F, 0.0F));

        PartDefinition cube_r3 = head_structure.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 24).addBox(0.0F, -5.5F, 0.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2132F, -3.0F, -7.9096F, 0.0F, -0.6109F, 0.0F));

        PartDefinition head_structure2 = head.addOrReplaceChild("head_structure2", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 1.0F));

        PartDefinition cube_r4 = head_structure2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(14, 24).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, -4.0F, -0.0873F, 0.6109F, 0.0F));

        PartDefinition cube_r5 = head_structure2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, 0.0F, 0.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, -4.0F, -0.0873F, -0.6109F, 0.0F));

        PartDefinition cube_r6 = head_structure2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -4.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 5.0F));

        PartDefinition right_r1 = eyes.addOrReplaceChild("right_r1", CubeListBuilder.create().texOffs(40, 26).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -6.0F, -6.0F, 0.0F, -0.6109F, 0.0F));

        PartDefinition left_r1 = eyes.addOrReplaceChild("left_r1", CubeListBuilder.create().texOffs(40, 22).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.0F, -6.0F, 0.0F, 0.6109F, 0.0F));

        PartDefinition spikes = body.addOrReplaceChild("spikes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r7 = spikes.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 44).addBox(-1.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -8.0F, 2.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r8 = spikes.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(42, 36).addBox(-1.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -8.0F, -1.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r9 = spikes.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(42, 33).addBox(-1.5F, -2.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -8.0F, -4.0F, -0.6109F, 0.0F, 0.0F));

        PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -10.0F, -2.0F, 0.0F, -1.5708F, -0.7854F));

        PartDefinition wing_left = wings.addOrReplaceChild("wing_left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition bone = wing_left.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(12, 38).addBox(-2.0F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 20).addBox(-1.0F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition cube_r10 = bone.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(42, 4).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 3.8F, 1.5708F, -0.3054F, 0.0F));

        PartDefinition cube_r11 = bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(42, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 1.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r12 = bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 41).addBox(-1.0F, 0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.5F, 0.0F, 0.0F, -0.8727F));

        PartDefinition cube_r13 = bone.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(8, 40).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.5F, 0.0F, 0.0F, -0.3491F));

        PartDefinition skin = wing_left.addOrReplaceChild("skin", CubeListBuilder.create().texOffs(20, 38).addBox(0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 44).addBox(0.5953F, -1.7813F, -0.5F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(42, 16).addBox(1.535F, -0.1233F, -0.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(42, 17).addBox(2.535F, -1.0F, -0.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(32, 22).addBox(2.6025F, -1.4131F, -0.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(22, 37).addBox(1.0F, -5.0F, -0.5F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 1.0F));

        PartDefinition wings2 = body.addOrReplaceChild("wings2", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, -10.0F, -2.0F, 0.0F, 1.5708F, 0.7854F));

        PartDefinition wing_right2 = wings2.addOrReplaceChild("wing_right2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone3 = wing_right2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(16, 38).addBox(1.0F, -6.0F, -2.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 18).addBox(-5.0F, -6.0F, -2.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.0F, 1.0F));

        PartDefinition cube_r14 = bone3.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(42, 12).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.0F, 1.8F, 1.5708F, 0.3054F, 0.0F));

        PartDefinition cube_r15 = bone3.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(42, 8).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.0F, -1.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r16 = bone3.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(28, 41).addBox(0.0F, 0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -6.0F, -1.5F, 0.0F, 0.0F, 0.8727F));

        PartDefinition cube_r17 = bone3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(24, 41).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -6.0F, -1.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition skin3 = wing_right2.addOrReplaceChild("skin3", CubeListBuilder.create().texOffs(38, 22).addBox(-1.5F, 0.0F, -0.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(32, 44).addBox(-2.5953F, -1.7813F, -0.5F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(42, 39).addBox(-3.535F, -0.1233F, -0.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(42, 40).addBox(-4.535F, -1.0F, -0.5F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(32, 23).addBox(-5.6026F, -1.4131F, -0.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(32, 37).addBox(-6.0F, -5.0F, -0.5F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(HobgobblerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(HobgobblerAnimations.ANIM_HOBGOBBLER_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, HobgobblerAnimations.ANIM_HOBGOBBLER_IDLE, ageInTicks, 1f);
    }


    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -15f, 15f);
        headPitch = Mth.clamp(headPitch, -5f, 25f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int colour) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}
