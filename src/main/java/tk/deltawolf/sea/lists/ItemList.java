package tk.deltawolf.sea.lists;

import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.itemgroup.SeaItemGroup;
import tk.deltawolf.sea.items.HookItem;
import tk.deltawolf.sea.items.ItemFishingRod;
import tk.deltawolf.sea.items.MessageInABottleItem;
import tk.deltawolf.sea.items.ReelItem;
import tk.deltawolf.sea.items.TestItem;
import tk.deltawolf.sea.items.armor.FlippersItem;
import tk.deltawolf.sea.items.armor.OxygenTank;
import tk.deltawolf.sea.items.armor.ScubaMask;
import tk.deltawolf.sea.lists.materials.ArmorMaterials;
import tk.deltawolf.sea.util.Util;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemList {
	public static Item salt;
	public static Item sea_moss;
	public static Item pebble;
	public static Item pearl;
	public static Item driftwood;
	public static Item message_in_a_bottle;
	public static Item valve;
	public static Item polymer;
	//test
	public static Item test;

	public static Item hook;
	public static Item gold_hook;
	public static Item reel;
	public static Item fishing_rod;

	//public static Item pile_of_sand; //4x to make a block of sand
	//mob drops
	//public static Item starfish;
	public static Item haddock;
	public static Item swamp_feeder;
	//public static Item clam;
	//salted food
	public static Item salted_cod;
	public static Item salted_meat;
	public static Item salted_pufferfish;
	public static Item salted_salmon;
	public static Item salted_haddock;
	//cooked food
	public static Item cooked_haddock;
	//armor
	public static Item scuba_mask;
	public static Item basic_tank;
	public static Item standard_tank;
	public static Item high_capacity_tank;
	public static Item flippers;
	public static Item weighted_boots;
	//eggs
	public static Item haddock_spawn_egg;
	public static Item swamp_feeder_spawn_egg;

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) { //ForgeRegistry<Item> registry
		Util.Log().info("Registering Items");
		event.getRegistry().registerAll(
			salt = new Item(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "salt"),
			sea_moss = new Item(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "sea_moss"),
			pebble = new Item(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "pebble"),
			pearl = new Item(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "pearl"),
			driftwood = new Item(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "driftwood"),
			message_in_a_bottle = new MessageInABottleItem(new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "message_in_a_bottle"),
			valve = new Item(new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "valve"),
			polymer = new Item(new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "polymer"),
			test = new TestItem(new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "test"),

			hook = new HookItem(48,0,new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "hook"),
			gold_hook = new HookItem(16,1,new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "gold_hook"),
			reel = new ReelItem(32,0, new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "reel"),
			fishing_rod = new ItemFishingRod(new Item.Properties().maxStackSize(1).group(SeaItemGroup.tabSeaWIP).setNoRepair()).setRegistryName(Reference.MOD_ID, "fishing_rod"),

			haddock = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.HADDOCK)).setRegistryName(Reference.MOD_ID, "haddock"),
			swamp_feeder = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SWAMP_FEEDER)).setRegistryName(Reference.MOD_ID, "swamp_feeder"),

			salted_cod = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_COD)).setRegistryName(Reference.MOD_ID, "salted_cod"),
			salted_meat = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_MEAT)).setRegistryName(Reference.MOD_ID, "salted_meat"),
			salted_pufferfish = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_PUFFER)).setRegistryName(Reference.MOD_ID, "salted_pufferfish"),
			salted_salmon = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_SALMON)).setRegistryName(Reference.MOD_ID, "salted_salmon"),
			salted_haddock = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.SALTED_HADDOCK)).setRegistryName(Reference.MOD_ID, "salted_haddock"),

			cooked_haddock = new Item(new Item.Properties().group(SeaItemGroup.tabSea).food(FoodList.COOKED_HADDOCK)).setRegistryName(Reference.MOD_ID, "cooked_haddock"),

			scuba_mask = new ScubaMask(ArmorMaterials.scuba, EquipmentSlotType.HEAD, new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "scuba_mask"),
			basic_tank = new OxygenTank(ArmorMaterials.scuba, EquipmentSlotType.CHEST, 10, new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "basic_tank"),
			standard_tank = new OxygenTank(ArmorMaterials.scuba, EquipmentSlotType.CHEST, 20, new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "standard_tank"),
			high_capacity_tank = new OxygenTank(ArmorMaterials.scuba, EquipmentSlotType.CHEST, 60, new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "high_capacity_tank"),
			flippers = new FlippersItem(ArmorMaterials.scuba, EquipmentSlotType.FEET, new Item.Properties().group(SeaItemGroup.tabSeaWIP)).setRegistryName(Reference.MOD_ID, "flippers"),
			weighted_boots = new WeightedBootsItem(ArmorMaterials.weighted, EquipmentSlotType.FEET, new Item.Properties().group(SeaItemGroup.tabSea)).setRegistryName(Reference.MOD_ID, "weighted_boots"),

			haddock_spawn_egg = registerSpawnEgg(EntityList.HADDOCK,0x93a1b7, 0x526675, "haddock_spawn_egg"),
			swamp_feeder_spawn_egg = registerSpawnEgg(EntityList.SWAMPFEEDER,0x5b9450, 0x33522d, "swamp_feeder_spawn_egg"),

			createItemBlockForBlock(BlockList.salt_pile, new Item.Properties().group(SeaItemGroup.tabSea)),
			createItemBlockForBlock(BlockList.compressed_sponge, new Item.Properties().group(SeaItemGroup.tabSea)),
			createItemBlockForBlock(BlockList.wet_compressed_sponge, new Item.Properties().group(SeaItemGroup.tabSea)),
			createItemBlockForBlock(BlockList.sea_stone, new Item.Properties().group(SeaItemGroup.tabSea)),
			createItemBlockForBlock(BlockList.red_sea_grass, new Item.Properties().group(SeaItemGroup.tabSeaWIP)),
			createItemBlockForBlock(BlockList.tackle_bench, new Item.Properties().group(SeaItemGroup.tabSeaWIP))
		);

		registerCompostable(0.3F, ItemList.sea_moss);
		OxygenTank.registerTanks();
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

	public static void registerCompostable(float chance, IItemProvider item){
		ComposterBlock.CHANCES.put(item.asItem(), chance);
	}
}
