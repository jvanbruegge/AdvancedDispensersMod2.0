package com.supermanitu.advanceddispensers.breaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.supermanitu.advanceddispensers.helper.DropHelper;
import com.supermanitu.advanceddispensers.lib.IReceiveRedstoneOnce;
import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class TileEntityBreaker extends TileEntityAdvancedDispensers implements IReceiveRedstoneOnce
{
	public TileEntityBreaker() 
	{
		super(new ItemStack[9]);
	}

	@Override
	protected String getUnlocalizedName()
	{
		return  "tile.blockbreaker_" + ((BreakerTier) worldObj.getBlockState(getPos()).getValue(BlockBreaker.PROPERTYTIER)).getName() + ".name";
	}

	@Override
	public void onReceiveRedstoneSignalOnce(World world, BlockPos pos, IBlockState state, Random rand)
	{
		EnumFacing facing = (EnumFacing) state.getValue(BlockBreaker.PROPERTYFACING);
		BlockPos frontPos = new BlockPos(pos.getX() + facing.getFrontOffsetX(), pos.getY() + facing.getFrontOffsetY(), pos.getZ() + facing.getFrontOffsetZ());
		IBlockState stateInFront = world.getBlockState(frontPos);
		Block blockInFront = stateInFront.getBlock();

		BreakerTier tier = (BreakerTier) state.getValue(BlockBreaker.PROPERTYTIER);
		
		if(blockInFront.getBlockHardness(world, frontPos) == -1) return; //If block is unbreakable
		
		if((tier.getName().equals("iron") && blockInFront.getHarvestLevel(stateInFront) <= 2)
				|| (tier.getName().equals("diamond") && blockInFront.getHarvestLevel(stateInFront) <= 3))
		{
			List<ItemStack> drops = blockInFront.getDrops(world, frontPos, world.getBlockState(frontPos), 0);
			List<ItemStack> remainingDrops = (List<ItemStack>) new ArrayList(drops).clone();

			for(ItemStack stack : drops)
			{
				for(ItemStack inventoryStack : inventory)
				{
					if(inventoryStack != null && inventoryStack.getItem().equals(stack.getItem()) && inventoryStack.getMaxStackSize() > inventoryStack.stackSize)
					{
						inventoryStack.stackSize++;
						remainingDrops.remove(stack);
						break;
					}
				}
			}
			
			List<ItemStack> collapsedDrops = collapseStacks(remainingDrops);
			List<ItemStack> collapsedDropsCopy = (List<ItemStack>) new ArrayList(collapsedDrops).clone();
			
			for(ItemStack stack : collapsedDrops)
			{
				for(int i = 0; i < inventory.length; i++)
				{
					if(inventory[i] == null)
					{
						this.setInventorySlotContents(i, stack);
						collapsedDropsCopy.remove(stack);
						break;
					}
				}
			}
			
			for(ItemStack stack : collapsedDropsCopy)
			{
				DropHelper.spawnItemStack(world, pos, stack);
			}
			blockInFront.breakBlock(world, frontPos, stateInFront);
			world.setBlockToAir(frontPos);
			
		}
	}

	private List<ItemStack> collapseStacks(List<ItemStack> remainingDrops) 
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		
		for(ItemStack stack : remainingDrops)
		{
			boolean added = false;
			
			for(ItemStack listStack : list)
			{
				if(stack.getItem().equals(listStack.getItem()))
				{
					listStack.stackSize++;
					added = true;
					break;
				}
			}
			
			if(!added)
			{
				list.add(stack);
			}
		}
		
		return list;
	}
}
