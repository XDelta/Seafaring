package tk.deltawolf.sea.worldgen;

import tk.deltawolf.sea.worldgen.feature.RedSeaGrassFeature;
import tk.deltawolf.sea.worldgen.feature.SaltFeature;
import tk.deltawolf.sea.worldgen.feature.SeaStoneFeature;
import tk.deltawolf.sea.worldgen.feature.ZabluVineFeature;

public class WorldGen {
	public static void oreGen() {

	}
	public static void oceanGen() {
		SaltFeature.addFeature();
		SeaStoneFeature.addFeature();
		ZabluVineFeature.addFeature();
		//RedSeaGrassFeature.addFeature();
	}
}
