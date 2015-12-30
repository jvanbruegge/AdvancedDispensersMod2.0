package com.supermanitu.advanceddispensers.autocrafting;

import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.item.ItemStack;

public class TileEntityAutoCrafting extends TileEntityAdvancedDispensers
{
	public TileEntityAutoCrafting() 
	{
		super(new ItemStack[33]);
	}

	@Override
	protected String getUnlocalizedName() 
	{
		return "tile.blockautocrafting.name";
	}

}
