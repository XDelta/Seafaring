package tk.deltawolf.sea.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tk.deltawolf.sea.Reference;
import tk.deltawolf.sea.container.TackleBenchContainer;

public class TackleBenchScreen extends ContainerScreen<TackleBenchContainer> {
	private ResourceLocation TACKLEBENCH_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/tackle_bench.png");
	public TackleBenchScreen(TackleBenchContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(this.title.getFormattedText(), 98.0F, 6.0F, 4210752);//8.0F,6.0F
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F,1.0F,1.0F,1.0F);
		this.minecraft.getTextureManager().bindTexture(TACKLEBENCH_GUI_TEXTURE);
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;
		this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
	}
}
