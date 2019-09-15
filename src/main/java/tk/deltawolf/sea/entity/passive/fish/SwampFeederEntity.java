package tk.deltawolf.sea.entity.passive.fish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import tk.deltawolf.sea.lists.EntityList;

@SuppressWarnings("ALL")
public class SwampFeederEntity extends AbstractGroupFishEntity {
	public SwampFeederEntity(EntityType<? extends SwampFeederEntity> entityType, World world) {
		super((EntityType<? extends SwampFeederEntity>) EntityList.SWAMPFEEDER, world);
	}

	protected void registerGoals() {
		super.registerGoals();
	}
	protected ItemStack getFishBucket() {
		return new ItemStack(Items.MOSSY_COBBLESTONE, 4);
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

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
	}
}
