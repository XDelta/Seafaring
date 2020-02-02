package tk.deltawolf.sea.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.deltawolf.sea.lists.ItemList;

public class SeaItemGroup extends ItemGroup {
	public static final ItemGroup tabSea = new SeaItemGroup("tabSea") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack createIcon(){
			return new ItemStack(ItemList.haddock);
		}
	};
	public static final ItemGroup tabSeaWIP = new SeaItemGroup("tabSeaWIP") {
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack createIcon(){
			return new ItemStack(ItemList.standard_tank);
		}
	};

	public SeaItemGroup(String label) {
		super(label);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ItemStack createIcon(){
		return new ItemStack(ItemList.haddock);
	}
}
