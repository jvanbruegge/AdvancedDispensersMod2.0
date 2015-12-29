package com.supermanitu.advanceddispensers.breaker;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBreaker extends ItemBlock
{
	public ItemBlockBreaker(Block block)
	{
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int metadata)
	{
		return metadata;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return stack.getMetadata() == 0 ? "tile.blockbreaker_iron" : "tile.blockbreaker_diamond";
	}
}
