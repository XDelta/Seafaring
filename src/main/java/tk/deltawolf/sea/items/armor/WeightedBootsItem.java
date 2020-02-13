package tk.deltawolf.sea.items.armor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.deltawolf.sea.Reference;
import java.util.List;

public class WeightedBootsItem extends ArmorItem {
	public WeightedBootsItem(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder){
		super(materialIn, slot, builder);
	}
	public boolean isRepairable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}


	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlot, String armorTexture) {
		return Reference.MOD_ID + ":textures/models/armor/weighted_boots.png";
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		list.add(new TranslationTextComponent("item.sea.weighted_boots.tooltip"));
	}
}

