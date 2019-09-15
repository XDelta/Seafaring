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
import tk.deltawolf.sea.entity.passive.fish.HaddockEntity;
import tk.deltawolf.sea.renderer.entity.model.HaddockModel;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class HaddockRenderer extends MobRenderer<HaddockEntity, HaddockModel<HaddockEntity>> {
	private static final ResourceLocation HADDOCK_LOCATION = new ResourceLocation(Reference.MOD_ID, "textures/entity/fish/haddock.png");

	public HaddockRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new HaddockModel(), 0.3F);
	}

	@Nullable
	protected ResourceLocation getEntityTexture(HaddockEntity entity) {
		return HADDOCK_LOCATION;
	}

	protected void applyRotations(HaddockEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
		if (!entityLiving.isInWater()) {
			GlStateManager.translatef(0.1F, 0.1F, -0.1F);
			GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
		}
	}

	public static class RenderFactory implements IRenderFactory<HaddockEntity> {
		@Override
		public EntityRenderer<? super HaddockEntity> createRenderFor(EntityRendererManager manager) {
			return new HaddockRenderer(manager);
		}
	}
}
