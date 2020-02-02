package tk.deltawolf.sea.items;

import net.minecraft.item.Item;

public class ReelItem extends Item {
	private int durability;
	private int luck;

	public ReelItem(int durability, int luck, Properties properties) {
		super(properties);
	}

	public int getDurability() {
		return durability;
	}
	public int getLuck() {
		return luck;
	}
}
