package wolfifurr.palegardenupgrade.models;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.state.CamelEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.animations.HiddelWoodlingAnimations;
import wolfifurr.palegardenupgrade.animations.MossyLizardAnimations;
import wolfifurr.palegardenupgrade.renderstates.HiddenWoodlingRenderState;
import wolfifurr.palegardenupgrade.renderstates.MossyLizardRenderState;

public class MossyLizard extends EntityModel<MossyLizardRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of (PaleGardenUpgrade.MOD_ID, "mossylizard"), "main");
	private final ModelPart Root;
	private final ModelPart head;

	public MossyLizard(ModelPart root) {
		super (root);
		this.Root = root.getChild(EntityModelPartNames.ROOT);
		this.head=this.Root.getChild ("Head");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData Root = partdefinition.addChild (EntityModelPartNames.ROOT, ModelPartBuilder.create().uv(6, 0).cuboid(-4.0F, -2.0F, -4.0F, 4.0F, 2.0F, 8.0F), ModelTransform.pivot(2.0F, 23.0F, 0.0F));

		ModelPartData BLLeg = Root.addChild("BLLeg", ModelPartBuilder.create(), ModelTransform.pivot (0.0F, -1.0F, 2.5F));

		ModelPartData cube_r1 = BLLeg.addChild("cube_r1", ModelPartBuilder.create().uv(28, 4).cuboid(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F), ModelTransform.of(1.5F, 2.0F, 0.0F, 0.0F, 0.0F, -0.48F));

		ModelPartData BLForeLeg = BLLeg.addChild("BLForeLeg", ModelPartBuilder.create().uv(22, 3).mirrored().cuboid(-0.6F, -0.6F, -1.8F, 1.0F, 1.0F, 2.0F).mirrored(false), ModelTransform.pivot(1.5F, 1.5F, 0.1F));

		ModelPartData BLFoot = BLForeLeg.addChild("BLFoot", ModelPartBuilder.create().uv(-2, 10).cuboid(-1.6F, 0.001F, -2.2F, 3.0F, 0.0F, 2.0F), ModelTransform.pivot(0.0F, 0.4F, -0.6F));

		ModelPartData FLLeg = Root.addChild("FLLeg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, -2.5F));

		ModelPartData cube_r2 = FLLeg.addChild("cube_r2", ModelPartBuilder.create().uv(24, 25).cuboid(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F), ModelTransform.of(1.5F, 2.0F, 0.0F, 0.0F, 0.0F, -0.48F));

		ModelPartData FLForeLeg = FLLeg.addChild("FLForeLeg", ModelPartBuilder.create().uv(24, 13).cuboid(-0.6F, -0.6F, -1.7F, 1.0F, 1.0F, 2.0F), ModelTransform.pivot(1.5F, 1.5F, 0.0F));

		ModelPartData FLFoot = FLForeLeg.addChild("FLFoot", ModelPartBuilder.create().uv(15, 27).cuboid(-1.6F, 0.001F, -2.2F, 3.0F, 0.0F, 2.0F), ModelTransform.pivot(0.0F, 0.4F, -0.5F));

		ModelPartData Tail = Root.addChild("Tail", ModelPartBuilder.create().uv(0, 22).cuboid(-1.7F, -1.1F, 0.0F, 3.0F, 2.0F, 5.0F), ModelTransform.pivot(-2.0F, -0.9F, 4.0F));

		ModelPartData ForeTail = Tail.addChild("ForeTail", ModelPartBuilder.create().uv(16, 17).cuboid(-1.2F, -0.5F, 0.0F, 2.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F, -0.1F, 5.0F));

		ModelPartData LaftTail = ForeTail.addChild("LaftTail", ModelPartBuilder.create().uv(16, 10).cuboid(-0.4F, -0.7F, 0.0F, 1.0F, 1.0F, 6.0F), ModelTransform.pivot(-0.3F, 0.2F, 5.0F));

		ModelPartData Head = Root.addChild("Head", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.0F, -2.0F, -3.0F, 4.0F, 3.0F, 3.0F).mirrored(false), ModelTransform.pivot(-2.0F, -1.0F, -4.0F));

		ModelPartData LowerJaw = Head.addChild("LowerJaw", ModelPartBuilder.create().uv(3, 11).cuboid(-1.5F, 0.0F, -3.0F, 3.0F, 1.0F, 3.0F), ModelTransform.pivot(0.0F, 0.0F, -3.0F));

		ModelPartData UpperJaw = Head.addChild("UpperJaw", ModelPartBuilder.create().uv(3, 16).cuboid(-1.5F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F), ModelTransform.pivot(0.0F, 0.0F, -3.0F));

		ModelPartData BRLeg = Root.addChild("BRLeg", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -1.0F, 2.5F));

		ModelPartData cube_r3 = BRLeg.addChild("cube_r3", ModelPartBuilder.create().uv(28, 0).cuboid(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F), ModelTransform.of(-1.5F, 2.0F, 0.0F, 0.0F, 0.0F, 0.48F));

		ModelPartData BRForeLeg = BRLeg.addChild("BRForeLeg", ModelPartBuilder.create().uv(22, 0).cuboid(-0.6F, -0.6F, -1.6F, 1.0F, 1.0F, 2.0F), ModelTransform.pivot(-1.5F, 1.5F, -0.1F));

		ModelPartData BRFoot = BRForeLeg.addChild("BRFoot", ModelPartBuilder.create().uv(-2, 7).cuboid(-1.6F, 0.001F, -2.2F, 3.0F, 0.0F, 2.0F), ModelTransform.pivot(0.0F, 0.4F, -0.4F));

		ModelPartData FRLeg = Root.addChild("FRLeg", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -1.0F, -2.5F));

		ModelPartData cube_r4 = FRLeg.addChild("cube_r4", ModelPartBuilder.create().uv(28, 25).mirrored().cuboid(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F).mirrored(false), ModelTransform.of(-1.5F, 2.0F, 0.0F, 0.0F, 0.0F, 0.48F));

		ModelPartData FRForeLeg = FRLeg.addChild("FRForeLeg", ModelPartBuilder.create().uv(24, 10).cuboid(-0.6F, -0.6F, -1.6F, 1.0F, 1.0F, 2.0F), ModelTransform.pivot(-1.5F, 1.5F, -0.1F));

		ModelPartData FRFoot = FRForeLeg.addChild("FRFoot", ModelPartBuilder.create().uv(15, 24).cuboid(-1.6F, 0.001F, -2.2F, 3.0F, 0.0F, 2.0F), ModelTransform.pivot(0.0F, 0.4F, -0.4F));

		return TexturedModelData.of (meshdefinition, 32, 32);
	}

	@Override
	public void setAngles(MossyLizardRenderState state) {
		this.getParts ().forEach (ModelPart::resetTransform);
		this.setHeadAngles (state,state.yawDegrees,state.pitch);

		this.animateWalking (MossyLizardAnimations.WALK,state.limbFrequency,state.limbAmplitudeMultiplier,2f,2.5f);
		this.animate (state.IDLE, MossyLizardAnimations.IDLE,state.age,1f);
		this.animate (state.ATTACK, MossyLizardAnimations.ATTACK,state.age,1f);
	}

	private void setHeadAngles(MossyLizardRenderState state, float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * (float) (Math.PI / 180.0);
		this.head.pitch = headPitch * (float) (Math.PI / 180.0);
	}

	@Override
	protected void animateWalking(Animation animation, float limbFrequency, float limbAmplitudeModifier, float f, float g) {
		super.animateWalking (animation, limbFrequency, limbAmplitudeModifier, f, g);
	}

}