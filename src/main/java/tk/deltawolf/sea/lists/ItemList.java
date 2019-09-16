package tk.deltawolf.sea.lists;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.itemgroup.SeaItemGroup;
import tk.deltawolf.sea.items.armor.OxygenTank;
import tk.deltawolf.sea.items.armor.ScubaMask;
import tk.deltawolf.sea.lists.materials.ArmorMaterials;
import tk.deltawolf.sea.util.Util;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MOD_ID)
public class ItemList {
	public static Item salt;
	public static Item salted_cod;
	public static Item salted_haddock;
	public static Item salted_meat;
	public static Item salted_pufferfish;
	public static Item salted_salmon;

	public static Item sea_moss;
	public static Item pebble;

	public static Item pearl;
	public static Item clam;
	public static Item haddock;
	public static Item cooked_haddock;

	public static Item scuba_mask;
	public static Item basic_tank;
	public static Item standard_tank;
	public static Item high_capacity_tank;
	//eggs
	public static Item haddock_spawn_egg;
	public static Item swamp_feeder_spawn_egg;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) { //ForgeRegistry<Item> registry
		Util.Log().info("Registering Items");
		event.getRegistry().registerAll(
			salt = new Item(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "salt"),
			pebble = new Item(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "pebble"),
			salted_cod = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_COD)).setRegistryName(Reference.MOD_ID, "salted_cod"),
			salted_haddock = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_HADDOCK)).setRegistryName(Reference.MOD_ID, "salted_haddock"),
			salted_meat = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_MEAT)).setRegistryName(Reference.MOD_ID, "salted_meat"),
			salted_pufferfish = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_PUFFER)).setRegistryName(Reference.MOD_ID, "salted_pufferfish"),
			salted_salmon = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_SALMON)).setRegistryName(Reference.MOD_ID, "salted_salmon"),
			haddock = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.HADDOCK)).setRegistryName(Reference.MOD_ID, "haddock"),
			cooked_haddock = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.COOKED_HADDOCK)).setRegistryName(Reference.MOD_ID, "cooked_haddock"),

			scuba_mask = new ScubaMask(ArmorMaterials.scuba, EquipmentSlotType.HEAD, new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "scuba_mask"),
			basic_tank = new OxygenTank(ArmorMaterials.scuba, EquipmentSlotType.CHEST, 10, new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "basic_tank"),
			standard_tank = new OxygenTank(ArmorMaterials.scuba, EquipmentSlotType.CHEST, 20, new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "standard_tank"),
			high_capacity_tank = new OxygenTank(ArmorMaterials.scuba, EquipmentSlotType.CHEST, 60, new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "high_capacity_tank"),

			haddock_spawn_egg = registerSpawnEgg(EntityList.HADDOCK,0x93a1b7, 0x526675, "haddock_spawn_egg"),
			swamp_feeder_spawn_egg = registerSpawnEgg(EntityList.SWAMPFEEDER,0x5b9450, 0x33522d, "swamp_feeder_spawn_egg"),

			createItemBlockForBlock(BlockList.salt_pile, new Item.Properties().group(SeaItemGroup.tabSea)),
			createItemBlockForBlock(BlockList.compressed_sponge, new Item.Properties().group(SeaItemGroup.tabSea)),
			createItemBlockForBlock(BlockList.wet_compressed_sponge, new Item.Properties().group(SeaItemGroup.tabSea)),
			createItemBlockForBlock(BlockList.sea_stone, new Item.Properties().group(SeaItemGroup.tabSea))
		);

		Util.Log().info("Registered Items");
	}

	private static BlockItem createItemBlockForBlock(Block block, Item.Properties properties) {
		return (BlockItem) new BlockItem(block, properties).setRegistryName(block.getRegistryName());
	}

	public static Item registerSpawnEgg(EntityType<?> type, int primary, int secondary, String name) {
		SpawnEggItem item = new SpawnEggItem(type, primary, secondary, new Item.Properties().group(SeaItemGroup.tabSea));
		item.setRegistryName(Reference.MOD_ID, name);
		return item;
	}
}
