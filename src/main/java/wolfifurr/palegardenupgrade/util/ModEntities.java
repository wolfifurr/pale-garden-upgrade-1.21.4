package wolfifurr.palegardenupgrade.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import wolfifurr.palegardenupgrade.PaleGardenUpgrade;
import wolfifurr.palegardenupgrade.entity.*;
import wolfifurr.palegardenupgrade.models.MossyLizard;

public class ModEntities {
    public static final Identifier HAUNTED_FLOWER_ID = Identifier.of (PaleGardenUpgrade.MOD_ID,"hauntedflower");
    public static final EntityType<HauntedFlowerEntity> HAUNTED_FLOWER_ENTITY = Registry.register (Registries.ENTITY_TYPE,
            HAUNTED_FLOWER_ID,
            EntityType.Builder.create (HauntedFlowerEntity::new, SpawnGroup.MONSTER)
                    .dimensions (1,1).build (RegistryKey.of (RegistryKeys.ENTITY_TYPE,HAUNTED_FLOWER_ID)));
    public static final Identifier HIDDEN_WOODLING_ID = Identifier.of (PaleGardenUpgrade.MOD_ID,"hiddenwoodling");
    public static final EntityType<HiddenWoodlingEntity> HIDDEN_WOODLING_ENTITY = Registry.register (Registries.ENTITY_TYPE,
            HIDDEN_WOODLING_ID,
            EntityType.Builder.create (HiddenWoodlingEntity::new, SpawnGroup.MONSTER)
                    .dimensions (1,1.5f).build (RegistryKey.of (RegistryKeys.ENTITY_TYPE,HIDDEN_WOODLING_ID)));
    public static final Identifier MOSSY_LIZARD_ID = Identifier.of (PaleGardenUpgrade.MOD_ID,"mossylizard");
    public static final EntityType<MossyLizardEntity> MOSSY_LIZARD_ENTITY = Registry.register (Registries.ENTITY_TYPE,
            MOSSY_LIZARD_ID,
            EntityType.Builder.create (MossyLizardEntity::new, SpawnGroup.MONSTER)
                    .dimensions (0.6f,0.6f).build (RegistryKey.of (RegistryKeys.ENTITY_TYPE,MOSSY_LIZARD_ID)));
    public static final Identifier PALE_SPIRIT_ID = Identifier.of (PaleGardenUpgrade.MOD_ID,"palespirit");
    public static final EntityType<PaleSpiritEntity> PALE_SPIRIT_ENTITY = Registry.register (Registries.ENTITY_TYPE,
            PALE_SPIRIT_ID,
            EntityType.Builder.create (PaleSpiritEntity::new, SpawnGroup.MONSTER)
                    .dimensions (1,1).build (RegistryKey.of (RegistryKeys.ENTITY_TYPE,PALE_SPIRIT_ID)));
    public static final Identifier WHITE_FIREFLY_ID = Identifier.of (PaleGardenUpgrade.MOD_ID,"whitefirefly");
    public static final EntityType<WhiteFireflyEntity> WHITE_FIREFLY_ENTITY = Registry.register (Registries.ENTITY_TYPE,
            WHITE_FIREFLY_ID,
            EntityType.Builder.create (WhiteFireflyEntity::new, SpawnGroup.CREATURE)
                    .dimensions (0.4f,0.4f).build (RegistryKey.of (RegistryKeys.ENTITY_TYPE,WHITE_FIREFLY_ID)));


    public static void registerEntities() {
    }
}
