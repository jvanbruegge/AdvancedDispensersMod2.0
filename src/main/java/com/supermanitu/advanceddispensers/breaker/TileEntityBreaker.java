package com.supermanitu.advanceddispensers.breaker;

import java.util.List;
import java.util.Random;

import com.supermanitu.advanceddispensers.lib.IReceiveRedstoneOnce;
import com.supermanitu.advanceddispensers.lib.TileEntityAdvancedDispensers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
		Block blockInFront = world.getBlockState(frontPos).getBlock();

		List<ItemStack> drops = blockInFront.getDrops(world, pos, state, 0);

		for(ItemStack stack : drops)
		{
			System.out.println(stack.getDisplayName());
		}

		//TODO: Add drops to inventory

		world.setBlockToAir(frontPos);
	}
}
