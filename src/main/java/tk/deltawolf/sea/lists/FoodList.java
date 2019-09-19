package tk.deltawolf.sea.lists;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodList {

	//TODO Cooked and salted and change current values of salted to be cooked and salted
	//possibly call them processed_

	//hunger(2) = 1 shank / (0.2) sat = 0.2 saturation
	//salted give less hunger more saturation and later will rot slower/ dehydrate you
	public static final Food HADDOCK = (new Food.Builder()).hunger(2).saturation(0.1F).build();
	public static final Food SWAMP_FEEDER = (new Food.Builder()).hunger(2).saturation(0.2F).effect(new EffectInstance(Effects.POISON, 300, 1), 0.6F).build();
	public static final Food COOKED_HADDOCK = (new Food.Builder()).hunger(5).saturation(0.6F).build();
	public static final Food SALTED_HADDOCK = (new Food.Builder()).hunger(1).saturation(0.15F).build();
	//public static final Food COOKED_AND_SALTED_HADDOCK = (new Food.Builder()).hunger(4).saturation(0.7F).build();

	// COD 2/0.1
	// Cooked Cod 5/0.6
	public static final Food SALTED_COD = (new Food.Builder()).hunger(5).saturation(0.6F).build();
	//public static final Food COOKED_AND_SALTED_COD = (new Food.Builder()).hunger(5).saturation(0.6F).build();

	//Rotten Flesh 4/0.1
	public static final Food SALTED_MEAT = (new Food.Builder()).hunger(3).saturation(0.2F).effect(new EffectInstance(Effects.HUNGER, 300, 0), 0.5F).meat().build();

	//Pufferfish 1/0.1
	public static final Food SALTED_PUFFER = (new Food.Builder()).hunger(1).saturation(0.2F).effect(new EffectInstance(Effects.POISON, 600, 1), 1.0F).effect(new EffectInstance(Effects.HUNGER, 160, 2), 1.0F).effect(new EffectInstance(Effects.NAUSEA, 160, 1), 1.0F).build();

	// Salmon 2/0.1
	// Cooked Slamon 6/0.8
	public static final Food SALTED_SALMON = (new Food.Builder()).hunger(5).saturation(0.9F).build();

}
