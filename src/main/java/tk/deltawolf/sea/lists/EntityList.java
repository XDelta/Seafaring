package tk.deltawolf.sea.lists;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.entity.passive.fish.HaddockEntity;
import tk.deltawolf.sea.entity.passive.fish.SwampFeederEntity;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MOD_ID)
public class EntityList {
	public static EntityType<?> HADDOCK = EntityType.Builder.create(HaddockEntity::new, EntityClassification.WATER_CREATURE).size(0.2f,0.3f).build(Reference.MOD_ID + ":haddock").setRegistryName(Reference.MOD_ID, "haddock");
	public static EntityType<?> SWAMPFEEDER = EntityType.Builder.create(SwampFeederEntity::new, EntityClassification.WATER_CREATURE).size(0.2f,0.3f).build(Reference.MOD_ID + ":swamp_feeder").setRegistryName(Reference.MOD_ID, "swamp_feeder");

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(
			HADDOCK,
			SWAMPFEEDER
			//HADDOCK = EntityType.Builder.create(HaddockEntity::new, EntityClassification.WATER_CREATURE).size(0.2f,0.3f).build(Reference.MOD_ID + ":haddock").setRegistryName(Reference.MOD_ID, "haddock")
		);
		registerSpawns();
	}

	public static void registerSpawns() {
		registerEntitySpawn(HADDOCK, 10,2,5, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN);
		registerEntitySpawn(SWAMPFEEDER, 10,1,2, Biomes.SWAMP, Biomes.SWAMP_HILLS);
	}

	public static void registerEntitySpawn(EntityType<?> entity, int weight, int minGroupCountIn, int maxGroupCountIn, Biome... biomes) {
		for(Biome biome : biomes) {
			if(biome != null) {
				biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
			}
		}
	}
}
