package tk.deltawolf.sea.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.entity.passive.fish.SwampFeederEntity;
import tk.deltawolf.sea.renderer.entity.model.SwampFeederModel;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SwampFeederRenderer extends MobRenderer<SwampFeederEntity, SwampFeederModel<SwampFeederEntity>> {
	private static final ResourceLocation SWAMP_FEEDER_LOCATION = new ResourceLocation(Reference.MOD_ID, "textures/entity/fish/swamp_feeder.png");

	public SwampFeederRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new SwampFeederModel(), 0.3F);
	}

	@Nullable
	protected ResourceLocation getEntityTexture(SwampFeederEntity entity) {
		return SWAMP_FEEDER_LOCATION;
	}

	protected void applyRotations(SwampFeederEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
		if (!entityLiving.isInWater()) {
			GlStateManager.translatef(0.1F, 0.1F, -0.1F);
			GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
		}
	}

	public static class RenderFactory implements IRenderFactory<SwampFeederEntity> {
		@Override
		public EntityRenderer<? super SwampFeederEntity> createRenderFor(EntityRendererManager manager) { return new SwampFeederRenderer(manager); }
	}
}
