package tk.deltawolf.sea;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tk.deltawolf.sea.lists.RendererList;
import tk.deltawolf.sea.proxy.ProxyClient;
import tk.deltawolf.sea.proxy.ProxyCommon;
import tk.deltawolf.sea.util.Util;

@Mod(value = Reference.MOD_ID)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Seafaring {
	public static Seafaring instance;
	private final ProxyCommon proxy = DistExecutor.runForDist(() -> ProxyClient::new, () -> ProxyCommon::new);
	public Seafaring() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);
		MinecraftForge.EVENT_BUS.register(this);
		proxy.construct();
	}
	private void preInit(final FMLCommonSetupEvent event) {
		proxy.preInit();
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {}
	private void clientRegistries(final FMLClientSetupEvent event) {
		RendererList.registerEntityRenderers();
		Util.Log().info("clientRegisteries method registered");
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			Util.Log().info("Items registered");
		}
	}
	private void postInit(final FMLLoadCompleteEvent event) {
		proxy.postInit();
	}
}
