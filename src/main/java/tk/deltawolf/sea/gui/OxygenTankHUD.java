package tk.deltawolf.sea.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tk.deltawolf.sea.items.armor.OxygenTank;
import tk.deltawolf.sea.lists.ItemList;
import tk.deltawolf.sea.util.Util;

@Mod.EventBusSubscriber
@OnlyIn(Dist.CLIENT)
public class OxygenTankHUD {
	private static Minecraft mc = Minecraft.getInstance();
	private static float x = 0.0F;
	private static float y = 0.0F;
	private static float topOfGUI = 69.0F;
	private static String display = "";
	public OxygenTankHUD() {
	}
	@SubscribeEvent
	public static void render(Post event) {
		if (event.getType() == ElementType.TEXT) {
			ItemStack chest = mc.player.getItemStackFromSlot(EquipmentSlotType.CHEST);

			if (Util.isEquipped(mc.player, ItemList.scuba_mask, 0) && (chest.getItem() == ItemList.basic_tank)) {
				long ticks = chest.getMaxDamage() - chest.getDamage();
				long minutes = ticks / 20 / 60;
				long seconds = ticks / 20 % 60;
				//&& chest.getDamage() == chest.getMaxDamage() - 20
				if(minutes == 0 && seconds <= 1){
					display = TextFormatting.RED + "Tank Empty";
					mc.fontRenderer.drawStringWithShadow(display, getXCenter(display), getYCenter(), -1);
				} else if (minutes == 0 && seconds <= getLowWarn(chest.getItem())) {//15
					display = TextFormatting.YELLOW + "Low Oxygen: " + minutes + ":" + (seconds == 0 ? "00" : seconds < 10 ? "0" + seconds : seconds);
					mc.fontRenderer.drawStringWithShadow(display, getXCenter(display), getYCenter(), 4);
				} else {
					display = "Oxygen: " + minutes + ":" + (seconds == 0 ? "00" : seconds < 10 ? "0" + seconds : seconds);
					mc.fontRenderer.drawStringWithShadow(display, getXCenter(display), getYCenter(), -1);
				}
			}
		}
	}
	public static int getLowWarn(Item chest) {
		if(chest == ItemList.basic_tank) {
			return 5;
		} else if(chest == ItemList.standard_tank) {
			return 8;
		} else if (chest == ItemList.high_capacity_tank) {
			return 18;
		} else {
			return 0;
		}
	}

	public static float getXCenter(String text) {
		x = mc.mainWindow.getScaledWidth();
		return (x - mc.fontRenderer.getStringWidth(text)) / 2.0F;
	}

	public static float getYCenter() {
		y = mc.mainWindow.getScaledHeight();
		return y - topOfGUI;
	}
}
