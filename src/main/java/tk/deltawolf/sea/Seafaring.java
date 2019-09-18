package tk.deltawolf.sea;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tk.deltawolf.sea.lists.RendererList;
import tk.deltawolf.sea.util.Util;
import tk.deltawolf.sea.worldgen.WorldGen;

@Mod(Reference.MOD_ID)//value =
public class Seafaring {
	public static Seafaring instance;
	//private final ProxyCommon proxy = DistExecutor.runForDist(() -> ProxyClient::new, () -> ProxyCommon::new);
	public Seafaring() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
		MinecraftForge.EVENT_BUS.register(this);
		//proxy.construct();
	}

	private void setup(final FMLCommonSetupEvent event) {
		WorldGen.oreGen();
		WorldGen.oceanGen();
		Util.Log().info("Setting Up");
		//proxy.preInit();
	}

	private void clientRegistries(final FMLClientSetupEvent event) {
		RendererList.registerEntityRenderers();
		Util.Log().info("??4");
		Util.Log().info("clientRegisteries method registered");
	}

	//private void enqueueIMC(final InterModEnqueueEvent event) {}

	private void postInit(final FMLLoadCompleteEvent event) {
		Util.Log().info("Done!");
		//proxy.postInit();
	}
}
