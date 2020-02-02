package tk.deltawolf.sea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import tk.deltawolf.sea.state.BlockStateProperties;

public class SeaStone extends WaterloggedBlockBase {
	public static final IntegerProperty SIZE = BlockStateProperties.SIZE_1_2;
	protected static final VoxelShape SHAPE1 = Block.makeCuboidShape(6.0D, 0.0D, 5.0D, 11.0D, 3.5D, 10.0D);
	protected static final VoxelShape SHAPE2 = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 3.5D, 12.0D);

	public SeaStone(Block.Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(SIZE, Integer.valueOf(1)).with(WATERLOGGED, Boolean.valueOf(false)));
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getWorld().getBlockState(context.getPos());
		if (blockstate.getBlock() == this) {
			return blockstate.with(SIZE, Integer.valueOf(Math.min(2, blockstate.get(SIZE) + 1)));
		} else {
			IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
			boolean flag = ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
			return super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(flag));
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(SIZE, WATERLOGGED);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(SIZE)) {
			case 1:
			default:
				return SHAPE1;
			case 2:
				return SHAPE2;
		}
	}
}
