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
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.lists.ItemList;
import tk.deltawolf.sea.util.Util;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OxygenTank extends ArmorItem {
	private int capacity;
	private static ArrayList<Item> tankList = new ArrayList<Item>();

	public OxygenTank(IArmorMaterial materialIn, EquipmentSlotType slot, int capacity, Item.Properties builder) {
		super(materialIn, slot, builder);
		this.capacity = capacity;
	}

	/**
	* Call to registers tanks in tankList after they are registered to the game
	*/
	public static void registerTanks() {
		tankList.add(ItemList.basic_tank);
		tankList.add(ItemList.standard_tank);
		tankList.add(ItemList.high_capacity_tank);
	}
	public int getMaxDamage(ItemStack stack) {
		return 20 * capacity; //ticks * seconds
	}

	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!player.isCreative() && !player.isSpectator()) {
			ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
			if (player.isPotionActive(Effects.CONDUIT_POWER)) {
				//while in conduit range, worn tanks refill slower
				this.repairTank(chest, player, true);
			} else {
				Block above = world.getBlockState(new BlockPos(player.getPosition().getX(), player.getPosition().getY() + 1, player.getPosition().getZ())).getBlock();
				if (player.isInWater() && !(Util.isInBubbleColumn(world, player))) {
					if (above == Blocks.AIR || above == Blocks.CAVE_AIR) {
						this.repairTank(chest, player, false);
					} else if (Util.isEquipped(player, ItemList.scuba_mask) && isTank(chest.getItem())) {
						this.damageTank(chest, player);
					}
				} else {
					this.repairTank(chest, player, false);
				}
			}
		}
	}

	private void repairTank(ItemStack chest, PlayerEntity player, Boolean conduitPower) {
		if (isTank(chest.getItem()) && chest.getDamage() < chest.getMaxDamage()) {
			if (conduitPower) {
				chest.damageItem(-(1), player, (Consumer)null);
			} else {
				chest.damageItem(-(2), player, (Consumer)null);
			}
		}
	}

	public static boolean isTank(Item tank) {
		if (tankList.contains(tank)){
			return true;
		}else {
			return false;
		}
	}
//TODO ensure when filling oxygen bar of player that we don't take too much from tank, since tanks start at 200units and the player has 300
	private void damageTank(ItemStack chest, PlayerEntity player) {
		if (isTank(chest.getItem()) && chest.getDamage() < chest.getMaxDamage() - 21) {
			int currentAir = player.getAir();
			int maxAir = player.getMaxAir();
			int airToFill = maxAir - currentAir;
			player.setAir(player.getMaxAir());
			if (airToFill > 1) {
				chest.damageItem(airToFill/2, player, (Consumer) null);
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
		} else if (chest == ItemList.standard_tank) {
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

		if(minutes == 0 && seconds <= 1){
			tooltip.add(new StringTextComponent(TextFormatting.RED + "Tank Empty"));
		} else if (minutes == 0 && seconds <= getLowWarn(stack.getItem())) {
			tooltip.add(new StringTextComponent( TextFormatting.YELLOW + "Oxygen: " + minutes + ":" + (seconds == 0 ? "00" : seconds < 10 ? "0" + seconds : seconds)));
		} else {
			tooltip.add(new StringTextComponent("Oxygen: " + minutes + ":" + (seconds == 0 ? "00" : seconds < 10 ? "0" + seconds : seconds)));
		}
	}
}
