package tk.deltawolf.sea.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class ItemMessageInABottle extends Item {
	private Random rand = new Random();

	public ItemMessageInABottle(Properties properties) {
		super(properties);
	}

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);
		world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));

		int messageRoll = rand.nextInt(12) + 1; //1-9
		//TODO in future make a scroll of paper/note that shows the message if there is one otherwise notify in chat to player
		String message;
		if (messageRoll == 0) {//shouldn't be 0
			message = "The message crumbled apart in your hands";
		} else if (messageRoll == 2) {
			message = "sea.message" + messageRoll;
			//give player a piece of paper
		} else if (messageRoll == 4) {
			message = "sea.message" + messageRoll;
			//give player a fish
		} else if (messageRoll == 5) {
			message = "sea.message" + messageRoll;
			//give player sea_moss
		} else {
			message = "sea.message" + messageRoll;
		}

		if (player instanceof ServerPlayerEntity) {
			TranslationTextComponent chatMessage = new TranslationTextComponent(message);
			player.sendMessage(chatMessage);
		}

		heldStack.shrink(1);

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.AQUA + "Well, it's a bottle with a message inside"));
	}
}
