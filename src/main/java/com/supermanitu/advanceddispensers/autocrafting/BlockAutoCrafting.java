package com.supermanitu.advanceddispensers.autocrafting;

import java.util.Random;

import com.supermanitu.advanceddispensers.lib.BlockAdvancedDispensers;
import com.supermanitu.advanceddispensers.main.AdvancedDispensersGuiHandler;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAutoCrafting extends BlockAdvancedDispensers
{
	public BlockAutoCrafting(int tickRate)
	{
		super(tickRate, Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityAutoCrafting();
	}

	@Override
	public Object[] getRecipe()
	{
		return new Object[]{"XZX","ZOZ","XYX", 'Z', Items.redstone, 'Y', Blocks.piston, 'X', Items.iron_ingot, 'O', Blocks.crafting_table};
	}

	@Override
	public String getName() 
	{
		return "blockautocrafting";
	}

	@Override
	protected int getGuiID() 
	{
		return AdvancedDispensersGuiHandler.AUTOCRAFTING_GUI;
	}
	
	
}
