package tk.deltawolf.sea.config;

import net.minecraftforge.fml.config.ModConfig;

public class ConfigHelper {
	public static ModConfig commonConfig;
	public static ModConfig clientConfig;

	public static void updateCommonConfig(ModConfig config) {
		commonConfig = config;

		Config.enableHaddockSpawning = Config.COMMON.enableHaddockSpawning.get();
		Config.enableSwampfeederSpawning = Config.COMMON.enableSwampfeederSpawning.get();

		Config.enableSaltPileSpawning = Config.COMMON.enableSaltPileSpawning.get();
		Config.enableSeaStoneSpawning = Config.COMMON.enableSeaStoneSpawning.get();
		Config.enableZabluVineSpawning = Config.COMMON.enableZabluVineSpawning.get();

		Config.zabluStunDuration = Config.COMMON.zabluStunDuration.get();

		Config.enableGameModeCommands = Config.COMMON.enableGameModeCommands.get();

		//Config.test = Config.COMMON.test.get();
	}

	public static void updateClientConfig(ModConfig config) {
		clientConfig = config;

		Config.enableWaterFogDensity = Config.CLIENT.enableWaterFogDensity.get();
	}
}
