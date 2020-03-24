package tk.deltawolf.sea.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
	//Common
	public static boolean enableHaddockSpawning;
	public static boolean enableSwampfeederSpawning;

	public static boolean enableSaltPileSpawning;
	public static boolean enableSeaStoneSpawning;
	public static boolean enableZabluVineSpawning;

	public static int zabluStunDuration;

	public static boolean enableGameModeCommands;
	//public static boolean test;
	//Client
	public static boolean enableWaterFogDensity;

	public static class Common {
		public final ConfigValue<Boolean> enableHaddockSpawning;
		public final ConfigValue<Boolean> enableSwampfeederSpawning;

		public final ConfigValue<Boolean> enableSaltPileSpawning;
		public final ConfigValue<Boolean> enableSeaStoneSpawning;
		public final ConfigValue<Boolean> enableZabluVineSpawning;

		public final ConfigValue<Integer> zabluStunDuration;

		public final ConfigValue<Boolean> enableGameModeCommands;
		//public final ConfigValue<Boolean> test;

		Common(ForgeConfigSpec.Builder builder) {
			//TODO figure out how to set an order or sort?
			//looks to keep the order on fresh configs, appends new settings/sections to end
			//keep fresh configs alphabetical
			builder.comment("Common Settings for Seafaring");
			builder.push("Commands");
			enableGameModeCommands = builder
				.comment("Enables all shortened gamemode commands (default: true)")
				.worldRestart()
				.define("enableGameModeCommands", true);
			builder.pop();
			builder.push("Effects");
			zabluStunDuration = builder
				.comment("Zablu Vines stun duration in ticks (default: 220)")
				.defineInRange("zabluStunDuration", 220,20,1200);
			builder.pop();
			builder.push("Spawning");
			enableHaddockSpawning = builder
					.comment("Toggle Haddock spawning (default: true)")
					.worldRestart()
					.define("enableHaddockSpawning", true);
			enableSwampfeederSpawning = builder
					.comment("Toggle Swamp Feeder spawning (default: true)")
					.worldRestart()
					.define("enableSwampfeederSpawning", true);
			enableSaltPileSpawning = builder
					.comment("Toggle Salt Piles spawning (default: true)")
					.worldRestart()
					.define("enableSaltPileSpawning", true);
			enableSeaStoneSpawning = builder
					.comment("Toggle Sea Stones spawning (default: true)")
					.worldRestart()
					.define("enableSeaStoneSpawning", true);
			enableZabluVineSpawning = builder
					.comment("Toggle Zablu Vines spawning (default: true)")
					.worldRestart()
					.define("enableZabluVineSpawning", true);
			builder.pop();
		}
	}

	/**
	 * Client specific configuration
	 */
	public static class Client {
		public final ConfigValue<Boolean> enableWaterFogDensity;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client Settings for Seafaring")
					.push("Rendering");
			enableWaterFogDensity = builder
					.comment("Enable mod fog density (default: false)")
					.define("enableWaterFogDensity", false);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMONSPEC;
	public static final Common COMMON;
	public static final ForgeConfigSpec CLIENTSPEC;
	public static final Client CLIENT;

	static {
		//Common
		final Pair<Common, ForgeConfigSpec> specPairCommon = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMONSPEC = specPairCommon.getRight();
		COMMON = specPairCommon.getLeft();

		//Client
		final Pair<Client, ForgeConfigSpec> specPairClient = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENTSPEC = specPairClient.getRight();
		CLIENT = specPairClient.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.ConfigReloading configEvent) {}
}
