package tk.deltawolf.sea.lists;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.entity.passive.fish.HaddockEntity;
import tk.deltawolf.sea.entity.passive.fish.SwampFeederEntity;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MOD_ID)
public class EntityList {
	public static EntityType<HaddockEntity> HADDOCK = createEntity(HaddockEntity::new, EntityClassification.WATER_CREATURE,"haddock",0.2f,0.3f, 20);
	public static EntityType<SwampFeederEntity> SWAMPFEEDER = createEntity(SwampFeederEntity::new, EntityClassification.WATER_CREATURE, "swamp_feeder",0.2f,0.3f, 20);

	private static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> f, EntityClassification entityClass, String name, float width, float height, int trackingRange) {
		EntityType<T> entity = EntityType.Builder.create(f, entityClass)
			.size(width, height)
			.setTrackingRange(trackingRange)
			.build(Reference.MOD_ID + ":" + name);

		entity.setRegistryName(Reference.MOD_ID, name);
		return entity;
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(
			HADDOCK,
			SWAMPFEEDER
		);
		registerSpawns();
	}

	public static void registerSpawns() {
		registerEntitySpawn(HADDOCK, 10,2,5, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN);
		registerEntitySpawn(SWAMPFEEDER, 10,1,2, Biomes.SWAMP, Biomes.SWAMP_HILLS);

		registerSpawnConditions();
	}

	private static void registerSpawnConditions () {
		EntitySpawnPlacementRegistry.register(HADDOCK, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityList::deepOceanCondition);
		EntitySpawnPlacementRegistry.register(SWAMPFEEDER, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityList::swampCondition);
	}

	public static void registerEntitySpawn(EntityType<?> entity, int weight, int minGroupCountIn, int maxGroupCountIn, Biome... biomes) {
		for(Biome biome : biomes) {
			if(biome != null) {
				biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
			}
		}
	}

	private static boolean deepOceanCondition(EntityType<? extends WaterMobEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		return pos.getY() <= 40;
	}

	private static boolean swampCondition(EntityType<? extends WaterMobEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		return (pos.getY() >= 50 && pos.getY() <= 64);
	}

	private static boolean mountainLakeCondition(EntityType<? extends WaterMobEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		return (pos.getY() >= 80 && pos.getY() <= 105);
	}

	private static boolean ravineCondition(EntityType<? extends WaterMobEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		return pos.getY() <= 30;
	}
	private static boolean netherCondition(EntityType<? extends MobEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if(world.getDimension().getType() != DimensionType.THE_NETHER) return false;
		return true;
	}

}
