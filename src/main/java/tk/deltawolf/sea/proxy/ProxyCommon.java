package tk.deltawolf.sea.proxy;

import tk.deltawolf.sea.util.Util;
import tk.deltawolf.sea.worldgen.WorldGen;

public class ProxyCommon {
	public void construct() {
		Util.Log().info("Initialized");
	}
	public void preInit() {
		WorldGen.oreGen();
		WorldGen.oceanGen();
		Util.Log().info("Setting Up");
	}
	public void postInit() {
		Util.Log().info("Done!");
	}
}
