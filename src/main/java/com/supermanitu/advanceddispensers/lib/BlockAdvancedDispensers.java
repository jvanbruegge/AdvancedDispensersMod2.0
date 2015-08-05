package com.supermanitu.advanceddispensers.lib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockAdvancedDispensers extends Block
{
	public BlockAdvancedDispensers(Material material) 
	{
		super(material);
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.SOLID;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	@Override
	public boolean isFullCube()
	{
		return true;
	}
	
	@Override
	public int getRenderType()
	{
		return 3;
	}
	
	public abstract Object[] getRecipe();
	public abstract String getName();
	public abstract int countSubtypes();
}
