package tk.deltawolf.sea.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.nbt.CompoundNBT;
import tk.deltawolf.sea.lists.materials.FishingRodParts;
import tk.deltawolf.sea.util.Util;

import java.util.List;

public class ItemFishingRod extends FishingRodItem {
	public ItemFishingRod(Properties properties) {
		super(properties);
	}
	private int getTotalDamage() {
		return 4;
	}

	//TODO read nbt before getting durability?

	@Override
	public int getMaxDamage(ItemStack stack) {
		//read(stack);
		//Util.Log().info(calcDurability());
		return 50;
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, PlayerEntity playerEntity) {
		//super.onCreated(p_77622_1_, p_77622_2_, p_77622_3_);
		Util.Log().info("rod created");
		read(itemStack);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack itemStack, PlayerEntity player) {
		Util.Log().info("rod dropped");
		read(itemStack);
		write(itemStack);
		return true;
	}

	private int durability;
	private String rod;
	private String hook;
	private String reel;
	private String extra;

	public void read(ItemStack itemStack) {
		CompoundNBT nbt;
		//check if there are any tags already, if not make a new tag
		if (itemStack.hasTag()){
			nbt = itemStack.getTag();
		} else {
			nbt = new CompoundNBT();
		}

		if (nbt.contains("rod")) {
			rod = nbt.getString("rod");
		} else {
			rod = "WOODROD";
		}
		if (nbt.contains("hook")) {
			hook = nbt.getString("hook");
		} else {
			hook = "WOODHOOK";
		}
		if (nbt.contains("reel")) {
			reel = nbt.getString("reel");
		} else {
			reel = "REEL";
		}
		if (nbt.contains("extra")) {
			extra = nbt.getString("extra");
		} else {
			extra = "NONE";
		}
		itemStack.setTag(nbt);
		Util.Log().info(nbt);
	}

	public void write(ItemStack itemStack) {
		CompoundNBT nbt;
		if (itemStack.hasTag()){
			nbt = itemStack.getTag();
		} else {
			nbt = new CompoundNBT();
		}

		itemStack.getTag().putString("rod",rod);
		itemStack.getTag().putString("hook",hook);
		itemStack.getTag().putString("reel",reel);
		itemStack.getTag().putString("extra",extra);
	}

	private int calcDurability() {
		int dRod = FishingRodParts.valueOf(rod).getDurabilityModifier();
		int dHook = FishingRodParts.valueOf(hook).getDurabilityModifier();
		int dReel = FishingRodParts.valueOf(reel).getDurabilityModifier();
		int dExtra = FishingRodParts.valueOf(extra).getDurabilityModifier();

		return (dRod + dHook + dReel + dExtra);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.WHITE + "Rod: " + rod));
		list.add(new StringTextComponent(TextFormatting.WHITE + "Hook: " + hook));
		list.add(new StringTextComponent(TextFormatting.WHITE + "Reel: " + reel));
		if (extra != null && extra != "NONE") {
			list.add(new StringTextComponent(TextFormatting.WHITE + "Extra: " + extra));
		}
	}
}
