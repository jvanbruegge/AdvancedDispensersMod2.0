package com.supermanitu.advanceddispensers.breaker;

import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersMod;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class BlockBreaker extends BlockAdvancedDispensers
{
	public BlockBreaker() 
	{
		super(Material.rock);
		this.setUnlocalizedName(getName());
		this.setCreativeTab(AdvancedDispensersMod.advancedDispensersTab);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public Object[] getRecipe()
	{
		return new Object[]{"XZX","ZOZ","BYB", 'Z', Items.redstone, 'Y', Blocks.piston, 'X', Items.iron_ingot, 'O', this.getItemByTier(), 'B', Blocks.stone};
	}

	@Override
	public String getName()
	{
		return "blockbreaker";
	}
	
	private Object getItemByTier()
	{
		return Items.iron_pickaxe;
	}
}
