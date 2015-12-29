package com.supermanitu.advanceddispensers.breaker;

import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.item.ItemStack;

public class TileEntityBreaker extends TileEntityAdvancedDispensers
{
	public TileEntityBreaker() 
	{
		super(new ItemStack[9]);
	}

	@Override
	protected String getUnlocalizedName()
	{
		return  "tile.blockbreaker_" + ((BreakerTier) worldObj.getBlockState(getPos()).getValue(BlockBreaker.PROPERTYTIER)).getName() + ".name";
	}
}
