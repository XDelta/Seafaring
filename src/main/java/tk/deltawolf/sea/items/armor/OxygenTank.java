package tk.deltawolf.sea.items.armor;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.deltawolf.sea.lists.ItemList;
import tk.deltawolf.sea.util.Util;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class OxygenTank extends ArmorItem {
	private int capacity;
	public OxygenTank(IArmorMaterial materialIn, EquipmentSlotType slot, int capacity, Item.Properties builder) {
		super(materialIn, slot, builder);
		this.capacity = capacity;
	}
	public int getMaxDamage(ItemStack stack) {
		return 20 * capacity; //ticks * seconds
	}

	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!player.isCreative() && !player.isSpectator()) {
			Block above = world.getBlockState(new BlockPos(player.getPosition().getX(), player.getPosition().getY() + 1, player.getPosition().getZ())).getBlock();
			ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
			if (player.isInWater()) {
				if (above != Blocks.WATER && above != Blocks.SEAGRASS && above != Blocks.KELP && above != Blocks.KELP_PLANT && above != Blocks.TALL_SEAGRASS) {
					this.repairTank(chest, player);
				} else if (Util.isEquipped(player, ItemList.scuba_mask, 0) && chest.getItem() == ItemList.basic_tank) {
					this.damageTank(chest, player);
				}
			} else {
				this.repairTank(chest, player);
			}
		}
	}

	private void repairTank(ItemStack chest, PlayerEntity player) {
		if (chest.getItem().equals(ItemList.basic_tank) && chest.getDamage() < chest.getMaxDamage()) {
			chest.damageItem(-(1), player, (Consumer)null);
		}
	}

	private boolean isTank(Item tank) {
		if(tank == ItemList.basic_tank) {
			return true;
		} else if(tank == ItemList.standard_tank) {
			return true;
		} else if (tank == ItemList.high_capacity_tank) {
			return true;
		} else {
			return false;
		}
	}

	private void damageTank(ItemStack chest, PlayerEntity player) {
		if (isTank(chest.getItem()) && chest.getDamage() < chest.getMaxDamage() - 20) {
			int currentAir = player.getAir();
			int maxAir = player.getMaxAir();
			int airToFill = maxAir - currentAir;
			player.setAir(player.getMaxAir());
			if (airToFill > 1) {
				chest.damageItem(airToFill/2, player, (Consumer) null);
				Util.Log().info("Air:" + airToFill);
			}
			chest.damageItem(1, player, (Consumer)null);
		}
	}

	public boolean isRepairable(ItemStack stack) {
		return false;
	}

	public static int getLowWarn(Item chest) {
		if(chest == ItemList.basic_tank) {
			return 5;
		} else if(chest == ItemList.standard_tank) {
			return 8;
		} else if (chest == ItemList.high_capacity_tank) {
			return 18;
		} else {
			return 0;
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		long ticks = stack.getMaxDamage() - stack.getDamage();
		long minutes = ticks / 20 / 60;
		long seconds = ticks / 20 % 60;

		if(minutes == 0 && seconds <= 1){//&& stack.getDamage() == stack.getMaxDamage() - 20
			tooltip.add(new StringTextComponent(TextFormatting.RED + "Tank Empty"));
		} else if (minutes == 0 && seconds <= getLowWarn(stack.getItem())) {
			tooltip.add(new StringTextComponent( TextFormatting.YELLOW + "Oxygen: " + minutes + ":" + (seconds == 0 ? "00" : seconds < 10 ? "0" + seconds : seconds)));
		} else {
			tooltip.add(new StringTextComponent("Oxygen: " + minutes + ":" + (seconds == 0 ? "00" : seconds < 10 ? "0" + seconds : seconds)));
		}
	}
}
