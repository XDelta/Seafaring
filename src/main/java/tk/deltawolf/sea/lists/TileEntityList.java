package tk.deltawolf.sea.lists;

import com.mojang.datafixers.types.Type;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.blocks.tiles.TackleBenchTile;
import tk.deltawolf.sea.util.Util;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityList {

	@ObjectHolder(Reference.MOD_ID + ":tackle_bench")
	public static TileEntityType<TackleBenchTile> TACKLEBENCH_TILE;

	@SubscribeEvent
	public static void onTileEntityRegistry(RegistryEvent.Register<TileEntityType<?>> event) {
		//event.getRegistry().register(
		TileEntityType<?> entityType = TileEntityType.Builder.create(TackleBenchTile::new, BlockList.tackle_bench).build(null).setRegistryName("tackle_bench");
		//TileEntityType<?> entityType = ;
		event.getRegistry().register(entityType);
	}
}
