package tk.deltawolf.sea.lists.materials;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.lists.ItemList;

public enum ArmorMaterials implements IArmorMaterial {
	scuba("scuba", 30, new int[] {1, 0, 1, 1}, 0, ItemList.rubber, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f),
	weighted("weighted", 30, new int[] {2, 0, 2, 2}, 0, Items.COBBLESTONE, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0f);

	private static final int[] max_damage_array = new int[]{10, 1, 1, 13}; //feet, legs, body, head
	private String name;
	private int durability, enchantability;
	private Item repairItem;
	private int[] damageReductionAmounts;
	private float toughness;
	private SoundEvent equipSound;

	private ArmorMaterials(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repairItem, SoundEvent equipSound, float toughness) {
		this.name = name;
		this.equipSound = equipSound;
		this.durability = durability;
		this.enchantability = enchantability;
		this.repairItem = repairItem;
		this.damageReductionAmounts = damageReductionAmounts;
		this.toughness = toughness;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot)
	{
		return this.damageReductionAmounts[slot.getIndex()];
	}

	@Override
	public int getDurability(EquipmentSlotType slot)
	{
		return max_damage_array[slot.getIndex()] * this.durability;
	}

	@Override
	public int getEnchantability()
	{
		return this.enchantability;
	}

	@Override
	public String getName()
	{
		return Reference.MOD_ID + ":" + this.name;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		return Ingredient.fromItems(this.repairItem);
	}

	@Override
	public SoundEvent getSoundEvent()
	{
		return this.equipSound;
	}

	@Override
	public float getToughness()
	{
		return this.toughness;
	}
}
