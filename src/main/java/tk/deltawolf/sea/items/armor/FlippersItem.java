package tk.deltawolf.sea.items.armor;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.lists.ItemList;
import tk.deltawolf.sea.renderer.client.model.FlippersModel;
import tk.deltawolf.sea.util.Util;

import javax.annotation.Nullable;
import java.util.Map;

public class FlippersItem extends ArmorItem {
	public FlippersItem(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder){
		super(materialIn, slot, builder);
	}
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (!player.isCreative() && !player.isSpectator()) {
			if (player.isInWater() && !(Util.isInBubbleColumn(world, player))) {
				this.setSwimming(player, true);
			} else {
				this.setSwimming(player,false);
			}
		}
	}
	private void setSwimming(PlayerEntity player, boolean isSwimming){
		ItemStack boots = player.getItemStackFromSlot(EquipmentSlotType.FEET);
		if(Util.isEquipped(player, ItemList.flippers) && isSwimming) {
			if(EnchantmentHelper.getEnchantments(boots).get(Enchantments.DEPTH_STRIDER) == null)
			{
				boots.addEnchantment(Enchantments.DEPTH_STRIDER, 1);
			}
		} else if(Util.isEquipped(player, ItemList.flippers) && !isSwimming) {
			removeEnchantments(boots);
			player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 0, false, false));
		}
	}
	@SuppressWarnings("unused")
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		PlayerEntity player = (PlayerEntity) entity;
		Block above = world.getBlockState(new BlockPos(player.getPosition().getX(), player.getPosition().getY() + 1, player.getPosition().getZ())).getBlock();
		removeEnchantments(stack);
	}

	public void removeEnchantments(ItemStack stack) {
		Map enchants = EnchantmentHelper.getEnchantments(stack);
		if(stack != null) {
			if(enchants.get(Enchantments.DEPTH_STRIDER) != null) {
				enchants.remove(Enchantments.DEPTH_STRIDER);
				EnchantmentHelper.setEnchantments(enchants, stack);
			}
		}
	}

	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Nullable
	@Override
	@OnlyIn(Dist.CLIENT)
	public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel _default) {
		return new FlippersModel();
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType equipmentSlot, String armorTexture) {
		return Reference.MOD_ID + ":textures/models/armor/flippers.png";
	}

//	@Override
//	@OnlyIn(Dist.CLIENT)
//	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
//		tooltip.add(new StringTextComponent(""));
//	}
}
