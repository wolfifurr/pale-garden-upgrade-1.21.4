package wolfifurr.palegardenupgrade;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import wolfifurr.palegardenupgrade.models.*;
import wolfifurr.palegardenupgrade.renderers.*;
import wolfifurr.palegardenupgrade.util.ModEntities;

public class PaleGardenUpgradeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer (HauntedFlower.LAYER_LOCATION,HauntedFlower::createBodyLayer);
        EntityRendererRegistry.register (ModEntities.HAUNTED_FLOWER_ENTITY, (HauntedFlowerRenderer::new));

        EntityModelLayerRegistry.registerModelLayer (HiddenWoodling.LAYER_LOCATION,HiddenWoodling::createBodyLayer);
        EntityRendererRegistry.register (ModEntities.HIDDEN_WOODLING_ENTITY, (HiddenWoodlingRenderer::new));

        EntityModelLayerRegistry.registerModelLayer (MossyLizard.LAYER_LOCATION,MossyLizard::createBodyLayer);
        EntityRendererRegistry.register (ModEntities.MOSSY_LIZARD_ENTITY, (MossyLizardRenderer::new));

        EntityModelLayerRegistry.registerModelLayer (PaleSpirit.LAYER_LOCATION,PaleSpirit::createBodyLayer);
        EntityRendererRegistry.register (ModEntities.PALE_SPIRIT_ENTITY, (PaleSpiritRenderer::new));

        EntityModelLayerRegistry.registerModelLayer (WhiteFirefly.LAYER_LOCATION,WhiteFirefly::createBodyLayer);
        EntityRendererRegistry.register (ModEntities.WHITE_FIREFLY_ENTITY, (WhiteFireflyRenderer::new));
    }
}
