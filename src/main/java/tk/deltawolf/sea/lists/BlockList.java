package tk.deltawolf.sea.lists;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.blocks.CompressedSponge;
import tk.deltawolf.sea.blocks.SaltPile;
import tk.deltawolf.sea.blocks.SeaStone;
import tk.deltawolf.sea.blocks.WetCompressedSponge;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MOD_ID)
public class BlockList {
	public static Block salt_pile = null;
	public static Block compressed_sponge = null;
	public static Block wet_compressed_sponge = null;
	public static Block sea_stone = null;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
			salt_pile = new SaltPile(Block.Properties.create(Material.OCEAN_PLANT).hardnessAndResistance(0.05f, 0.05f).doesNotBlockMovement().variableOpacity()).setRegistryName(Reference.MOD_ID, "salt_pile"),
			compressed_sponge = new CompressedSponge(Block.Properties.create(Material.SPONGE).hardnessAndResistance(0.6f).sound(SoundType.PLANT)).setRegistryName(Reference.MOD_ID, "compressed_sponge"),
			wet_compressed_sponge = new WetCompressedSponge(Block.Properties.create(Material.SPONGE).hardnessAndResistance(0.6f).sound(SoundType.PLANT)).setRegistryName(Reference.MOD_ID, "wet_compressed_sponge"),
			sea_stone = new SeaStone(Block.Properties.create(Material.OCEAN_PLANT).hardnessAndResistance(0.05f, 0.05f).variableOpacity()).setRegistryName(Reference.MOD_ID, "sea_stone")

			// KELP = register("kelp", new KelpTopBlock(Block.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
			// KELP_PLANT = register("kelp_plant", new KelpBlock((KelpTopBlock)KELP, Block.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
		);
	}
}
