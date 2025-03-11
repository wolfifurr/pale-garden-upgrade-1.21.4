package wolfifurr.palegardenupgrade.models;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.animations.HiddelWoodlingAnimations;
import wolfifurr.palegardenupgrade.renderstates.HiddenWoodlingRenderState;

public class HiddenWoodling extends EntityModel<HiddenWoodlingRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of (PaleGardenUpgrade.MOD_ID, "hiddenwoodling"), "main");
	private final ModelPart root;

	public HiddenWoodling(ModelPart root) {
        super (root);
        this.root = root.getChild(EntityModelPartNames.ROOT);
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData root = partdefinition.addChild (EntityModelPartNames.ROOT, ModelPartBuilder.create (), ModelTransform.pivot (0.0F, 24.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 45).cuboid(-4.0F, -27.0F, -4.0F, 8.0F, 27.0F, 8.0F)
		.uv(20, 37).mirrored().cuboid(-8.0F, -2.0F, -3.0F, 4.0F, 2.0F, 6.0F).mirrored(false)
		.uv(0, 8).mirrored().cuboid(-13.0F, -1.0F, -2.0F, 5.0F, 1.0F, 3.0F).mirrored(false)
		.uv(20, 5).cuboid(-4.0F, -3.0F, 4.0F, 4.0F, 3.0F, 4.0F)
		.uv(21, 22).cuboid(-3.0F, -2.0F, 8.0F, 4.0F, 2.0F, 5.0F)
		.uv(0, 12).cuboid(1.0F, -1.0F, 10.0F, 4.0F, 1.0F, 2.0F)
		.uv(39, 20).cuboid(13.0F, -1.0F, 7.0F, 2.0F, 1.0F, 4.0F)
		.uv(20, 0).cuboid(11.0F, -2.0F, 4.0F, 5.0F, 2.0F, 3.0F)
		.uv(45, 8).cuboid(2.0F, -4.0F, 0.0F, 6.0F, 4.0F, 6.0F)
		.uv(0, 21).cuboid(-3.0F, -3.0F, -8.0F, 4.0F, 3.0F, 4.0F)
		.uv(20, 29).cuboid(-2.0F, -2.0F, -14.0F, 3.0F, 2.0F, 6.0F)
		.uv(16, 29).cuboid(-1.0F, -1.0F, -17.0F, 2.0F, 1.0F, 3.0F)
		.uv(0, 28).cuboid(3.0F, -3.0F, -6.0F, 4.0F, 3.0F, 4.0F)
		.uv(19, 12).cuboid(5.0F, -2.0F, -9.0F, 6.0F, 2.0F, 4.0F)
		.uv(56, 0).cuboid(11.0F, -1.0F, -8.0F, 3.0F, 1.0F, 6.0F)
		.uv(0, 15).cuboid(8.0F, -3.0F, 2.0F, 4.0F, 3.0F, 3.0F)
		.uv(35, 41).cuboid(-4.0F, -31.0F, -4.0F, 8.0F, 4.0F, 8.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData arm1 = root.addChild("arm1", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -14.0F, -4.0F));

		ModelPartData cube_r1 = arm1.addChild("cube_r1", ModelPartBuilder.create().uv(76, 6).cuboid(-7.0F, -16.0F, -14.0F, 10.0F, 4.0F, 4.0F), ModelTransform.of(12.0F, 14.0F, -7.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData fore_arm1 = arm1.addChild("fore_arm1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -10.0F));

		ModelPartData cube_r2 = fore_arm1.addChild("cube_r2", ModelPartBuilder.create().uv(91, 21).cuboid(-11.0F, -15.0F, -17.0F, 13.0F, 2.0F, 2.0F), ModelTransform.of(16.0F, 14.0F, -11.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData last_arm1 = fore_arm1.addChild("last_arm1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -13.0F));

		ModelPartData cube_r3 = last_arm1.addChild("cube_r3", ModelPartBuilder.create().uv(70, 31).cuboid(-16.5F, -14.5F, -22.0F, 18.0F, 1.0F, 1.0F), ModelTransform.of(21.5F, 14.0F, -16.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData arm2 = root.addChild("arm2", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F), ModelTransform.pivot(4.0F, -18.0F, 1.0F));

		ModelPartData fore_arm2 = arm2.addChild("fore_arm2", ModelPartBuilder.create().uv(19, 18).cuboid(0.0F, -1.0F, -1.0F, 9.0F, 2.0F, 2.0F), ModelTransform.pivot(6.0F, 0.0F, 0.0F));

		ModelPartData last_arm2 = fore_arm2.addChild("last_arm2", ModelPartBuilder.create().uv(36, 0).cuboid(0.0F, -0.5F, -0.5F, 12.0F, 1.0F, 1.0F), ModelTransform.pivot(9.0F, 0.0F, 0.0F));

		ModelPartData arm3 = root.addChild("arm3", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -14.0F, 3.0F));

		ModelPartData cube_r4 = arm3.addChild("cube_r4", ModelPartBuilder.create().uv(42, 18).cuboid(-1.0F, -16.0F, -14.0F, 4.0F, 4.0F, 9.0F), ModelTransform.of(-10.0F, 14.0F, 10.0F, 0.0F, -0.6981F, 0.0F));

		ModelPartData fore_arm3 = arm3.addChild("fore_arm3", ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, 0.0F, 7.0F));

		ModelPartData cube_r5 = fore_arm3.addChild("cube_r5", ModelPartBuilder.create().uv(59, 21).mirrored().cuboid(-10.1358F, -1.0F, -0.1307F, 11.0F, 2.0F, 2.0F).mirrored(false), ModelTransform.of(-1.2F, 0.0F, -0.1F, 0.0F, 0.8727F, 0.0F));

		ModelPartData last_arm3 = fore_arm3.addChild("last_arm3", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, 0.0F, 8.0F));

		ModelPartData cube_r6 = last_arm3.addChild("cube_r6", ModelPartBuilder.create().uv(75, 45).cuboid(-12.4158F, -14.5F, 0.2414F, 13.0F, 1.0F, 1.0F), ModelTransform.of(-1.0F, 14.0F, 0.2F, 0.0F, 0.8727F, 0.0F));

		return TexturedModelData.of (meshdefinition, 128, 128);
	}

	@Override
	public void setAngles(HiddenWoodlingRenderState state) {
		this.getParts ().forEach (ModelPart::resetTransform);

		this.animate (state.IDLE, HiddelWoodlingAnimations.IDLE,state.age,1f);
		this.animate (state.ATTACK, HiddelWoodlingAnimations.ATTACK,state.age,1f);
	}

	@Override
	protected void animateWalking(Animation animation, float limbFrequency, float limbAmplitudeModifier, float f, float g) {
		super.animateWalking (animation, limbFrequency, limbAmplitudeModifier, f, g);
	}

}