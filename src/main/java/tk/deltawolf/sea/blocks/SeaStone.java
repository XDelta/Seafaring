package tk.deltawolf.sea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SeaStone extends WaterBlockFeature {
	//TODO outcrop color/drop/model variation

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 5.0D, 11.0D, 3.5D, 10.0D);

	public SeaStone(Block.Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
