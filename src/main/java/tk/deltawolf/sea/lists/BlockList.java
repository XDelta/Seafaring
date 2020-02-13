package tk.deltawolf.sea.lists;

import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.blocks.*;
import tk.deltawolf.sea.util.Util;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MOD_ID)
public class BlockList {
	public static Block salt_pile;
	public static Block compressed_sponge;
	public static Block wet_compressed_sponge;
	public static Block sea_stone;
	public static Block red_sea_grass;

	public static Block tackle_bench;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
			salt_pile = new SaltPile(Block.Properties.create(Material.OCEAN_PLANT).hardnessAndResistance(0.05f, 0.05f).doesNotBlockMovement().variableOpacity()).setRegistryName(Reference.MOD_ID, "salt_pile"),
			compressed_sponge = new CompressedSponge(Block.Properties.create(Material.SPONGE).hardnessAndResistance(0.6f).sound(SoundType.PLANT)).setRegistryName(Reference.MOD_ID, "compressed_sponge"),
			wet_compressed_sponge = new WetCompressedSponge(Block.Properties.create(Material.SPONGE).hardnessAndResistance(0.6f).sound(SoundType.PLANT)).setRegistryName(Reference.MOD_ID, "wet_compressed_sponge"),
			sea_stone = new SeaStone(Block.Properties.create(Material.OCEAN_PLANT).hardnessAndResistance(0.05f, 0.05f).variableOpacity()).setRegistryName(Reference.MOD_ID, "sea_stone"),
			red_sea_grass = new RedSeaGrass(Block.Properties.create(Material.SEA_GRASS).hardnessAndResistance(0f).doesNotBlockMovement().sound(SoundType.WET_GRASS)).setRegistryName(Reference.MOD_ID, "red_sea_grass"),
			tackle_bench = new TackleBench(Block.Properties.create(Material.ANVIL).hardnessAndResistance(0.6f).sound(SoundType.ANVIL)).setRegistryName(Reference.MOD_ID, "tackle_bench")
			// KELP = register("kelp", new KelpTopBlock(Block.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
			// KELP_PLANT = register("kelp_plant", new KelpBlock((KelpTopBlock)KELP, Block.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
		);
		Util.Log().info("Registered Blocks");
	}
}
