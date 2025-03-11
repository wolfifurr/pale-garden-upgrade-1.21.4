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
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.animations.HauntedFlowerAnimations;
import wolfifurr.palegardenupgrade.entity.HauntedFlowerEntity;
import wolfifurr.palegardenupgrade.renderstates.HauntedFlowerRenderState;

import javax.swing.text.html.parser.Entity;

public class HauntedFlower extends EntityModel<HauntedFlowerRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of (PaleGardenUpgrade.MOD_ID, "hauntedflower"), "main");
	private final ModelPart Root;
	public HauntedFlower(ModelPart root) {
        super (root);
        this.Root = root.getChild(EntityModelPartNames.ROOT);
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData Root = partdefinition.addChild(EntityModelPartNames.ROOT, ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Pipe = Root.addChild("Pipe", ModelPartBuilder.create().uv(18, 6).cuboid(-0.5F, -8.0F, -0.5F, 1.0F, 10.0F, 1.0F)
		.uv(22, 9).mirrored ().cuboid(-1.0F, -9.0F, -1.0F, 2.0F, 1.0F, 2.0F).mirrored (false)
		.uv(21, 36).cuboid(-1.5F, -10.0F, -1.6F, 3.0F, 1.0F, 3.0F)
		.uv(22, 6).mirrored().cuboid(-1.0F, -11.0F, -1.0F, 2.0F, 1.0F, 2.0F).mirrored(false), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		ModelPartData Leaf1 = Root.addChild("Leaf1", ModelPartBuilder.create().uv(29, 0).cuboid(-2.2F, 0.0F, -3.0F, 4.0F, 0.0F, 3.0F), ModelTransform.of (0.0F, -7.0F, -0.5F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r1 = Leaf1.addChild("cube_r1", ModelPartBuilder.create().uv(21, 3).cuboid(-1.6F, 0.0F, -2.6F, 3.0F, 0.0F, 3.0F), ModelTransform.of (0.0F, 0.2F, -3.3F, 0.3927F, 0.0F, 0.0F));

		ModelPartData Leaf2 = Root.addChild("Leaf2", ModelPartBuilder.create().uv(29, 3).cuboid(-2.2F, 0.0F, -3.0F, 4.0F, 0.0F, 3.0F), ModelTransform.of (-0.2F, -5.2F, 0.0F, -3.0462F, 0.5118F, 3.1287F));

		ModelPartData cube_r2 = Leaf2.addChild("cube_r2", ModelPartBuilder.create().uv(15, 0).cuboid(-1.6F, 0.0F, -2.6F, 3.0F, 0.0F, 3.0F), ModelTransform.of (0.0F, 0.1F, -3.3F, 0.3927F, 0.0F, 0.0F));

		ModelPartData Leaf3 = Root.addChild("Leaf3", ModelPartBuilder.create().uv(21, 0).cuboid(-2.2F, 0.0F, -3.0F, 4.0F, 0.0F, 3.0F), ModelTransform.of (0.2F, -8.2F, 0.3F, -2.9083F, -1.2386F, 3.0737F));

		ModelPartData cube_r3 = Leaf3.addChild("cube_r3", ModelPartBuilder.create().uv(15, 3).cuboid(-1.6F, 0.0F, -2.6F, 3.0F, 0.0F, 3.0F), ModelTransform.of (0.0F, 0.1F, -3.3F, 0.3927F, 0.0F, 0.0F));

		ModelPartData UpperHalf = Root.addChild("UpperHalf", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		ModelPartData Leafs1 = UpperHalf.addChild("Leafs1", ModelPartBuilder.create(), ModelTransform.pivot(1.2F, 0.0F, 1.2F));

		ModelPartData cube_r4 = Leafs1.addChild("cube_r4", ModelPartBuilder.create().uv(3, 10).cuboid(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 5.0F), ModelTransform.of (0.0F, 0.0F, 0.0F, 0.48F, 0.7854F, 0.0F));

		ModelPartData cube_r5 = Leafs1.addChild("cube_r5", ModelPartBuilder.create().uv(3, 5).cuboid(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 5.0F), ModelTransform.of (-2.4F, 0.0F, 0.0F, 0.48F, -0.7854F, 0.0F));

		ModelPartData cube_r6 = Leafs1.addChild("cube_r6", ModelPartBuilder.create().uv(3, 20).cuboid(-2.5F, 0.0F, -4.6F, 5.0F, 0.0F, 5.0F), ModelTransform.of (-2.4F, 0.0F, -2.4F, -0.48F, 0.7854F, 0.0F));

		ModelPartData cube_r7 = Leafs1.addChild("cube_r7", ModelPartBuilder.create().uv(3, 0).cuboid(-2.5F, 0.0F, -4.6F, 5.0F, 0.0F, 5.0F), ModelTransform.of (0.0F, 0.0F, -2.4F, -0.48F, -0.7854F, 0.0F));

		ModelPartData cube_r8 = Leafs1.addChild("cube_r8", ModelPartBuilder.create().uv(3, 15).cuboid(-2.5F, 0.0F, -4.6F, 5.0F, 0.0F, 5.0F), ModelTransform.of (-1.2F, 0.0F, -2.7F, -0.48F, 0.0F, 0.0F));

		ModelPartData cube_r9 = Leafs1.addChild("cube_r9", ModelPartBuilder.create().uv(3, 25).cuboid(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 5.0F), ModelTransform.of (-1.2F, 0.0F, 0.3F, 0.48F, 0.0F, 0.0F));

		ModelPartData cube_r10 = Leafs1.addChild("cube_r10", ModelPartBuilder.create().uv(3, 30).cuboid(-5.0F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F), ModelTransform.of (-2.7F, 0.0F, -1.2F, 0.0F, 0.0F, 0.48F));

		ModelPartData cube_r11 = Leafs1.addChild("cube_r11", ModelPartBuilder.create().uv(3, 35).cuboid(-0.4F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F), ModelTransform.of (0.3F, 0.0F, -1.2F, 0.0F, 0.0F, -0.48F));

		ModelPartData Leafs2 = UpperHalf.addChild("Leafs2", ModelPartBuilder.create(), ModelTransform.pivot (1.2F, 0.0F, -1.2F));

		ModelPartData cube_r12 = Leafs2.addChild("cube_r12", ModelPartBuilder.create().uv(-4, 20).cuboid(-2.2F, 0.0F, -4.1F, 4.0F, 0.0F, 4.0F), ModelTransform.of (0.0F, 0.0F, 0.0F, -0.7418F, -0.7854F, 0.0F));

		ModelPartData cube_r13 = Leafs2.addChild("cube_r13", ModelPartBuilder.create().uv(-4, 24).cuboid(-2.2F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F), ModelTransform.of (0.0F, 0.0F, 2.4F, 0.7418F, 0.7854F, 0.0F));

		ModelPartData cube_r14 = Leafs2.addChild ("cube_r14", ModelPartBuilder.create().uv(-4, 8).cuboid(-2.2F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F), ModelTransform.of (-2.4F, 0.0F, 2.4F, 0.7418F, -0.7854F, 0.0F));

		ModelPartData cube_r15 = Leafs2.addChild("cube_r15", ModelPartBuilder.create().uv(-4, 12).cuboid(-2.2F, 0.0F, -4.1F, 4.0F, 0.0F, 4.0F), ModelTransform.of (-2.4F, 0.0F, 0.0F, -0.7418F, 0.7854F, 0.0F));

		ModelPartData Leafs3 = UpperHalf.addChild("Leafs3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.1F));

		ModelPartData cube_r16 = Leafs3.addChild("cube_r16", ModelPartBuilder.create().uv(-4, 16).cuboid(-2.0F, 0.0F, -4.4F, 4.0F, 0.0F, 4.0F), ModelTransform.of (0.0F, 0.0F, 0.0F, -0.9599F, 0.0F, 0.0F));

		ModelPartData cube_r17 = Leafs3.addChild("cube_r17", ModelPartBuilder.create().uv(-4, 4).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F), ModelTransform.of (0.0F, 0.0F, 2.2F, 0.9599F, 0.0F, 0.0F));

		ModelPartData cube_r18 = Leafs3.addChild("cube_r18", ModelPartBuilder.create().uv(-4, 28).cuboid(-4.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F), ModelTransform.of (-1.1F, 0.0F, 1.1F, 0.0F, 0.0F, 0.9599F));

		ModelPartData cube_r19 = Leafs3.addChild("cube_r19", ModelPartBuilder.create().uv(-4, 0).cuboid(0.4F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F), ModelTransform.of (1.1F, 0.0F, 1.1F, 0.0F, 0.0F, -0.9599F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}



	@Override
	public void setAngles(HauntedFlowerRenderState state) {
		this.getParts ().forEach (ModelPart::resetTransform);

		this.animate (state.IDLE,HauntedFlowerAnimations.IDLE,state.age,1f);
		this.animate (state.ATTACK,HauntedFlowerAnimations.ATTACK,state.age,1f);
	}

	@Override
	protected void animateWalking(Animation animation, float limbFrequency, float limbAmplitudeModifier, float f, float g) {
		super.animateWalking (animation, limbFrequency, limbAmplitudeModifier, f, g);
	}
}