package tk.deltawolf.sea.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SwampFeederModel<T extends Entity> extends EntityModel<T> {
	private final RendererModel body;
	private final RendererModel head;
	private final RendererModel headFront;
	private final RendererModel finRight;
	private final RendererModel finLeft;
	private final RendererModel finTop;
	private final RendererModel finBottom;
	private final RendererModel tail;

	public SwampFeederModel() {
		//(name,texOffX,texOffY,offX,offY,offZ,width,height,depth,?)
		this.textureWidth = 32;
		this.textureHeight = 32;
		int i = 22;
		this.body = new RendererModel(this, 1, 1);
		this.body.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 6);
		this.body.setRotationPoint(0.0F, 22.0F, 0.0F);

		this.head = new RendererModel(this, 11, 0);
		this.head.addBox(-1.0F, -2.0F, -3.0F, 2, 4, 3);
		this.head.setRotationPoint(0.0F, 22.0F, 0.0F);
		this.headFront = new RendererModel(this, 0, 0);
		this.headFront.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 1);
		this.headFront.setRotationPoint(0.0F, 22.0F, -3.0F);

		this.finRight = new RendererModel(this, 22, 1);
		this.finRight.addBox(-2.0F, 0.0F, -1.0F, 2, 0, 2);
		this.finRight.setRotationPoint(-1.0F, 23.0F, 0.0F);
		this.finRight.rotateAngleZ = (-(float)Math.PI / 4F);

		this.finLeft = new RendererModel(this, 22, 4);
		this.finLeft.addBox(0.0F, 0.0F, -1.0F, 2, 0, 2);
		this.finLeft.setRotationPoint(1.0F, 23.0F, 0.0F);
		this.finLeft.rotateAngleZ = ((float)Math.PI / 4F);

		this.finTop = new RendererModel(this, 0, 4);
		this.finTop.addBox(0.0F, -2.0F, -2.0F, 0, 2, 7);
		this.finTop.setRotationPoint(0.0F, 20.0F, 0.0F);

		this.finBottom = new RendererModel(this, 0, 9);
		this.finBottom.addBox(0.0F, 4.0F, 1.0F, 0, 1, 4);
		this.finBottom.setRotationPoint(0.0F, 20.0F, 0.0F);

		this.tail = new RendererModel(this, 22, 3);
		this.tail.addBox(0.0F, -2.0F, 0.0F, 0, 4, 4);
		this.tail.setRotationPoint(0.0F, 22.0F, 6.0F);
	}

	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.body.render(scale);
		this.head.render(scale);
		this.headFront.render(scale);
		this.finRight.render(scale);
		this.finLeft.render(scale);
		this.finTop.render(scale);
		this.finBottom.render(scale);
		this.tail.render(scale);
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		float f = 1.0F;
		if (!entityIn.isInWater()) {
			f = 1.5F;
		}

		this.tail.rotateAngleY = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
	}
}
