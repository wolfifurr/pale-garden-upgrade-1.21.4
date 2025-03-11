package wolfifurr.palegardenupgrade.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.entity.MossyLizardEntity;
import wolfifurr.palegardenupgrade.models.MossyLizard;
import wolfifurr.palegardenupgrade.renderstates.MossyLizardRenderState;

public class MossyLizardRenderer extends MobEntityRenderer<MossyLizardEntity, MossyLizardRenderState, MossyLizard> {
    public MossyLizardRenderer(EntityRendererFactory.Context context) {
        super (context, new MossyLizard (context.getPart (MossyLizard.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public Identifier getTexture(MossyLizardRenderState state) {
        return Identifier.of (PaleGardenUpgrade.MOD_ID,"textures/entity/mossylizard.png");
    }

    @Override
    public void render(MossyLizardRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntityRenderState.baby) {
            matrixStack.scale (0.5f,0.5f,0.5f);
        }else {
            matrixStack.scale (1f,1f,1f);
        }
        super.render (livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public MossyLizardRenderState createRenderState() {
        return new MossyLizardRenderState ();
    }

    @Override
    public void updateRenderState(MossyLizardEntity livingEntity, MossyLizardRenderState livingEntityRenderState, float f) {
        super.updateRenderState (livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.IDLE.copyFrom (livingEntity.idleAnimState);
        livingEntityRenderState.ATTACK.copyFrom (livingEntity.attackAnimState);
        livingEntityRenderState.WALK.copyFrom (livingEntity.walkAnimState);
    }
}
