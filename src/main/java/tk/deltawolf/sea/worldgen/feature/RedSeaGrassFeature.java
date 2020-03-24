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
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.registries.ForgeRegistries;
import tk.deltawolf.sea.lists.BlockList;

import java.util.Random;
import java.util.function.Function;

public class RedSeaGrassFeature extends Feature<NoFeatureConfig> {
	public RedSeaGrassFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
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
			BlockState blockstate = BlockList.red_sea_grass.get().getDefaultState();
			if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.WATER && this.isValidSpawnPosition(blockstate, worldIn, blockpos)) {
				worldIn.setBlockState(blockpos, blockstate,2);
				return true;
			}
		}
		return false;
	}

	public static void addFeature() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(RedSeaGrassFeature::process);
	}
	private static void process(Biome biome) {
		if (biome.getCategory() == Biome.Category.OCEAN){
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Features.RED_SEA_GRASS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.TOP_SOLID_HEIGHTMAP_RANGE, new TopSolidRangeConfig(1,10)));
		}
	}
}
