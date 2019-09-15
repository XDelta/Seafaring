package tk.deltawolf.sea.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.deltawolf.sea.Reference;

public class Util {
	private static Logger logger;
	/**
	 * Log Information to MC Console<p>
	 * Usage: Util.Log().info("Info!");<p>
	 * Accepts: info, warn, debug, error, fatal
	 */
	public static Logger Log() { //Util.Log().info("Info!"); //info,warn,error
		if (logger == null) {
			logger = LogManager.getFormatterLogger((Reference.MOD_ID).toUpperCase());
		}
		return logger;
	}
	/**
	 * Checks for an armor item equiped in slot<p>
	 * Usage: isEquipped(player, ItemList.ScubaMask, 0);<p>
	 * slot 0-3 for head-feet
	 */
	public static boolean isEquipped(PlayerEntity player, Item armor, int slot) {
		switch (slot) {
			case 0:
				if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == armor) return true;
				break;
			case 1:
				if(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == armor) return true;
				break;
			case 2:
				if(player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == armor) return true;
				break;
			case 3:
				if(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == armor) return true;
				break;
			default:
				break;
		}
		return false;
	}
}
