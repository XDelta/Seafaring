package tk.deltawolf.sea.cmd;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.world.GameType;
import tk.deltawolf.sea.util.Util;

import java.util.Collections;

public class CmdGmX {
	public static void register(CommandDispatcher dispatcher) {
		try {
			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gms").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.SURVIVAL)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.SURVIVAL))));
			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gmc").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.CREATIVE)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.CREATIVE))));
			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gma").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.ADVENTURE)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.ADVENTURE))));
			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gmsp").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.SPECTATOR)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.SPECTATOR))));

			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gm0").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.SURVIVAL)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.SURVIVAL))));
			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gm1").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.CREATIVE)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.CREATIVE))));
			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gm2").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.ADVENTURE)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.ADVENTURE))));
			dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) Commands.literal("gm3").requires((source) -> source.hasPermissionLevel(2))).executes((context) -> Util.setGameMode(context, Collections.singleton(((CommandSource) context.getSource()).asPlayer()), GameType.SPECTATOR)).then(Commands.argument("targets", EntityArgument.players()).executes((cmdContext) -> Util.setGameMode(cmdContext, EntityArgument.getPlayers(cmdContext, "targets"), GameType.SPECTATOR))));
		} catch (Exception e) {
			Util.Log().error("Failed to exec command gm [X]");
		}
	}
}
