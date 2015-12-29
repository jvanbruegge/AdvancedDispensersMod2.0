package com.supermanitu.advanceddispensers.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AdvancedDispensersTab extends CreativeTabs
{
	public AdvancedDispensersTab()
	{
		super("advancedDispensersTab");
	}

	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(AdvancedDispensersBlocks.getTabIcon());
	}
}
