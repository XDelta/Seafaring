package tk.deltawolf.sea.lists.materials;

import net.minecraft.util.IStringSerializable;

public enum FishingRodParts implements IStringSerializable {

	NONE(0, "none","extra",0,0,""),
	WOODROD(1, "wood_rod","rod", 16 , 0 ,""),
	BAMBOOROD(2, "bamboo_rod","rod", 16 , 0 ,""),
	REINFORCEDROD(3, "reinforced_rod","rod",64,0,""),

	WOODHOOK(4, "wood_hook","hook",1,0,""),
	IRONHOOK(5, "iron_hook","hook",48,0,""),
	GOLDHOOK(6, "golden_hook","hook",16,1,""),
	IRONTREBLE(7, "iron_treble_hook","hook",32,1,""),

	REEL(8, "wood_rod","reel",32,0,""),

	BELL(9, "bell","extra",1,0,"");



	private int meta;
	private String name;
	private String type;
	private int durabilityModifier;
	private int luckModifier;
	private String lore;

	FishingRodParts(int meta, String name, String type, int durabilityModifier, int luckModifier, String lore) {
		this.meta = meta;
		this.name = name;
		this.type = type;
		this.durabilityModifier = durabilityModifier;
		this.luckModifier = luckModifier;
		this.lore = lore;
	}

	public int getMeta() { return meta; }
	public String getName() { return this.name; }
	public String getType() { return this.type; }
	public int getDurabilityModifier() { return durabilityModifier; }
	public int getLuckModifier() { return luckModifier; }
	public String getLore() { return this.lore; }

	//nbt for hook type and reel type when recipes can support setting nbt

	//disable vanilla rod

	//mods:
	//hook
	//    -standard --affect durability and loottables?(change what fish will bite)
	//    -golden
	//    -treble
	//reels
	//    -standard --affect durability and luck
	//    -golden
	//attachments
	//    -bell --rings when fish bite

	//custom bench to make new fishing rods
	//base texture and depending on attachments overlay a texture on


	//parts: rod/reel/hook/extra
}
