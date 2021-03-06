package tk.deltawolf.sea.worldgen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
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

import static tk.deltawolf.sea.blocks.SeaStone.SIZE;
import static tk.deltawolf.sea.blocks.WaterloggedBlockBase.WATERLOGGED;

public class SeaStoneFeature extends Feature<NoFeatureConfig> {
	public SeaStoneFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
		super(configFactory);
	}

	public boolean isValidSpawnPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		return block != Blocks.MAGMA_BLOCK && block != Blocks.OBSIDIAN && (blockstate.func_224755_d(worldIn, blockpos, Direction.UP));
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		int j = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ());
		BlockPos blockpos = new BlockPos(pos.getX(), j, pos.getZ());
		if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER) {
			BlockState blockstate = BlockList.sea_stone.get().getDefaultState().with(WATERLOGGED, Boolean.valueOf(true)).with(SIZE, Integer.valueOf(rand.nextInt(2)+1));
			if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.WATER && this.isValidSpawnPosition(blockstate, worldIn, blockpos)) {
				worldIn.setBlockState(blockpos, blockstate, 2);
				return true;
			}
		}
		return false;
	}

	public static void addFeature() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(SeaStoneFeature::process);
	}
	private static void process(Biome biome) {
		if (Config.enableSeaStoneSpawning) {
			if (biome.getCategory() == Biome.Category.OCEAN || biome.getCategory() == Biome.Category.RIVER) {
				biome.addFeature(GenerationStage.Decoration.RAW_GENERATION,
					Biome.createDecoratedFeature(
						Features.SEA_STONE,
						IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED,
						new TopSolidWithNoiseConfig(1, 140.0D, 1.1D, Heightmap.Type.OCEAN_FLOOR_WG)
					)
				);
			}
		}
	}
}
