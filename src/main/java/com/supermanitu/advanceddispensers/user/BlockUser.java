package com.supermanitu.advanceddispensers.user;

import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersGuiHandler;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockUser extends BlockAdvancedDispensers
{
	public BlockUser(int tickRate)
	{
		super(tickRate, Material.rock);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityUser();
	}

	@Override
	public Object[] getRecipe() 
	{
		return new Object[]{"XCX", "CSC", "FCF", 'X', Blocks.planks, 'C', Items.redstone, 'S', Blocks.dispenser, 'F', Blocks.stone};
	}

	@Override
	public String getName()
	{
		return "blockuser";
	}

	@Override
	protected int getGuiID() 
	{
		return AdvancedDispensersGuiHandler.USER_GUI;
	}
	
}