package tk.deltawolf.sea.handler;

import net.minecraftforge.common.MinecraftForge;
//import tk.deltawolf.sea.handler.events.EventCapabilityAttachment;
import tk.deltawolf.sea.handler.events.EventClientWeighted;
import tk.deltawolf.sea.util.Util;

public class GameEventHandler {
	public static void init() {
		Util.Log().info("Loaded Game Events");
		MinecraftForge.EVENT_BUS.register(new CmdHandler());
		//MinecraftForge.EVENT_BUS.register(new EventCapabilityAttachment());
		MinecraftForge.EVENT_BUS.register(new EventClientWeighted());

		Util.Log().info("Loaded Game Events");
	}
}
