package com.supermanitu.advanceddispensers.breaker;

import com.supermanitu.advanceddispensers.lib.ContainerAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.GuiAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBreaker extends GuiAdvancedDispensers
{
	public GuiBreaker(InventoryPlayer player, TileEntityAdvancedDispensers tileEntity) throws Exception 
	{
		super(player, tileEntity, ContainerBreaker.class);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.getBlockInventory().getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.getPlayer().getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	    this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/dispenser.png"));
	    this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
