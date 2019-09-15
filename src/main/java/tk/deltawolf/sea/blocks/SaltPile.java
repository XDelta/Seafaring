package tk.deltawolf.sea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SaltPile extends WaterBlockFeature {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 4.0D, 12.0D, 3.5D, 12.0D);

	public SaltPile(Block.Properties properties) {
		super(properties);
	}


	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
