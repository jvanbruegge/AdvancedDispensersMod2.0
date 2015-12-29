package com.supermanitu.advanceddispensers.lib;

import com.supermanitu.advanceddispensers.breaker.TileEntityBreaker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockAdvancedDispensers extends BlockContainer
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
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntityAdvancedDispensers tileEntity = (TileEntityAdvancedDispensers) world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, tileEntity);
		super.breakBlock(world, pos, state);
	}
	
	public abstract Object[] getRecipe();
	public abstract String getName();
}
