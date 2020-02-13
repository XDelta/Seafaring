package tk.deltawolf.sea.renderer.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class FlippersModel<T extends LivingEntity> extends BipedModel<T> {
	//public static final FlippersModel INSTANCE = new FlippersModel();
	RendererModel rightFlipper;
	RendererModel leftFlipper;

	public FlippersModel() {
		super(1.0F, 0.0F, 64, 32);

		this.bipedBody.showModel = false;
		this.bipedHead.showModel = false;
		this.bipedHeadwear.showModel = false;
		this.bipedLeftArm.showModel = false;
		this.bipedLeftLeg.showModel = false;
		this.bipedRightArm.showModel = true;
		this.bipedRightLeg.showModel = true;

		this.rightFlipper = new RendererModel(this, 40, 0);
		this.rightFlipper.addBox(-3.25F, 11.75F, -7.0F, 5, 1, 7, 0.25F);
		this.rightFlipper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightFlipper.setTextureSize(64, 32);
		this.rightFlipper.mirror = true;
		setRotation(this.rightFlipper, 0.0F, 0.0F, 0.0F);

		this.leftFlipper = new RendererModel(this, 40, 0);
		this.leftFlipper.addBox(-1.75F, 11.75F, -7.0F, 5, 1, 7, 0.25F);
		this.leftFlipper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftFlipper.setTextureSize(64, 32);
		this.leftFlipper.mirror = false;
		setRotation(this.leftFlipper, 0.0F, 0.0F, 0.0F);

		this.bipedRightLeg.addChild(this.rightFlipper);
		this.bipedLeftLeg.addChild(this.leftFlipper);
	}

	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	private void setRotation(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

