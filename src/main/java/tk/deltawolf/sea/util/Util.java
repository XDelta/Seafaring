package tk.deltawolf.sea.util;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import tk.deltawolf.sea.Reference;

import java.util.Collection;

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
	 * Checks for an armor item equipped in slot<p>
	 * Usage: isEquipped(player, ItemList.ScubaMask);<p>
	 */
	public static boolean isEquipped(PlayerEntity player, Item armor) {
		if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == armor) return true;
		if(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == armor) return true;
		if(player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == armor) return true;
		if(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == armor) return true;
		return false;
	}

	public static boolean isInBubbleColumn(World world, PlayerEntity player) {
		Block block = world.getBlockState(new BlockPos(player.getPosition())).getBlock();
		return (block == Blocks.BUBBLE_COLUMN);
	}
}
