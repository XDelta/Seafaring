package tk.deltawolf.sea.cmd;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.world.GameType;
import tk.deltawolf.sea.util.Util;

import java.util.Collections;

public class CmdGmChar {
	public static void register(CommandDispatcher dispatcher) {
		LiteralArgumentBuilder literalBuilder = Commands.literal("gm").requires((commandSource) -> commandSource.hasPermissionLevel(2));
		GameType[] gameTypes = GameType.values();

		for (GameType gameType : gameTypes) {
			if (gameType != GameType.NOT_SET) {
				try {
					if (gameType != GameType.SPECTATOR) {
						literalBuilder.then(((LiteralArgumentBuilder) Commands.literal(Character.toString(gameType.getName().toLowerCase().charAt(0))).executes((context) -> Util.setGameMode(context, Collections.singleton(context.getSource().asPlayer()), gameType))).then(Commands.argument("target", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "target"), gameType))));
					} else {
						literalBuilder.then(((LiteralArgumentBuilder) Commands.literal("sp").executes((context) -> Util.setGameMode(context, Collections.singleton(context.getSource().asPlayer()), gameType))).then(Commands.argument("target", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "target"), gameType))));
					}
				} catch (Exception e) {
					Util.Log().error("Failed to exec command gm [char]");
				}
			}
		}
		dispatcher.register(literalBuilder);
	}
}
