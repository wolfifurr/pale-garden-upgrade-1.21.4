package wolfifurr.palegardenupgrade.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.entity.HauntedFlowerEntity;
import wolfifurr.palegardenupgrade.models.HauntedFlower;
import wolfifurr.palegardenupgrade.renderstates.HauntedFlowerRenderState;

public class HauntedFlowerRenderer extends MobEntityRenderer<HauntedFlowerEntity, HauntedFlowerRenderState,HauntedFlower> {
    public HauntedFlowerRenderer(EntityRendererFactory.Context context) {
        super (context, new HauntedFlower (context.getPart (HauntedFlower.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public Identifier getTexture(HauntedFlowerRenderState state) {
        return Identifier.of (PaleGardenUpgrade.MOD_ID,"textures/entity/hauntedflower.png");
    }

    @Override
    public HauntedFlowerRenderState createRenderState() {
        return new HauntedFlowerRenderState ();
    }

    @Override
    public void updateRenderState(HauntedFlowerEntity livingEntity, HauntedFlowerRenderState livingEntityRenderState, float f) {
        super.updateRenderState (livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.IDLE.copyFrom (livingEntity.idleAnimState);
        livingEntityRenderState.ATTACK.copyFrom (livingEntity.attackAnimState);
    }
}
