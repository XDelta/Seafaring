package tk.deltawolf.sea.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.deltawolf.sea.config.Config;
import tk.deltawolf.sea.lists.EffectList;
import tk.deltawolf.sea.util.Util;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ZabluVine extends BushBlock implements ILiquidContainer {
	private static final VoxelShape SHAPE = Block.makeCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 16.0D, 13.0D);

	public ZabluVine(Block.Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		VoxelShape voxelshape = this.getShape(stateIn, worldIn, pos, ISelectionContext.dummy());
		Vec3d vec3d = voxelshape.getBoundingBox().getCenter();
		double d0 = (double)pos.getX() + vec3d.x;
		double d1 = (double)pos.getZ() + vec3d.z;

		for(int i = 0; i < 3; ++i) {
			if (rand.nextBoolean()) {
				worldIn.addParticle(ParticleTypes.UNDERWATER, d0 + (double)(rand.nextFloat() / 5.0F), (double)pos.getY() + (0.5D - (double)rand.nextFloat()), d1 + (double)(rand.nextFloat() / 5.0F), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof LivingEntity && !(entityIn.isSpectator())) {
			((LivingEntity)entityIn).addPotionEffect(new EffectInstance(EffectList.STUNNED.get(), Config.zabluStunDuration,0));//duration/amp
			entityIn.setMotion(entityIn.getMotion().mul(0.25D, 0.5D, 0.25D));
			if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.posX || entityIn.lastTickPosZ != entityIn.posZ)) {
				double d0 = Math.abs(entityIn.posX - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.posZ - entityIn.lastTickPosZ);
				if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
					entityIn.attackEntityFrom(DamageSource.GENERIC, 1.0F);
				}
			}
		}
	}

	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		//Util.Log().warn("ZabluVine checked valid ground");
		return isValidCeiling(state, worldIn, pos);
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
		if (state.getBlock() == this) {
			return (Block.isRock(blockAbove) || Block.isDirt(blockAbove));
		}
		return isValidCeiling(state, worldIn, pos);
	}

	//check if block is solid on bottom and is a type of rock or dirt
	public boolean isValidCeiling(BlockState state, IBlockReader worldIn, BlockPos pos) {
		//state.isSolidSide, is the directional face solid?
		return state.func_224755_d(worldIn, pos, Direction.DOWN) && (Block.isRock(state.getBlock()) || Block.isDirt(state.getBlock()));
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8 ? super.getStateForPlacement(context) : null;
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		BlockState blockstate = super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		if (!blockstate.isAir()) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return blockstate;
	}

	public IFluidState getFluidState(BlockState state) {
		return Fluids.WATER.getStillFluidState(false);
	}

	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return false;
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new TranslationTextComponent("block.sea.zablu_vine.tooltip"));
	}
}
