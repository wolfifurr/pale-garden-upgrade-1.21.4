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
import wolfifurr.palegardenupgrade.animations.PaleSpiritAnimations;
import wolfifurr.palegardenupgrade.renderstates.PaleSpiritRenderState;

public class PaleSpirit extends EntityModel<PaleSpiritRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of (PaleGardenUpgrade.MOD_ID, "palespirit"), "main");
	private final ModelPart Root;

	public PaleSpirit(ModelPart root) {
		super (root);
		this.Root = root.getChild(EntityModelPartNames.ROOT);
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData Root = partdefinition.addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 8.0F, 6.0F)
		.uv(24, 0).cuboid(-3.0F, 2.0F, -3.0F, 6.0F, 3.0F, 6.0F), ModelTransform.pivot(0.0F, 18.0F, 0.0F));

		ModelPartData RightArm = Root.addChild("RightArm", ModelPartBuilder.create().uv(20, 21).cuboid(-2.0F, -1.0F, -7.0F, 2.0F, 2.0F, 8.0F)
		.uv(0, 14).cuboid(-2.0F, 1.0F, -7.0F, 2.0F, 2.0F, 8.0F), ModelTransform.pivot(-3.0F, -2.0F, 0.0F));

		ModelPartData RightForeArm = RightArm.addChild("RightForeArm", ModelPartBuilder.create().uv(20, 11).cuboid(-1.0F, 1.0F, -8.0F, 2.0F, 2.0F, 8.0F)
		.uv(0, 24).cuboid(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F), ModelTransform.pivot(-1.0F, 0.0F, -7.0F));

		ModelPartData LeftArm = Root.addChild("LeftArm", ModelPartBuilder.create().uv(40, 1).cuboid(0.0F, 1.0F, -7.0F, 2.0F, 2.0F, 8.0F)
		.uv(40, 21).cuboid(0.0F, -1.0F, -7.0F, 2.0F, 2.0F, 8.0F), ModelTransform.pivot(3.0F, -2.0F, 0.0F));

		ModelPartData LeftForeArm = LeftArm.addChild("LeftForeArm", ModelPartBuilder.create().uv(40, 31).cuboid(-1.0F, 1.0F, -8.0F, 2.0F, 2.0F, 8.0F)
		.uv(40, 11).cuboid(-1.0F, -1.0F, -8.0F, 2.0F, 2.0F, 8.0F), ModelTransform.pivot(1.0F, 0.0F, -7.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(PaleSpiritRenderState state) {
		this.getParts ().forEach (ModelPart::resetTransform);

		this.animate (state.IDLE, PaleSpiritAnimations.IDLE,state.age,1f);
		this.animate (state.ATTACK, PaleSpiritAnimations.ATTACK,state.age,1f);
	}

	@Override
	protected void animateWalking(Animation animation, float limbFrequency, float limbAmplitudeModifier, float f, float g) {
		super.animateWalking (animation, limbFrequency, limbAmplitudeModifier, f, g);
	}
}