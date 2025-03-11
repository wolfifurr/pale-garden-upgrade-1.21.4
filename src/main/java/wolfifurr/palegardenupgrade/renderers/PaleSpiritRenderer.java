package wolfifurr.palegardenupgrade.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.entity.MossyLizardEntity;
import wolfifurr.palegardenupgrade.entity.PaleSpiritEntity;
import wolfifurr.palegardenupgrade.models.PaleSpirit;
import wolfifurr.palegardenupgrade.renderstates.MossyLizardRenderState;
import wolfifurr.palegardenupgrade.renderstates.PaleSpiritRenderState;

public class PaleSpiritRenderer extends MobEntityRenderer<PaleSpiritEntity, PaleSpiritRenderState, PaleSpirit> {
    public PaleSpiritRenderer(EntityRendererFactory.Context context) {
        super (context, new PaleSpirit (context.getPart (PaleSpirit.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public Identifier getTexture(PaleSpiritRenderState state) {
        return Identifier.of (PaleGardenUpgrade.MOD_ID,"textures/entity/palespirit.png");
    }

    @Override
    public PaleSpiritRenderState createRenderState() {
        return new PaleSpiritRenderState();
    }

    @Override
    public void updateRenderState(PaleSpiritEntity livingEntity, PaleSpiritRenderState livingEntityRenderState, float f) {
        super.updateRenderState (livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.IDLE.copyFrom (livingEntity.idleAnimState);
        livingEntityRenderState.ATTACK.copyFrom (livingEntity.attackAnimState);
    }
}
