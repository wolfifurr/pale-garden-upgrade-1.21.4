package wolfifurr.palegardenupgrade.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.BiomeKeys;
import wolfifurr.palegardenupgrade.util.ModEntities;

public class ModEntitySpawns {

    public static void addSpawns() {
        BiomeModifications.addSpawn (BiomeSelectors.includeByKey (BiomeKeys.PALE_GARDEN), SpawnGroup.CREATURE,
                ModEntities.HIDDEN_WOODLING_ENTITY,100,2,5);
        SpawnRestriction.register (ModEntities.HIDDEN_WOODLING_ENTITY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> validateBlock(world,pos));
        BiomeModifications.addSpawn (BiomeSelectors.includeByKey (BiomeKeys.PALE_GARDEN), SpawnGroup.CREATURE,
                ModEntities.WHITE_FIREFLY_ENTITY,100,5,10);
        SpawnRestriction.register (ModEntities.WHITE_FIREFLY_ENTITY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING, (type, world, spawnReason, pos, random) -> world.getBlockState (pos.down ()).getBlock ()==Blocks.WATER);
        BiomeModifications.addSpawn (BiomeSelectors.includeByKey (BiomeKeys.PALE_GARDEN), SpawnGroup.CREATURE,
                ModEntities.MOSSY_LIZARD_ENTITY,100,2,5);
        SpawnRestriction.register (ModEntities.MOSSY_LIZARD_ENTITY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> {
                    boolean bl = SpawnReason.isTrialSpawner(spawnReason) ||  world.getBaseLightLevel(pos, 0) < 8;;
                    return validateMossBlock(world,pos)  && bl;
                });
        BiomeModifications.addSpawn (BiomeSelectors.includeByKey (BiomeKeys.PALE_GARDEN), SpawnGroup.CREATURE,
                ModEntities.HAUNTED_FLOWER_ENTITY,100,1,3);
        SpawnRestriction.register (ModEntities.HAUNTED_FLOWER_ENTITY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> validateMossBlock(world,pos));
        BiomeModifications.addSpawn (BiomeSelectors.includeByKey (BiomeKeys.PALE_GARDEN), SpawnGroup.CREATURE,
                ModEntities.PALE_SPIRIT_ENTITY,100,1,1);
        SpawnRestriction.register (ModEntities.PALE_SPIRIT_ENTITY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, world, spawnReason, pos, random) -> {
                    boolean bl = SpawnReason.isTrialSpawner(spawnReason) ||  world.getBaseLightLevel(pos, 0) < 8;;
                    return validateMossBlock(world,pos)  && bl;
                });
    }

    public static boolean validateBlock(ServerWorldAccess world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock ()==Blocks.GRASS_BLOCK || world.getBlockState(pos.down()).getBlock ()==Blocks.PALE_MOSS_BLOCK || world.getBlockState(pos).getBlock ()==Blocks.PALE_MOSS_CARPET || world.getBlockState(pos).getBlock ()==Blocks.SHORT_GRASS || world.getBlockState(pos.down()).getBlock ()==Blocks.TALL_GRASS;
    }

    public static boolean validateMossBlock(ServerWorldAccess world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock ()==Blocks.PALE_MOSS_BLOCK || world.getBlockState(pos).getBlock ()==Blocks.PALE_MOSS_CARPET || world.getBlockState(pos).getBlock ()==Blocks.SHORT_GRASS || world.getBlockState(pos.down()).getBlock ()==Blocks.TALL_GRASS;
    }
}
