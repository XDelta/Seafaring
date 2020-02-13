package tk.deltawolf.sea.cmd;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.world.GameType;
import tk.deltawolf.sea.util.Util;

import java.util.Collections;

public class CmdGmInt {
	public static void register(CommandDispatcher dispatcher) {
		LiteralArgumentBuilder literalBuilder = Commands.literal("gm").requires((commandSource) -> commandSource.hasPermissionLevel(2));
		GameType[] gameTypes = GameType.values();

		for (GameType gameType : gameTypes) {
			if (gameType != GameType.NOT_SET) {
				try {
					literalBuilder.then(((LiteralArgumentBuilder) Commands.literal(Integer.toString(gameType.getID())).executes((context) -> Util.setGameMode(context, Collections.singleton(context.getSource().asPlayer()), gameType))).then(Commands.argument("target", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "target"), gameType))));
				} catch (Exception e) {
					Util.Log().error("Failed to exec command gm [int]");
				}
			}
		}
		dispatcher.register(literalBuilder);
	}
}
