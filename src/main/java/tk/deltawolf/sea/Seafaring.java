package tk.deltawolf.sea;

import net.minecraft.server.dedicated.ServerProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tk.deltawolf.sea.config.Config;
import tk.deltawolf.sea.config.ConfigHelper;
import tk.deltawolf.sea.handler.GameEventHandler;
import tk.deltawolf.sea.lists.*;
import tk.deltawolf.sea.util.Util;
import tk.deltawolf.sea.worldgen.WorldGen;

@Mod(Reference.MOD_ID)
public class Seafaring {
	public static Seafaring instance;

	public Seafaring() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
		MinecraftForge.EVENT_BUS.register(this);
		GameEventHandler.init();

		//Configs
		FMLJavaModLoadingContext.get().getModEventBus().addListener((ModConfig.ModConfigEvent event) -> {
			final ModConfig config = event.getConfig();
			if(config.getSpec() == Config.CLIENTSPEC) {
				ConfigHelper.updateClientConfig(config);
			}
			if(config.getSpec() == Config.COMMONSPEC) {
				ConfigHelper.updateCommonConfig(config);
			}
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENTSPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMONSPEC);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		Util.Log().info("Setting Up");
		//Analytics.sendAll(); //add config
		WorldGen.oreGen();
		WorldGen.oceanGen();
		ContainerList.init();
	}

	private void clientRegistries(final FMLClientSetupEvent event) {
		RendererList.registerEntityRenderers();
		Util.Log().info("clientRegisteries method registered");
	}

	//private void enqueueIMC(final InterModEnqueueEvent event) {}

	private void postInit(final FMLLoadCompleteEvent event) {
		Util.Log().info("Done!");
	}
}
