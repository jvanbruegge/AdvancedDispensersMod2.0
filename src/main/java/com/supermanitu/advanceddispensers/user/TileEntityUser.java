package com.supermanitu.advanceddispensers.user;

import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.item.ItemStack;

public class TileEntityUser extends TileEntityAdvancedDispensers
{
	public TileEntityUser() 
	{
		super(new ItemStack[9]);
	}

	@Override
	protected String getUnlocalizedName()
	{
		return "tile.blockuser.name";
	}
}