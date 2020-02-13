package tk.deltawolf.sea.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import tk.deltawolf.sea.blocks.tiles.TackleBenchTile;

import javax.annotation.Nullable;

public class TackleBench extends Block {
	//private static final TranslationTextComponent translationTextComponent = new TranslationTextComponent("container.tacklebench", new Object[0]);
	//private static final TranslationTextComponent containerName = new TranslationTextComponent("sea.container.tacklebench", new Object[0]);
	//public static DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static BooleanProperty hasItem = BooleanProperty.create("hasItem");

	public TackleBench(Properties properties) {
		super(properties);
		//this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		if(entity != null) {
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
		}
	}
	public static Direction getFacingFromEntity(BlockPos block, LivingEntity entity) {
		return Direction.getFacingFromVector((float) (entity.posX - block.getX()), (float) (entity.posY - block.getY()), (float) (entity.posZ - block.getZ()));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING);
	}

//	public boolean onBlockActivated(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand handIn, BlockRayTraceResult blockRayTraceResult) {
//		playerEntity.openContainer(blockState.getContainer(world, blockPos));
//		//playerEntity.addStat(Stats.INTERACT_WITH_CARTOGRAPHY_TABLE);
//		return true;
//	}

//	@Nullable
//	public INamedContainerProvider getContainer(BlockState blockState, World world, BlockPos blockPos) {
//		return new SimpleNamedContainerProvider((p_220267_2_, p_220267_3_, p_220267_4_) -> {
//			return new CartographyContainer(p_220267_2_, p_220267_3_, IWorldPosCallable.of(world, blockPos));
//		}, translationTextComponent);
//	}

//	public INamedContainerProvider getContainer(BlockState blockState, World world, BlockPos blockPos) {
//		return new SimpleNamedContainerProvider((p_220254_2_, playerInventory, p_220254_4_) -> {
//			return new LoomContainer(p_220254_2_, playerInventory, IWorldPosCallable.of(world, blockPos));
//		}, containerName);
//	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TackleBenchTile();
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		if (!world.isRemote) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
			} else {
				throw new IllegalStateException("Our named container provider is missing!");
			}
			return true;
		}
		return super.onBlockActivated(state, world, pos, player, hand, result);
	}
}
