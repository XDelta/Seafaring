package tk.deltawolf.sea.worldgen.feature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.SeaPickleFeature;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public abstract class Features {
	public static final Feature<NoFeatureConfig> SALT_PILE = new SaltFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SEA_STONE = new SeaStoneFeature(NoFeatureConfig::deserialize);

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
			SALT_PILE,
			SEA_STONE
		);
	}
}
