package net.nitu.httydmod.entity.client.nightterror;

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
import net.nitu.httydmod.entity.custom.nightterror.NightTerrorEntity;

public class NightTerrorModel<T extends NightTerrorEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HttydMod.MODID, "night_terror"), "main");

    private final ModelPart body;
    private final ModelPart head;

    public NightTerrorModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-5.0F, -8.0F, 0.0F));

        PartDefinition top_head = head.addOrReplaceChild("top_head", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0221F, -4.2342F, -1.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5221F, 0.0342F, 0.0F));

        PartDefinition front = top_head.addOrReplaceChild("front", CubeListBuilder.create().texOffs(40, 15).addBox(-0.5221F, 3.9658F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -4.0F, 0.0F));

        PartDefinition cube_r1 = front.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 13).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5221F, 3.9658F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r2 = front.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(36, 10).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 19).addBox(0.5F, -4.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.2618F));

        PartDefinition cube_r3 = front.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 27).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 3).addBox(0.5F, -4.0F, -1.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.2618F));

        PartDefinition head_sides = top_head.addOrReplaceChild("head_sides", CubeListBuilder.create(), PartPose.offset(-5.5221F, -0.0342F, 1.0F));

        PartDefinition l_side_blue_r1 = head_sides.addOrReplaceChild("l_side_blue_r1", CubeListBuilder.create().texOffs(12, 22).addBox(0.0F, -4.0F, -1.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

        PartDefinition r_side_blue_r1 = head_sides.addOrReplaceChild("r_side_blue_r1", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition spike_top = head_sides.addOrReplaceChild("spike_top", CubeListBuilder.create(), PartPose.offset(4.5F, -3.5F, -1.0F));

        PartDefinition cube_r4 = spike_top.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(26, 8).addBox(-0.438F, -0.6881F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5294F, -1.6187F, 1.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r5 = spike_top.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 21).addBox(-0.47F, -0.9F, -0.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6731F, -1.1997F, 0.5F, 0.0F, 0.0F, 1.0472F));

        PartDefinition cube_r6 = spike_top.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 35).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition spike_btm = head_sides.addOrReplaceChild("spike_btm", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5F, -1.5F, -1.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r7 = spike_btm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(22, 30).addBox(-0.438F, -0.6881F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5294F, -1.6187F, 1.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r8 = spike_btm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(20, 30).addBox(-0.47F, -0.9F, -0.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6731F, -1.1997F, 0.5F, 0.0F, 0.0F, 1.0472F));

        PartDefinition cube_r9 = spike_btm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(36, 40).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition btm_head = head.addOrReplaceChild("btm_head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition cube_r10 = btm_head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 22).addBox(-0.25F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 2.0F, 1.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r11 = btm_head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(40, 38).addBox(0.2F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 1.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r12 = btm_head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 36).addBox(0.2F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 1.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition btm_r_slv_r1 = btm_head.addOrReplaceChild("btm_r_slv_r1", CubeListBuilder.create().texOffs(26, 0).addBox(0.4588F, -2.2F, -0.2841F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 1.0F, 0.0F, -0.2618F, -0.1309F));

        PartDefinition btm_r_slv_r2 = btm_head.addOrReplaceChild("btm_r_slv_r2", CubeListBuilder.create().texOffs(0, 26).addBox(0.4588F, -2.2F, -0.7159F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 1.0F, 0.0F, 0.2618F, -0.1309F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(-2.0F, -5.0F, 0.0F));

        PartDefinition bone7 = torso.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(-4.0F, -4.0F, 0.0F));

        PartDefinition neck_r1 = bone7.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(14, 15).addBox(-2.0F, 1.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone8 = torso.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.3F, -2.5F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r13 = bone8.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(18, 41).addBox(0.0F, -2.0F, -0.001F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5171F, -1.8681F, 0.001F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r14 = bone8.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(40, 41).addBox(0.0F, -2.0F, -0.001F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4829F, -1.8681F, 0.001F, 0.0F, 0.0F, 0.5236F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-5.0F, -5.0F, -2.0F));

        PartDefinition frontleg1 = legs.addOrReplaceChild("frontleg1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone11 = frontleg1.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r15 = bone11.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(26, 3).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition bone12 = frontleg1.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r16 = bone12.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(26, 15).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.366F, 2.0981F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition backleg1 = legs.addOrReplaceChild("backleg1", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition bone13 = backleg1.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r17 = bone13.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(24, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition bone14 = backleg1.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r18 = bone14.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(12, 27).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.366F, 2.0981F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition frontleg2 = legs.addOrReplaceChild("frontleg2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition bone15 = frontleg2.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r19 = bone15.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 29).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.366F, 2.0981F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition bone16 = frontleg2.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r20 = bone16.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(28, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition backleg2 = legs.addOrReplaceChild("backleg2", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, 4.0F));

        PartDefinition bone17 = backleg2.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r21 = bone17.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(28, 31).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.366F, 2.0981F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition bone18 = backleg2.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r22 = bone18.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(20, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(1.9128F, -4.9962F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition bone9 = tail.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -1.0F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition cube_r23 = bone9.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(36, 24).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4829F, 1.1319F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition bone3 = bone9.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-1.5171F, -2.8681F, 0.0F));

        PartDefinition cube_r24 = bone3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(28, 13).addBox(-0.5F, -0.8F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0171F, 1.8681F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r25 = bone3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(36, 35).addBox(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1239F, 0.0421F, -0.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition bone2 = bone9.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.4829F, -2.8681F, 0.0F));

        PartDefinition cube_r26 = bone2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(26, 20).addBox(-0.5F, -0.8F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0171F, 1.8681F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r27 = bone2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(42, 18).addBox(0.0F, -1.0F, 0.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1239F, 0.0421F, -0.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition bone10 = tail.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(16, 9).addBox(0.0F, -2.5F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition cube_r28 = bone10.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(16, 41).addBox(-1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4829F, 1.1319F, -1.0F, 0.0F, 0.0F, 1.0036F));

        PartDefinition cube_r29 = bone10.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(14, 41).addBox(-1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4829F, -0.8681F, -1.0F, 0.0F, 0.0F, 1.0036F));

        PartDefinition bone = bone10.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-1.5171F, -3.8681F, 0.0F));

        PartDefinition cube_r30 = bone.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(38, 28).addBox(-0.5F, -1.8F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0171F, 1.8681F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r31 = bone.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(32, 20).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition bone4 = bone10.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.2829F, 1.1319F, 0.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition cube_r32 = bone4.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(34, 6).addBox(0.0F, 0.0F, -0.001F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3438F, 1.5057F, 0.001F, 0.0F, 0.0F, -1.309F));

        PartDefinition cube_r33 = bone4.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(4, 41).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7264F, 0.5818F, 0.5F, 0.0F, 0.0F, -1.1781F));

        PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(-4.0F, 3.0F, 0.0F));

        PartDefinition wingR = wings.addOrReplaceChild("wingR", CubeListBuilder.create().texOffs(40, 10).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 36).addBox(0.7F, -4.0F, -0.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -1.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition bone5 = wingR.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r34 = bone5.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(38, 0).addBox(1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(26, 36).addBox(0.0F, -5.0F, -1.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r35 = bone5.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(36, 31).addBox(-4.4641F, -4.0F, -1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(30, 36).addBox(-3.4641F, -4.0F, -1.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 2.4435F));

        PartDefinition cube_r36 = bone5.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(32, 26).addBox(-4.9392F, -6.6946F, -1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 34).addBox(-3.9392F, -6.6946F, -1.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.7453F));

        PartDefinition cube_r37 = bone5.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(24, 40).addBox(-1.4351F, -11.1734F, -1.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(12, 32).addBox(-2.4351F, -11.1734F, -1.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 29).addBox(-2.4351F, -11.1734F, -1.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 1.0036F));

        PartDefinition cube_r38 = bone5.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(30, 20).addBox(-0.4358F, -6.981F, -1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.4363F));

        PartDefinition wingL = wings.addOrReplaceChild("wingL", CubeListBuilder.create().texOffs(20, 40).addBox(0.0F, -3.3572F, 0.234F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 24).addBox(0.7F, -3.3572F, 0.734F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.6428F, 1.234F, -0.6981F, 0.0F, 0.0F));

        PartDefinition bone6 = wingL.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r39 = bone6.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(12, 41).addBox(1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(8, 38).addBox(0.0F, -5.0F, -1.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.234F, 2.1428F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r40 = bone6.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(40, 6).addBox(-4.4641F, -4.0F, -1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(40, 0).addBox(-3.4641F, -4.0F, -1.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.234F, 2.1428F, 0.0F, 0.0F, 2.4435F));

        PartDefinition cube_r41 = bone6.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(36, 19).addBox(-4.9392F, -6.6946F, -1.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(36, 3).addBox(-3.9392F, -6.6946F, -1.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.234F, 2.1428F, 0.0F, 0.0F, 1.7453F));

        PartDefinition cube_r42 = bone6.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(34, 40).addBox(-1.4351F, -11.1734F, -1.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(-2.4351F, -11.1734F, -1.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-2.4351F, -11.1734F, -1.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.234F, 2.1428F, 0.0F, 0.0F, 1.0036F));

        PartDefinition cube_r43 = bone6.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(32, 13).addBox(-0.4358F, -6.981F, -1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.234F, 2.1428F, 0.0F, 0.0F, 0.4363F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(NightTerrorEntity entity, float limbSwing, float limbSwingAmout,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(NightTerrorAnimations.NIGHT_TERROR_WALK, limbSwing, limbSwingAmout, 2f, 2.5f);
        this.animate(entity.idleAnimationState, NightTerrorAnimations.NIGHT_TERROR_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -15f, 15f);
        headPitch = Mth.clamp(headPitch, -5f, 25f);

        this.head.yRot = headYaw * ((float) Math.PI / 180f);
        this.head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay, int colour) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
    }

    @Override
    public ModelPart root() {
        return body;
    }

}
