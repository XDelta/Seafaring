package tk.deltawolf.sea.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import tk.deltawolf.sea.lists.ItemList;

public class SeaItemGroup extends ItemGroup {
	public static final ItemGroup tabSea = new SeaItemGroup();
	public SeaItemGroup(){
		super("tabSea");
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public ItemStack createIcon(){
		return new ItemStack(ItemList.haddock, 43);
	}
}
