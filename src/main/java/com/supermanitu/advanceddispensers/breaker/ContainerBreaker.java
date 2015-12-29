package com.supermanitu.advanceddispensers.breaker;

import com.supermanitu.advanceddispensers.lib.ContainerAdvancedDispensers;
import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.inventory.IInventory;

public class ContainerBreaker extends ContainerAdvancedDispensers
{
	public ContainerBreaker(IInventory player, TileEntityAdvancedDispensers tileEntity) 
	{
		super(player, tileEntity);
	}
}
