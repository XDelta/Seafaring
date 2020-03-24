package tk.deltawolf.sea.effects;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import tk.deltawolf.sea.util.Util;

/**
 * Stuns a {@link LivingEntity},
 *
 * reducing speed to 25% and an additional 5% per level.
 *
 * Has a 2% chance per tick to stop ActiveHand action and dismount the Entity,
 * chance increases by 2% per level
 */

public class EffectStun extends Effect {
	public EffectStun() {
		super(EffectType.HARMFUL, 0xa17600);
	}

	@Override
	public void performEffect(LivingEntity entityIn, int amplifier) {
		if (!entityIn.isSpectator() || !entityIn.isInvulnerable()) {
			entityIn.setMotion(entityIn.getMotion().mul(0.25D - (amplifier * 0.05), 1.0D, 0.25D - (amplifier * 0.05)));
			if ((Math.random() * 100.0F) >= (100.0F - ((amplifier+1) * 2.0))) {
				if(entityIn.getType() == EntityType.PLAYER) {
					entityIn.stopActiveHand();
					entityIn.stopRiding();
				}
				entityIn.attackEntityFrom(DamageSource.GENERIC, (float)(0.2F + (amplifier * 0.2)));
			}
		}
	}

	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
		gui.blit(x, y, 18, 18, 0, 0);
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
}

