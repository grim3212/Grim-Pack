package com.grim3212.mc.pack.core.manual.button;

import org.lwjgl.opengl.GL11;

import com.grim3212.mc.pack.core.manual.gui.GuiManualIndex;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonPause extends GuiButton {

	private boolean isPaused = false;

	public GuiButtonPause(int id, int j, int k) {
		super(id, j, k, 9, 10, "");
	}

	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		if (this.visible) {
			boolean flag = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(GuiManualIndex.texture);
			int k = 202;
			int l = 0;

			if (flag || isPaused) {
				l += 10;
			}

			this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 9, 10);
		}
	}

	public void setIsPaused(boolean pauseState) {
		this.isPaused = pauseState;
	}
}
