package tk.deltawolf.sea.handler;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import tk.deltawolf.sea.cmd.CmdGm;
import tk.deltawolf.sea.cmd.CmdGmChar;
import tk.deltawolf.sea.cmd.CmdGmInt;
import tk.deltawolf.sea.cmd.CmdGmX;
import tk.deltawolf.sea.config.Config;
import tk.deltawolf.sea.util.Util;

public class CmdHandler {

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		CommandDispatcher dispatcher = event.getCommandDispatcher();
		int x = 0;
		Util.Log().info("Registering Commands");
		try {
			if (Config.enableGameModeCommands){
				CmdGm.register(dispatcher); // /gm creative
				x+=1;
				CmdGmInt.register(dispatcher); // /gm 1
				x+=1;
				CmdGmChar.register(dispatcher); // /gm c or /gm sp
				x+=1;
				CmdGmX.register(dispatcher); // /gm* and /gm# cmds
				x+=1;
			} else {
				Util.Log().debug("Gamemode commands are not enabled");
			}
			Util.Log().info("Registered " + x + " Commands");
		} catch (Exception e) {
			Util.Log().error("Error Registering Commands");
			Util.Log().debug("Registered " + x + "/4");
			Util.Log().error(e);
		}
	}
}
