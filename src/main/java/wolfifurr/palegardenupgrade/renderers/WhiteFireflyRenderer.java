package wolfifurr.palegardenupgrade.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.entity.PaleSpiritEntity;
import wolfifurr.palegardenupgrade.entity.WhiteFireflyEntity;
import wolfifurr.palegardenupgrade.models.WhiteFirefly;
import wolfifurr.palegardenupgrade.renderstates.PaleSpiritRenderState;
import wolfifurr.palegardenupgrade.renderstates.WhiteFireflyRenderState;

public class WhiteFireflyRenderer extends MobEntityRenderer<WhiteFireflyEntity, WhiteFireflyRenderState, WhiteFirefly> {
    public WhiteFireflyRenderer(EntityRendererFactory.Context context) {
        super (context, new WhiteFirefly (context.getPart (WhiteFirefly.LAYER_LOCATION)), 0.1f);
    }

    @Override
    public Identifier getTexture(WhiteFireflyRenderState state) {
        return Identifier.of (PaleGardenUpgrade.MOD_ID,"textures/entity/whitefirefly.png");
    }

    @Override
    public void render(WhiteFireflyRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntityRenderState.baby) {
            matrixStack.scale (0.5f,0.5f,0.5f);
        }else {
            matrixStack.scale (1f,1f,1f);
        }
        super.render (livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public WhiteFireflyRenderState createRenderState() {
        return new WhiteFireflyRenderState ();
    }

    @Override
    public void updateRenderState(WhiteFireflyEntity livingEntity, WhiteFireflyRenderState livingEntityRenderState, float f) {
        super.updateRenderState (livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.IDLE.copyFrom (livingEntity.idleAnimState);
        livingEntityRenderState.FLYING.copyFrom (livingEntity.flyAnimState);
    }
}
