package tk.deltawolf.sea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class WaterBlockFeature extends Block implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static Boolean requiresSupport;
	//TODO conditionally require a supporting block, for now only using thing that should break anyway

	public WaterBlockFeature(Block.Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/**
	 * Breaks this Block when not supported by a Block
	 */
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		//MCP func_220055_a = DirectionIn
		return func_220055_a(worldIn, pos.down(), Direction.UP);
	}

	/**
	 * Allows placement into water blocks without breaking the source
	 * */
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockPos blockpos = context.getPos();
		World world = context.getWorld();
		return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(world.getFluidState(blockpos).getFluid() == Fluids.WATER));
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}

	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
}
