package com.supermanitu.advanceddispensers.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public interface IHasSubtypes 
{
	public String getVariantName(int i);
	
	public void addVariantName(Item item);
	
	public Class<? extends ItemBlock> getItemBlock();
	
	public void addRecipes();
}
