package com.supermanitu.advanceddispensers.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ContainerAdvancedDispensers extends Container
{
	protected TileEntityAdvancedDispensers tileEntity;
	
	public ContainerAdvancedDispensers(IInventory player, TileEntityAdvancedDispensers tileEntity)
	{
		this.tileEntity = tileEntity;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileEntity.isUseableByPlayer(player);
	}
}
