package tk.deltawolf.sea.lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.container.TackleBenchContainer;
import tk.deltawolf.sea.gui.TackleBenchScreen;
import tk.deltawolf.sea.util.Util;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerList {

	@ObjectHolder(Reference.MOD_ID + ":tackle_bench")
	public static ContainerType<TackleBenchContainer> TACKLEBENCH_CONTAINER;

	public static void init() {
		ScreenManager.registerFactory(TACKLEBENCH_CONTAINER, TackleBenchScreen::new);
	}

	@SubscribeEvent
	public static void onContainerRegistry(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(
			IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				World world = inv.player.world; //Minecraft.getInstance().world;

				return new TackleBenchContainer(windowId, world, pos, inv, getClientPlayer());
			}).setRegistryName("tackle_bench")
		);
	}

	@OnlyIn(Dist.CLIENT)
	private static ClientPlayerEntity getClientPlayer() {
		ClientWorld world = Minecraft.getInstance().world; //Client World //https://www.minecraftforge.net/forum/topic/78238-solved1144-error-when-starting-server/
		if (world.isRemote) {
			return Minecraft.getInstance().player;
		}
		return null;
	}

}
