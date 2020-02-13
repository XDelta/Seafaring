package tk.deltawolf.sea.handler;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import tk.deltawolf.sea.cmd.CmdGm;
import tk.deltawolf.sea.cmd.CmdGmChar;
import tk.deltawolf.sea.cmd.CmdGmInt;
import tk.deltawolf.sea.cmd.CmdGmX;
import tk.deltawolf.sea.util.Util;

public class CmdHandler {

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		CommandDispatcher dispatcher = event.getCommandDispatcher();
		Util.Log().info("Registering Commands");
		try {
			CmdGm.register(dispatcher); // /gm creative
			CmdGmInt.register(dispatcher); // /gm 1
			CmdGmChar.register(dispatcher); // /gm c or /gm sp
			CmdGmX.register(dispatcher); // /gm* and /gm# cmds
		} catch (Exception e) {
			Util.Log().error("Error Registering Commands");
			Util.Log().error(e);
		}
	}
}
