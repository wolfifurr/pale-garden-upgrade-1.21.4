package wolfifurr.palegardenupgrade;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wolfifurr.palegardenupgrade.entity.*;
import wolfifurr.palegardenupgrade.util.ModEntities;
import wolfifurr.palegardenupgrade.world.gen.ModWorldGen;

public class PaleGardenUpgrade implements ModInitializer {
	public static final String MOD_ID = "palegardenupgrade";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModEntities.registerEntities();

		ModWorldGen.generateModWorldGen();

		FabricDefaultAttributeRegistry.register (ModEntities.HAUNTED_FLOWER_ENTITY, HauntedFlowerEntity.createAttributes ());
		FabricDefaultAttributeRegistry.register (ModEntities.HIDDEN_WOODLING_ENTITY, HiddenWoodlingEntity.createAttributes ());
		FabricDefaultAttributeRegistry.register (ModEntities.MOSSY_LIZARD_ENTITY, MossyLizardEntity.createAttributes ());
		FabricDefaultAttributeRegistry.register (ModEntities.PALE_SPIRIT_ENTITY, PaleSpiritEntity.createAttributes ());
		FabricDefaultAttributeRegistry.register (ModEntities.WHITE_FIREFLY_ENTITY, WhiteFireflyEntity.createAttributes ());
	}
}