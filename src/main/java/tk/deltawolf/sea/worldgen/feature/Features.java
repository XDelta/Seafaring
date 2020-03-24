package tk.deltawolf.sea.worldgen.feature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public abstract class Features {
	public static final Feature<NoFeatureConfig> SALT_PILE = new SaltFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SEA_STONE = new SeaStoneFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> RED_SEA_GRASS = new RedSeaGrassFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> ZABLU_VINE = new ZabluVineFeature(NoFeatureConfig::deserialize);

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		//Reminder to add registered to {@link Seafaring#WorldGen}
		event.getRegistry().registerAll(
			SALT_PILE,
			SEA_STONE,
			ZABLU_VINE
			//RED_SEA_GRASS
		);
	}
}
