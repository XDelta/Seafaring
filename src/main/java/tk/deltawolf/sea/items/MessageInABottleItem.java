package tk.deltawolf.sea.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import tk.deltawolf.sea.lists.ItemList;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class MessageInABottleItem extends Item {
	private Random rand = new Random();

	public MessageInABottleItem(Properties properties) {
		super(properties);
	}

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);
		world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));

		int messageRoll = rand.nextInt(12) + 1; //1-13
		int fishRoll = rand.nextInt(3); //0-3
		//TODO in future make a scroll of paper/note that shows the message if there is one otherwise notify in chat to player
		String message;
		if (messageRoll == 0) {//shouldn't be 0
			message = "The message crumbled apart in your hands";
		} else if (messageRoll == 2) {//give player a piece of paper
			message = "sea.message" + messageRoll;
			player.inventory.addItemStackToInventory(new ItemStack(Items.PAPER));

		} else if (messageRoll == 4) {
			message = "sea.message" + messageRoll;
			switch (fishRoll) {
				case 1:
					player.inventory.addItemStackToInventory(new ItemStack(Items.TROPICAL_FISH));
					break;
				case 2:
					player.inventory.addItemStackToInventory(new ItemStack(ItemList.haddock.get()));
					break;
				default://0
					player.inventory.addItemStackToInventory(new ItemStack(Items.COD));
			}
		} else if (messageRoll == 5) {//give player sea_moss
			message = "sea.message" + messageRoll;
			player.inventory.addItemStackToInventory(new ItemStack(ItemList.sea_moss.get()));
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
		list.add(new TranslationTextComponent("item.sea.message_in_a_bottle.tooltip"));
	}
}
