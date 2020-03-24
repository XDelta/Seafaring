package tk.deltawolf.sea.worldgen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraftforge.registries.ForgeRegistries;
import tk.deltawolf.sea.config.Config;
import tk.deltawolf.sea.lists.BlockList;

import java.util.Random;
import java.util.function.Function;

public class ZabluVineFeature  extends Feature<NoFeatureConfig> {
	public ZabluVineFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
		super(configFactory);
	}

	/** Checks if provided {@Link BlockPos} is a valid place for this vine to exist<br>
	 * Vine requires a block of stone or dirt above it and<br>
	 * 2 blocks of water below that stone/dirt
	 */
	public boolean isValidSpawnPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Block blockAbove = worldIn.getBlockState(pos.up()).getBlock(); //block for vine to attach to

		if((Block.isRock(blockAbove) || Block.isDirt(blockAbove))) { // check block above for stone/dirt forge tag
			if(worldIn.getBlockState(pos).getBlock() == Blocks.WATER) { //block to check if vine can be placed here, should always pass but sanity check
				if(worldIn.getBlockState(pos.down(1)).getBlock() == Blocks.WATER) { //block below should be water as well
					return true;
				}
			}
		}
		return false;
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
 		int j = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ());
		BlockState blockstate = BlockList.zablu_vine.get().getDefaultState();//.with(WATERLOGGED, Boolean.valueOf(true))
		//BlockPos blockpos = new BlockPos(pos.getX(), j, pos.getZ());
		for (int i = 0; i < 8; i++) {//try 8 times vertically to find suitable spawn
			BlockPos blockpos = new BlockPos(pos.getX(), j - i, pos.getZ());
			if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) {
				if (this.isValidSpawnPosition(blockstate, worldIn, blockpos)) {// worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER &&
					//Util.Log().debug("ZabluVine ValidSpawned pos: " + blockpos + "i: " + i);
					worldIn.setBlockState(blockpos, blockstate,2);
					return true;
				}
			}
		}
		return false;
	}

	public static void addFeature() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(ZabluVineFeature::process);
	}
	private static void process(Biome biome) {
		if (Config.enableZabluVineSpawning) {
			if (biome.getCategory() == Biome.Category.OCEAN) {
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
					Biome.createDecoratedFeature(
						Features.ZABLU_VINE,
						IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED,
						new TopSolidWithNoiseConfig(4, 200.0D, 0.75D, Heightmap.Type.OCEAN_FLOOR_WG)
						//noise_to_count_ratio, noise_factor, noise_offset, heightmap
					)
				);
			}
		}
	}
}
