package wolfifurr.palegardenupgrade.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.entity.HauntedFlowerEntity;
import wolfifurr.palegardenupgrade.entity.HiddenWoodlingEntity;
import wolfifurr.palegardenupgrade.models.HiddenWoodling;
import wolfifurr.palegardenupgrade.renderstates.HauntedFlowerRenderState;
import wolfifurr.palegardenupgrade.renderstates.HiddenWoodlingRenderState;

public class HiddenWoodlingRenderer extends MobEntityRenderer<HiddenWoodlingEntity, HiddenWoodlingRenderState, HiddenWoodling> {
    public HiddenWoodlingRenderer(EntityRendererFactory.Context context) {
        super (context, new HiddenWoodling (context.getPart (HiddenWoodling.LAYER_LOCATION)), 1);
    }

    @Override
    public Identifier getTexture(HiddenWoodlingRenderState state) {
        return Identifier.of (PaleGardenUpgrade.MOD_ID,"textures/entity/hiddenwoodling.png");
    }

    @Override
    public HiddenWoodlingRenderState createRenderState() {
        return new HiddenWoodlingRenderState ();
    }

    @Override
    public void updateRenderState(HiddenWoodlingEntity livingEntity, HiddenWoodlingRenderState livingEntityRenderState, float f) {
        super.updateRenderState (livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.IDLE.copyFrom (livingEntity.idleAnimState);
        livingEntityRenderState.ATTACK.copyFrom (livingEntity.attackAnimState);
    }
}
