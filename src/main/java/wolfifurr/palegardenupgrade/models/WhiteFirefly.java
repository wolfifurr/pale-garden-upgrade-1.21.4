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
import wolfifurr.palegardenupgrade.animations.WhiteFireflyAnimations;
import wolfifurr.palegardenupgrade.renderstates.WhiteFireflyRenderState;

public class WhiteFirefly extends EntityModel<WhiteFireflyRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of (PaleGardenUpgrade.MOD_ID, "whitefirefly"), "main");
	private final ModelPart Root;

	public WhiteFirefly(ModelPart root) {
        super (root);
        this.Root = root.getChild(EntityModelPartNames.ROOT);
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData Root = partdefinition.addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create().uv(12, 0).cuboid(-2.0F, -3.0F, -2.0F, 3.0F, 3.0F, 4.0F, new Dilation(-0.5F)), ModelTransform.pivot(0.0F, 24.5F, 1.0F));

		ModelPartData Head = Root.addChild("Head", ModelPartBuilder.create().uv(10, 7).cuboid(-0.4F, -4.0F, -2.5F, 1.0F, 2.0F, 2.0F, new Dilation(-0.5F))
		.uv(0, 7).cuboid(-1.6F, -4.0F, -2.5F, 1.0F, 2.0F, 2.0F, new Dilation (-0.5F))
		.uv(0, 1).cuboid(-2.0F, -3.0F, -2.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.5F)), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

		ModelPartData LeftWing = Root.addChild("LeftWing", ModelPartBuilder.create().uv(14, 7).cuboid(-0.5F, -0.5F, -0.4F, 2.0F, 3.0F, 4.0F, new Dilation(-0.4F)), ModelTransform.pivot(-0.4F, -2.5F, -1.6F));

		ModelPartData RightWing = Root.addChild("RightWing", ModelPartBuilder.create().uv(2, 7).cuboid(-1.5F, -0.5F, -0.5F, 2.0F, 3.0F, 4.0F, new Dilation(-0.4F)), ModelTransform.pivot(-0.6F, -2.5F, -1.5F));

		ModelPartData Light = Root.addChild("Light", ModelPartBuilder.create().uv(0, 14).cuboid(-2.0F, -3.0F, -2.0F, 3.0F, 3.0F, 4.0F, new Dilation(-0.49F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 32, 32);
	}

	@Override
	public void setAngles(WhiteFireflyRenderState state) {
		this.getParts ().forEach (ModelPart::resetTransform);

		this.animate (state.IDLE, WhiteFireflyAnimations.IDLE,state.age,1f);
		this.animate (state.FLYING, WhiteFireflyAnimations.FLYING,state.age,1f);
	}

	@Override
	protected void animateWalking(Animation animation, float limbFrequency, float limbAmplitudeModifier, float f, float g) {
		super.animateWalking (animation, limbFrequency, limbAmplitudeModifier, f, g);
	}

}