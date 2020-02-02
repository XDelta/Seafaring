package tk.deltawolf.sea.items;

import net.minecraft.item.Item;

public class HookItem extends Item {
	private int durability;
	private int luck;

	public HookItem(int durability, int luck, Properties properties) {
		super(properties);
	}

	public int getDurability() {
		return durability;
	}
	public int getLuck() {
		return luck;
	}
}
