package tk.deltawolf.sea.entity.passive.fish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import tk.deltawolf.sea.lists.ItemList;

//shrug, it works tho
@SuppressWarnings("ALL") //temp
public class HaddockEntity extends AbstractGroupFishEntity {
	public HaddockEntity(EntityType<? extends HaddockEntity> entityType, World world) {
		super(entityType, world);
	}

	protected void registerGoals() {
		super.registerGoals();
	}
	protected ItemStack getFishBucket() {
		return new ItemStack(Items.COD_BUCKET);
	}
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_COD_AMBIENT;
	}
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_COD_DEATH;
	}
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_COD_HURT;
	}
	protected SoundEvent getFlopSound() {
		return SoundEvents.ENTITY_COD_FLOP;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(ItemList.haddock_spawn_egg.get());
	}

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
	}
}
