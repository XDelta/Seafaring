package tk.deltawolf.sea.lists;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tk.deltawolf.sea.entity.passive.fish.HaddockEntity;
import tk.deltawolf.sea.entity.passive.fish.SwampFeederEntity;
import tk.deltawolf.sea.renderer.entity.HaddockRenderer;
import tk.deltawolf.sea.renderer.entity.SwampFeederRenderer;

@OnlyIn(Dist.CLIENT)
public class RendererList {
	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(HaddockEntity.class, new HaddockRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(SwampFeederEntity.class, new SwampFeederRenderer.RenderFactory());
	}
}
