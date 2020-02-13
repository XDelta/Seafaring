package tk.deltawolf.sea.handler.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import tk.deltawolf.sea.lists.ItemList;
import tk.deltawolf.sea.util.Util;

public class EventClientWeighted {
//	@OnlyIn(Dist.CLIENT)
//	@SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
//	public void onFogRender(EntityViewRenderEvent.FogDensity event) {
//		PlayerEntity player = mc.player;
//		if (player.isInWater()) {
//
//			if (Util.isEquipped(player, ItemList.scuba_mask)) {
//				event.setDensity(0.1F);
//				event.setCanceled(true);
//			}
//		}
//	}
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.getEntity() != null && event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)event.getEntity();
			if (!player.isCreative() && !player.isSpectator()) {
				if (Util.isEquipped(player, ItemList.weighted_boots)) {
					player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 0, false, false));
					if (player.isInWater() && player.getMotion().y < -0.04) {//-0.0784000015258789
						player.setMotion(player.getMotion().x,player.getMotion().y * 1.25D,player.getMotion().z);
					}
				}
			}
		}
	}
}
