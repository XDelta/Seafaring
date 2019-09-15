package tk.deltawolf.sea.proxy;

import tk.deltawolf.sea.util.Util;

public class ProxyClient extends ProxyCommon {
	public void construct() {
		super.construct();
	}
	public void preInit() {
		super.preInit();
		Util.Log().info("ClientSide Setup");
	}
	public void postInit() {
		Util.Log().info("ClientSide Finishing");
		//TitleScreenHandler.init();
		super.postInit();
	}
}
